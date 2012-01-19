package fi.csc.microarray.client.visualisation.methods.gbrowserng.view.overview;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.MouseEvent;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.SpaceDivider;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.data.Session;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces.GenosideComponent;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.model.GeneCircle;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.model.GenoFPSCounter;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.view.trackview.SessionView;
import gles.SoulGL2;
import gles.renderer.TextRenderer;
import math.Matrix4;
import math.Vector2;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;


public class OverView extends GenosideComponent {

    GeneCircle geneCircle = new GeneCircle();
    GeneCircleGFX geneCircleGFX = new GeneCircleGFX(geneCircle);
    GenoFPSCounter fpsCounter = new GenoFPSCounter();

    private Vector2 mousePosition = new Vector2();

    ConcurrentLinkedQueue<SessionViewCapsule> sessions = new ConcurrentLinkedQueue<SessionViewCapsule>();
    ConcurrentLinkedQueue<SessionViewCapsule> activeSessions = new ConcurrentLinkedQueue<SessionViewCapsule>();
    ConcurrentLinkedDeque<SessionViewRecentCapsule> recentSessions = new ConcurrentLinkedDeque<SessionViewRecentCapsule>();

    OverViewState state = OverViewState.OVERVIEW_ACTIVE;

    public OverView() {
        super(null);
    }

    @Override
    public void childComponentCall(String who, String what) {
        if(who.equals("SESSION")) {
            if(what.equals("SHRINK")) {
                disableActiveSession();
                state = OverViewState.OVERVIEW_ACTIVE;
            }
            if(what.equals("KILL")) {
                killActiveSession();
                disableActiveSession();
                state = OverViewState.OVERVIEW_ACTIVE;
            }
            if(what.equals("OPEN_ANOTHER")) {
                hideActiveSessions();
                state = OverViewState.OVERVIEW_ACTIVE;
            }
        }
    }

    private void showActiveSessions() {
        SpaceDivider divider = new SpaceDivider(SpaceDivider.HORIZONTAL, 1.0f, 2.0f);
        for(SessionViewCapsule capsule : activeSessions)
            divider.insertComponent(capsule.getSession());
        divider.calculate();
    }

    private void hideActiveSessions() {

        for(SessionViewCapsule otherCapsule : sessions) {
            otherCapsule.show();
        }

        for(SessionViewCapsule capsule : activeSessions) {
            capsule.getSession().setPosition(+2.5f, 0);
        }

    }

    private void killActiveSession() {
        if(activeSessions.isEmpty())
            return;

        for(SessionViewCapsule capsule : activeSessions) {
            if(capsule.isActive()) {
                capsule.die();
                capsule.getSession().setPosition(-1.4f, 0.0f);
                if(recentSessions.size()>=4)
                {
                	for(SessionViewRecentCapsule recentcapsule : recentSessions)
                	{
                		recentcapsule.setId(recentcapsule.getId()-1);
                	}
                	recentSessions.remove(recentSessions.getFirst());
                }
                for(SessionViewRecentCapsule recentcapsule : recentSessions) recentcapsule.show();
                int id;
                if(recentSessions.size()>0) id=recentSessions.getLast().getId()+1;
                else id=0;
                SessionViewRecentCapsule recent=new SessionViewRecentCapsule(id, capsule.getPosition(), capsule.getGeneCirclePosition(), capsule.getSession(), capsule.getSession().getSession());
                recentSessions.add(recent);
                recent.show();
            }
        }
    }

    // TODO: might be a good idea to break this function apart
    private void disableActiveSession() {
        if(activeSessions.isEmpty())
            return;

        for(SessionViewCapsule otherCapsule : sessions) {
            otherCapsule.show();
        }

        for(SessionViewCapsule capsule : activeSessions) {
            capsule.deactivate();
        }

        activeSessions.clear();
    }


