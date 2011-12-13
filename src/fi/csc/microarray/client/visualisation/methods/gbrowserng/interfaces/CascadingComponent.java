package fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces;

import com.soulaim.tech.math.Vector2;

public abstract class CascadingComponent {

    CascadingComponent parent;

    public CascadingComponent(CascadingComponent parent) {
        this.parent = parent;
    }

    private Vector2 currentPosition = new Vector2();
    private Vector2 targetPosition = new Vector2();
    private Vector2 currentDimensions = new Vector2();
    private Vector2 targetDimensions = new Vector2();

    private Vector2 relativePosition = new Vector2();
    private Vector2 relativeDimensions = new Vector2();

    public boolean inScreen() {
        if(getPosition().x + getDimensions().x * 0.5f < -1) return false;
        if(getPosition().x - getDimensions().x * 0.5f > +1) return false;
        if(getPosition().y + getDimensions().y * 0.5f < -1) return false;
        if(getPosition().y - getDimensions().y * 0.5f > +1) return false;
        return true;
    }

    public CascadingComponent getParent() {
        return parent;
    }

    public void cascadingTick(float dt) {
        currentPosition.approach(targetPosition, dt, 0.06f);
        currentDimensions.approach(targetDimensions, dt, 0.06f);
        updateDimensions();
        updatePosition();
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


}
