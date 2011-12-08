package fi.csc.microarray.client.visualisation.methods.gbrowserng.view.sessionview;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.MouseEvent;
import com.soulaim.tech.gles.SoulGL2;

import com.soulaim.tech.gles.TextureID;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.data.Session;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces.GenosideComponent;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.view.common.GenoButton;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.view.common.GenoVisualBorder;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.view.trackview.TrackView;

import java.util.concurrent.ConcurrentLinkedQueue;

public class SessionView extends GenosideComponent {

    private final GenoButton quitButton = new GenoButton(this, "QUIT_BUTTON", 0.97f, 0.97f, TextureID.QUIT_BUTTON);
    private final GenoButton shrinkButton = new GenoButton(this, "SHRINK_BUTTON", 0.93f, 0.97f, TextureID.SHRINK_BUTTON);
    private final GenoVisualBorder border = new GenoVisualBorder(this);

    private ConcurrentLinkedQueue<TrackView> trackViews = new ConcurrentLinkedQueue<TrackView>();
    private final Session session;

    public SessionView(Session session, GenosideComponent parent) {
        super(parent);
        this.session = session;
        TrackView trackView1 = new TrackView(this, this.session);
        TrackView trackView2 = new TrackView(this, this.session);
        this.addTrackView(trackView1);
        this.addTrackView(trackView2);
    }
    
    public void addTrackView(TrackView view) {
    	this.trackViews.add(view);
    	int num = this.trackViews.size();
    	int current = 0;
    	for(TrackView t : this.trackViews) {
    		
    		float pos = (current+1.0f) / (num+1.0f);
    		t.setPosition(0, pos * 2 - 1);
    		t.setDimensions(2, 2.0f / num);
    		
    		++current;
    	}
    	
    }

    @Override
    public void childComponentCall(String who, String what) {

        if(who.equals("SHRINK_BUTTON")) {
            if(what.equals("PRESSED")) {
                getParent().childComponentCall("SESSION", "SHRINK");
            }
        }

        if(who.equals("QUIT_BUTTON")) {
            if(what.equals("PRESSED")) {
                getParent().childComponentCall("SESSION", "KILL");
            }
        }
    }

    public boolean handle(MouseEvent event, float screen_x, float screen_y) {

        quitButton.handle(event, screen_x, screen_y);
        shrinkButton.handle(event, screen_x, screen_y);

        // child views want to handle this?
        for(TrackView t : trackViews) {
            if(t.handle(event, screen_x, screen_y))
                return true;
        }

        // does this view want to handle the input?

        // if not, then say so.
        return false;
    }

    public boolean handle(KeyEvent event) {
    	
        // does this view want to handle the input?
        if(event.getKeyChar() == 'b') {
            getParent().childComponentCall("SESSION", "SHRINK");
            return true;
        }

        // child views want to handle this?
    	boolean handled = false;
        for(TrackView t : trackViews) {
            if(t.handle(event))
            	handled = true;
        }
        
        return handled;
    }

    public void draw(SoulGL2 gl) {

        // first draw all the internal views
        for(TrackView t : trackViews) {
            t.draw(gl);
        }

        // then draw whatever this session view wants to draw.
        quitButton.draw(gl);
        shrinkButton.draw(gl);
        border.draw(gl);
    }

    @Override
    public void userTick(float dt) {
        for(TrackView t : trackViews) {
            t.tick(dt);
        }

        quitButton.tick(dt);
        shrinkButton.tick(dt);
    }

}
