package fi.csc.microarray.client.visualisation.methods.gbrowserng.view.sessionview;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.MouseEvent;
import com.soulaim.tech.gles.SoulGL2;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces.GenosideHudComponent;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces.GenosideVisualComponent;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.model.SessionData;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.view.trackview.TrackView;

import java.util.concurrent.ConcurrentLinkedQueue;

public class SessionView implements GenosideVisualComponent, GenosideHudComponent {

    private ConcurrentLinkedQueue<TrackView> trackViews = new ConcurrentLinkedQueue<TrackView>();
    private final SessionData sessionData;

    public SessionView(SessionData sessionData) {
        this.sessionData = sessionData;
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
        // first draw all the internal views
        for(TrackView t : trackViews) {
            t.draw(gl);
        }

        // then draw whatever this session view wants to draw.
    }

    // could update the state of child objects here
    // for example the information where they should be drawn etc.
    public void tick(float dt) {
        for(TrackView t : trackViews) {
            t.tick(dt);
        }
    }

}