    // TODO: This is becoming quite tedious. Consider writing separate input-handler classes.
    public boolean handle(MouseEvent event, float x, float y) {

        // if there is an active session, let it handle input.
        if(!activeSessions.isEmpty()) {
            for(SessionViewCapsule capsule : activeSessions)
                if(capsule.getSession().inComponent(x, y))
                    return capsule.getSession().handle(event, x, y);
        }

        // note, x axis is negated to make tracking begin from the mathematical zero angle.
        float pointerGenePosition = 1.0f - ((float) (Math.atan2(y, -x) / Math.PI) * 0.5f + 0.5f);
        geneCircle.updatePosition(pointerGenePosition);

        // allow capsules to update their states
        for(SessionViewCapsule capsule : sessions)
            capsule.handle(event, x, y);
        
        for(SessionViewRecentCapsule capsule : recentSessions)
        {
        	capsule.handle(event, x, y);
        }

        // then see if they actually want the event
        if(MouseEvent.EVENT_MOUSE_CLICKED == event.getEventType() && event.getButton() == 1) {
            for(SessionViewCapsule capsule : sessions) {
                if(capsule.isDying()) {
                    continue;
                }
                if(capsule.handle(event, x, y)) {
                    capsule.activate();
                    activeSessions.add(capsule);

                    showActiveSessions();
                    state = OverViewState.SESSIONVIEW_ACTIVE;

                    for(SessionViewCapsule otherCapsule : sessions) {
                        boolean found = false;
                        for(SessionViewCapsule activeCapsule : activeSessions) {
                            if(otherCapsule.getId() == activeCapsule.getId()) {
                                found = true;
                            }
                        }

                        if(!found)
                            otherCapsule.hide();
                    }
                    for(SessionViewRecentCapsule recentCapsule : recentSessions)
                    	recentCapsule.hide();

                    return true;
                }
            }
            for(SessionViewRecentCapsule capsule : recentSessions)
            {
            	if(capsule.handle(event, x,y))
            	{
            		SessionViewCapsule restorecapsule = new SessionViewCapsule(new SessionView(capsule.getSession(), this), capsule.getOldGeneCirclePosition());
            		restorecapsule.getSession().setDimensions(0.4f, 0.2f);
            		Vector2 oldpos=capsule.getOldPosition();
            		restorecapsule.getSession().setPosition(oldpos.x, oldpos.y);
            		sessions.add(restorecapsule);
            		recentSessions.remove(capsule);
            		
            		if(recentSessions.size()>0)
            		{
	            		// This could be better, but as there won't be more than few recent capsules saved anyway, it will do.
	            		recentSessions.getFirst().setId(0);
	            		int id=0;
	                	for(SessionViewRecentCapsule recentcapsule : recentSessions)
	                	{
	                		recentcapsule.setId(id);
	                		++id;
	                	}
            		}
            		return true;
            	}
            }

            // respond to mouse click
            System.out.println("Adding capsule with " + x + " " + y);
            SessionViewCapsule capsule = new SessionViewCapsule(new SessionView(new Session(geneCircle.getChromosome().getReferenceSequence(), geneCircle.getChromosomePosition()), this), pointerGenePosition);
            capsule.getSession().setDimensions(0.4f, 0.2f);
            capsule.getSession().setPosition(x, y);
            sessions.add(capsule);
        }

        mousePosition.x = x;
        mousePosition.y = y;
        return false;
    }

    public boolean handle(KeyEvent event) {
        if (!activeSessions.isEmpty()) {
            for(SessionViewCapsule capsule : activeSessions) {
                if(capsule.inComponent(mousePosition.x, mousePosition.y))
                    return capsule.getSession().handle(event);
            }
        }
        return false;
    }

    public void draw(SoulGL2 gl) {
        Vector2 mypos = this.getPosition();
        Matrix4 geneCircleModelMatrix = new Matrix4();
        geneCircleModelMatrix.makeTranslationMatrix(mypos.x, mypos.y, 0);
        geneCircleModelMatrix.scale(0.5f, 0.5f, 0.5f);
        geneCircleGFX.draw(gl, geneCircleModelMatrix, this.mousePosition);

        for(SessionViewCapsule capsule : sessions) {
            capsule.draw(gl);
        }
        
        for(SessionViewRecentCapsule capsule : recentSessions)
        {
        	capsule.draw(gl);
        }

        TextRenderer.getInstance().drawText(gl, "FPS: " + fpsCounter.getFps(), 0, 0.92f, 0.9f);

        if(state == OverViewState.OVERVIEW_ACTIVE) {
            // Mouse hover information
            // TODO: Show the info of a session view, when hovering mouse over session view.
            TextRenderer.getInstance().drawText(gl, "Chromosome " + this.geneCircle.getChromosome().getChromosomeNumber(), 0, -0.86f, 0.8f);
            TextRenderer.getInstance().drawText(gl, "Position: " + this.geneCircle.getChromosomePosition(), 0, -0.95f, 0.8f);
        }
    }

    @Override
    public void userTick(float dt) {
        geneCircleGFX.tick(dt);
        fpsCounter.tick(dt);

        SessionViewCapsule killCapsule = null;
        for(SessionViewCapsule capsule : sessions) {
            if(!capsule.isAlive())
                killCapsule = capsule;
            capsule.tick(dt);
            capsule.clearPositionAdjustment();
        }
        
        for(SessionViewRecentCapsule capsule : recentSessions)
        {
        	capsule.tick(dt);
        }

        // if no active session, try to place session views in a good way.
        if(activeSessions.isEmpty()) {
            for(SessionViewCapsule capsule1 : sessions) {
                if(capsule1.isDying())
                    continue;

                // push away from the origin
                Vector2 position = new Vector2();
                position.copyFrom(capsule1.getSession().getPosition());
                position.normalize();
                position.scale(0.004f);
                capsule1.incrementPositionAdjustment(position.x, position.y);

                // pull towards target
                position.copyFrom(capsule1.getSession().getPosition());
                position.x -= capsule1.getPosition().x;
                position.y -= capsule1.getPosition().y;

                // to prevent overdoing things.
                float pow = Math.min(0.2f, position.lengthSquared());
                position.scale(0.1f * pow);

                capsule1.incrementPositionAdjustment(-position.x, -position.y);

                for(SessionViewCapsule capsule2 : sessions) {
                    if(capsule1.getId() == capsule2.getId())
                        continue;
                    if(capsule2.isDying())
                        continue;

                    if(capsule1.getSession().getPosition().distance( capsule2.getSession().getPosition() ) < 0.5f) {
                        Vector2 v = capsule1.getSession().getPosition().minus( capsule2.getSession().getPosition() );
                        v.scale(0.0005f / (0.001f + v.lengthSquared()));
                        capsule1.incrementPositionAdjustment(+v.x, +v.y);
                        capsule2.incrementPositionAdjustment(-v.x, -v.y);
                    }
                }
            }
        }

        if(killCapsule != null)
            sessions.remove(killCapsule);
    }

}

