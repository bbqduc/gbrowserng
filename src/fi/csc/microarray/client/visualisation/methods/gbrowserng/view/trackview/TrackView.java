package fi.csc.microarray.client.visualisation.methods.gbrowserng.view.trackview;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.MouseEvent;
import com.soulaim.tech.gles.SoulGL2;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces.GenosideComponent;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.model.GeneTrack;

public class TrackView extends GenosideComponent {

    private final GeneTrack myTrack;

    public TrackView(GeneTrack track, GenosideComponent parent) {
        super(parent);
        this.myTrack = track;
    }

    public boolean handle(MouseEvent event, float screen_x, float screen_y) {
        return false;
    }

    public boolean handle(KeyEvent event) {
        return false;
    }

    public void draw(SoulGL2 gl) {
    }

    @Override
    protected void userTick(float dt) {
    }

}
