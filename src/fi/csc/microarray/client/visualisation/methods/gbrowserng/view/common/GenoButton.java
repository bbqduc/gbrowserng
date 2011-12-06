package fi.csc.microarray.client.visualisation.methods.gbrowserng.view.common;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.MouseEvent;
import com.soulaim.tech.gles.SoulGL2;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces.GenosideComponent;

public class GenoButton extends GenosideComponent {

    public GenoButton(GenosideComponent parent) {
        super(parent);
        setDimensions(0.1f, 0.1f);
    }

    @Override
    public boolean handle(MouseEvent event, float screen_x, float screen_y) {
        return false;
    }

    @Override
    public boolean handle(KeyEvent event) {
        return false;
    }

    @Override
    protected void draw(SoulGL2 gl) {
    }

    @Override
    protected void userTick(float dt) {
    }
}
