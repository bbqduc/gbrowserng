package fi.csc.microarray.client.visualisation.methods.gbrowserng.view.overview;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.MouseEvent;
import com.soulaim.tech.gles.Color;
import com.soulaim.tech.gles.SoulGL2;
import com.soulaim.tech.gles.renderer.PrimitiveRenderer;
import com.soulaim.tech.math.Vector2;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.GlobalVariables;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces.GenosideComponent;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.view.sessionview.SessionView;

public class SessionViewCapsule extends GenosideComponent {

    private final SessionView sessionView;
    private boolean requiresActivation = false;
    private boolean isActive = false;
    private boolean dying = false;
    private float death = 0;
    private Color backGroundColor = new Color(0, 0, 0, 255);


    public SessionViewCapsule(SessionView sessionView) {
        super(null); // should be ok
        this.sessionView = sessionView;
        this.getAnimatedValues().setAnimatedValue("ALPHA", 1.0f);
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
        this.getAnimatedValues().setAnimatedValue("ALPHA", -0.02f);
    }

    public void deactivate() {
        System.out.println("deactivating capsule");
        isActive = false;
        sessionView.setDimensions(0.4f, 0.2f);
        this.getAnimatedValues().setAnimatedValue("ALPHA", 1.0f);
    }

    @Override
    public void childComponentCall(String who, String what) {
    }

    @Override
    public boolean handle(MouseEvent event, float screen_x, float screen_y) {
        Vector2 dimensions = sessionView.getDimensions();
        Vector2 position = sessionView.getPosition();

        if(screen_x > position.x - dimensions.x * 0.5f && screen_x < position.x + dimensions.x * 0.5f)
            if(screen_y > position.y - dimensions.y * 0.5f && screen_y < position.y + dimensions.y * 0.5f) {
                this.getAnimatedValues().setAnimatedValue("MOUSEHOVER", 1);
                return true;
            }

        this.getAnimatedValues().setAnimatedValue("MOUSEHOVER", 0);
        return false;
    }

    @Override
    public boolean handle(KeyEvent event) {
        return false;
    }

    @Override
    public void draw(SoulGL2 gl) {

        // this is just for debug
        float v = this.getAnimatedValues().getAnimatedValue("MOUSEHOVER");
        float alpha = this.getAnimatedValues().getAnimatedValue("ALPHA");

        if(alpha > 0) {
            gl.glEnable(SoulGL2.GL_BLEND);
            this.backGroundColor.r = v;
            this.backGroundColor.g = v;
            this.backGroundColor.b = v;
            this.backGroundColor.a = alpha * (1.0f - death);
            PrimitiveRenderer.drawRectangle(sessionView.glx(0), sessionView.gly(0), sessionView.getDimensions().x * 0.5f, sessionView.getDimensions().y * 0.5f / GlobalVariables.aspectRatio, gl, backGroundColor);
            gl.glDisable(SoulGL2.GL_BLEND);
        }

        sessionView.draw(gl);
    }

    @Override
    public void userTick(float dt) {
        if(dying) death += dt;
        sessionView.tick(dt);
    }

    public SessionView getSession() {
        return sessionView;
    }

    public void die() {
        dying = true;
    }

    public boolean isDying() {
        return dying;
    }

    public void hide() {
        // todo
        Vector2 myPos = this.sessionView.getPosition();
        myPos.normalize();
        myPos.scale(1.7f);
        this.sessionView.setPosition(myPos.x, myPos.y);
    }

    public void show() {
        // todo
        Vector2 myPos = this.sessionView.getPosition();
        myPos.normalize();
        myPos.scale(0.7f);
        this.sessionView.setPosition(myPos.x, myPos.y);
    }
}

