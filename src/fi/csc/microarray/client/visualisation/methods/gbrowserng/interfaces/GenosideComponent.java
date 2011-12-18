package fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.MouseEvent;
import com.soulaim.tech.gles.SoulGL2;

import fi.csc.microarray.client.visualisation.methods.gbrowserng.model.AnimatedValues;

public abstract class GenosideComponent extends CascadingComponent implements VisualComponent, InteractiveComponent {

    private static int idCounter = 0;
    private final int id;

    private AnimatedValues animatedValues = new AnimatedValues();

    public GenosideComponent(GenosideComponent parent) {
        super(parent);
        id = idCounter++;
    }

    public int getId() {
        return id;
    }

    public abstract void childComponentCall(String who, String what);
    public abstract boolean handle(MouseEvent event, float screen_x, float screen_y);
    public abstract boolean handle(KeyEvent event);
    public abstract void draw(SoulGL2 gl);
    public abstract void userTick(float dt);

    public AnimatedValues getAnimatedValues() {
        return animatedValues;
    }

    public void tick(float dt) {
        cascadingTick(dt);
        animatedValues.tick(dt);
        userTick(dt);
    }

    public GenosideComponent getParent() {
        return (GenosideComponent) parent;
    }
}
