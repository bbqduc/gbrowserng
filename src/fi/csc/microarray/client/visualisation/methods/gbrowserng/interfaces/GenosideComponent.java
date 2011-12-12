package fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.MouseEvent;
import com.soulaim.tech.gles.SoulGL2;
import com.soulaim.tech.math.Vector2;

import fi.csc.microarray.client.visualisation.methods.gbrowserng.model.AnimatedValues;

public abstract class GenosideComponent {

    private static int idCounter = 0;

    private final int id;
    private Vector2 currentPosition = new Vector2();
    private Vector2 targetPosition = new Vector2();
    private Vector2 currentDimensions = new Vector2();
    private Vector2 targetDimensions = new Vector2();

    private Vector2 relativePosition = new Vector2();
    private Vector2 relativeDimensions = new Vector2();

    private final GenosideComponent parent;
    private AnimatedValues animatedValues = new AnimatedValues();

    public GenosideComponent(GenosideComponent parent) {
        this.parent = parent;
        id = idCounter++;
    }

    public int getId() {
        return id;
    }

    public boolean inScreen() {
        if(getPosition().x + getDimensions().x * 0.5f < -1) return false;
        if(getPosition().x - getDimensions().x * 0.5f > +1) return false;
        if(getPosition().y + getDimensions().y * 0.5f < -1) return false;
        if(getPosition().y - getDimensions().y * 0.5f > +1) return false;
        return true;
    }

    public abstract void childComponentCall(String who, String what);
    public abstract boolean handle(MouseEvent event, float screen_x, float screen_y);
    public abstract boolean handle(KeyEvent event);
    public abstract void draw(SoulGL2 gl);
    public abstract void userTick(float dt);

    public AnimatedValues getAnimatedValues() {
        return animatedValues;
    }

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
            relativeDimensions.copyFrom( parent.getDimensions().scaled(0.5f).scale(new Vector2(currentDimensions.x * 0.5f, currentDimensions.y * 0.5f)).scale(2.0f) );
    }

    // transforms a float from -1, 1 range to the correct position within this component
    public float glx(float x) {
        return getPosition().x + getDimensions().x * 0.5f * x;
    }

    // transforms a float from -1, 1 range to the correct position within this component
    public float gly(float y) {
        return getPosition().y + getDimensions().y * 0.5f * y;
    }
    
    public float glySize(float y) {
    	return this.getDimensions().y/2 * y;
    }
    
    public float glxSize(float x) {
    	return this.getDimensions().x/2 * x;
    }

    public void setPosition(float x, float y) {
        targetPosition.x = x;
        targetPosition.y = y;
    }

    public void setDimensions(float w, float h) {
        targetDimensions.x = w;
        targetDimensions.y = h;
    }

    public void modifyPosition(float x, float y) {
        targetPosition.x += x;
        targetPosition.y += y;
    }

    public void modifyDimensions(float x, float y) {
        targetDimensions.x += x;
        targetDimensions.y += y;
    }

    public void tick(float dt) {
        currentPosition.approach(targetPosition, dt, 0.06f);
        currentDimensions.approach(targetDimensions, dt, 0.06f);
        animatedValues.tick(dt);

        updateDimensions();
        updatePosition();

        userTick(dt);
    }

    public GenosideComponent getParent() {
        return parent;
    }
}
