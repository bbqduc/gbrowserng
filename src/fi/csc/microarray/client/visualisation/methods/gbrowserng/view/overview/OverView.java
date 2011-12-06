package fi.csc.microarray.client.visualisation.methods.gbrowserng.view.overview;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.MouseEvent;
import com.soulaim.tech.gles.Color;
import com.soulaim.tech.gles.SoulGL2;
import com.soulaim.tech.gles.renderer.PrimitiveRenderer;
import com.soulaim.tech.gles.renderer.TextRenderer;
import com.soulaim.tech.math.Vector2;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.data.ReferenceSequence;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces.GenosideComponent;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.view.sessionview.SessionView;

import java.util.concurrent.ConcurrentLinkedQueue;

public class OverView extends GenosideComponent {

    private int mouseState = 0;
    private float mouse_x = 0;
    private float mousy_y = 0;
    private float geneHilightPower = 0;
    private float totalTime = 0;

    private float percentage = 0;

    ReferenceSequence referenceSequence = null;
    ConcurrentLinkedQueue<SessionView> sessions = new ConcurrentLinkedQueue<SessionView>();

    SessionView activeSession = null;

    public OverView() {
        super(null);
    }

    public boolean handle(MouseEvent event, float x, float y) {

        // if there is an active session, let it handle input.
        if(activeSession != null) {
            return activeSession.handle(event, x, y);
        }

        // if no session is active, handle input myself.
        if(event.getButton() == 1 && mouseState == 0) {
            mouseState = 1;

            // respond to mouse click
            percentage = (float)Math.atan2(y, x);
        }
        else if(event.getButton() != 1) {
            mouseState = 0;
        }

        mouse_x = x;
        mousy_y = y;

        return false;
    }

    public boolean handle(KeyEvent event) {
        return false;
    }


    public void draw(SoulGL2 gl) {
        Vector2 mypos = this.getPosition();
        PrimitiveRenderer.drawCircle(mypos.x, mypos.y, 0.40f, gl, Color.MAGENTA);
        PrimitiveRenderer.drawCircle(mypos.x, mypos.y, 0.38f, gl, Color.BLACK);
        TextRenderer.getInstance().drawText(gl, "lol: " + percentage, mypos.x, mypos.y, 1.0f);
    }

    @Override
    protected void userTick(float dt) {

        totalTime += dt;
        if(totalTime > 2) {
            this.setPosition(0.7f, 0.0f);
            totalTime = -4f;
        }

        if(totalTime < 0 && totalTime > -2) {
            this.setPosition(-0.7f, 0.0f);
            totalTime = 0;
        }

    }

}
