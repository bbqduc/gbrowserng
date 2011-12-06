package fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.MouseEvent;
import com.soulaim.tech.gles.SoulGL2;
import com.soulaim.tech.math.Vector2;

public abstract class GenosideComponent {

    private Vector2 currentPosition = new Vector2();
    private Vector2 targetPosition = new Vector2();
    private Vector2 currentDimensions = new Vector2();
    private Vector2 targetDimensions = new Vector2();

    private Vector2 relativePosition = new Vector2();
    private Vector2 relativeDimensions = new Vector2();

    private final GenosideComponent parent;

    public GenosideComponent(GenosideComponent parent) {
        this.parent = parent;
    }

    public abstract boolean handle(MouseEvent event, float screen_x, float screen_y);
    public abstract boolean handle(KeyEvent event);
    protected abstract void draw(SoulGL2 gl);
    protected abstract void userTick(float dt);

    public Vector2 getPosition() {
        return relativePosition;
    }

    public Vector2 getDimensions() {
        return relativeDimensions;
    }

    private void updatePosition() {
        if(parent == null)
            relativePosition.copyFrom(currentPosition);
        else
            relativePosition.copyFrom( parent.getPosition().plus(new Vector2(parent.getDimensions().x * 0.5f * currentPosition.x, parent.getDimensions().y * 0.5f * currentPosition.y)) );
    }

    private void updateDimensions() {
        if(parent == null) {
            relativeDimensions.x = 2;
            relativeDimensions.y = 2;
        }
        else
            relativeDimensions.copyFrom( parent.getDimensions().scale(0.5f).scale(new Vector2(currentDimensions.x * 0.5f, currentDimensions.y * 0.5f)).scale(2.0f) );
    }

    // transforms a float from -1, 1 range to the correct position within this component
    public float glx(float x) {
        return getPosition().x + getDimensions().x * 0.5f * x;
    }

    // transforms a float from -1, 1 range to the correct position within this component
    public float gly(float y) {
        return getPosition().y + getDimensions().y * 0.5f * y;
    }

    public void setPosition(float x, float y) {
        targetPosition.x = x;
        targetPosition.y = y;
    }

    public void setDimensions(float w, float h) {
        targetDimensions.x = w;
        targetDimensions.y = h;
    }

    public void tick(float dt) {
        currentPosition.approach(targetPosition, dt, 0.06f);
        currentDimensions.approach(targetDimensions, dt, 0.06f);

        updateDimensions();
        updatePosition();

        userTick(dt);
    }
}
