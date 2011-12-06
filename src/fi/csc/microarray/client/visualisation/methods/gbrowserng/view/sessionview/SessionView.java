package fi.csc.microarray.client.visualisation.methods.gbrowserng.view.sessionview;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.MouseEvent;
import com.soulaim.tech.gles.Color;
import com.soulaim.tech.gles.SoulGL2;

import fi.csc.microarray.client.visualisation.methods.gbrowserng.data.Session;
import com.soulaim.tech.gles.renderer.PrimitiveRenderer;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces.GenosideComponent;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.view.trackview.TrackView;

import java.util.concurrent.ConcurrentLinkedQueue;

public class SessionView extends GenosideComponent {

    private ConcurrentLinkedQueue<TrackView> trackViews = new ConcurrentLinkedQueue<TrackView>();
    private final Session session;

    public SessionView(Session session, GenosideComponent parent) {
        super(parent);
        this.session = session;
        this.trackViews.add(new TrackView(this, this.session));
    }

    public boolean handle(MouseEvent event, float screen_x, float screen_y) {
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

        // child views want to handle this?
        for(TrackView t : trackViews) {
            if(t.handle(event))
                return true;
        }

        // does this view want to handle the input?

        // if not, then say so.
        return false;
    }

    public void draw(SoulGL2 gl) {

        // this is just for debug
        PrimitiveRenderer.drawRectangle(glx(0), gly(0), getDimensions().x * 0.5f, getDimensions().y * 0.5f, gl, Color.WHITE);

        // first draw all the internal views
        for(TrackView t : trackViews) {
            t.draw(gl);
        }

        // then draw whatever this session view wants to draw.
    }

    @Override
    protected void userTick(float dt) {
        for(TrackView t : trackViews) {
            t.tick(dt);
        }
    }

}
