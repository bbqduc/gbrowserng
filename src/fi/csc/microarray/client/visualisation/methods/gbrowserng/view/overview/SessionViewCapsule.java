package fi.csc.microarray.client.visualisation.methods.gbrowserng.view.overview;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.MouseEvent;
import com.soulaim.tech.gles.SoulGL2;
import com.soulaim.tech.math.Vector2;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces.GenosideComponent;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.view.sessionview.SessionView;

public class SessionViewCapsule extends GenosideComponent {

    private final SessionView sessionView;
    private boolean requiresActivation = false;
    private boolean isActive = false;
    private boolean dying = false;
    private float death = 0;

    public SessionViewCapsule(SessionView sessionView) {
        super(null); // should be ok
        this.sessionView = sessionView;
    }

    public boolean isAlive() {
        return death < 1.0f;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean requiresActivation() {
        return requiresActivation;
    }

    public void activate() {
        System.out.println("activating capsule");
        isActive = true;
        requiresActivation = false;
        sessionView.setDimensions(2, 2);
        sessionView.setPosition(0, 0);
    }

    public void deactivate() {
        isActive = false;
        sessionView.setDimensions(0.4f, 0.2f);
    }

    @Override
    public boolean handle(MouseEvent event, float screen_x, float screen_y) {
        Vector2 dimensions = sessionView.getDimensions();
        Vector2 position = sessionView.getPosition();

        if(screen_x > position.x - dimensions.x * 0.5f && screen_x < position.x + dimensions.x * 0.5f)
            if(screen_y > position.y - dimensions.y * 0.5f && screen_y < position.y + dimensions.y * 0.5f) {
                return true;
            }

        return false;
    }

    @Override
    public boolean handle(KeyEvent event) {
        return false;
    }

    @Override
    protected void draw(SoulGL2 gl) {
        sessionView.draw(gl);
    }

    @Override
    protected void userTick(float dt) {
        if(dying) death += dt;
        sessionView.tick(dt);
    }

    public SessionView getSession() {
        return sessionView;
    }

    public void die() {
        dying = true;
    }
}
