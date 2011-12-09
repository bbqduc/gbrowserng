package fi.csc.microarray.client.visualisation.methods.gbrowserng.view.common;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.MouseEvent;
import com.soulaim.tech.gles.Color;
import com.soulaim.tech.gles.SoulGL2;
import com.soulaim.tech.gles.TextureID;
import com.soulaim.tech.gles.renderer.PrimitiveRenderer;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces.GenosideComponent;

public class GenoButton extends GenosideComponent {

    private final TextureID myTexture;
    Color myColor = new Color(1, 1, 1, 1);

    float xpos = 1;
    float ypos = 1;
    String buttonName;
    private float buttonLock = 0;

    public GenoButton(GenosideComponent parent, String name, float x, float y, TextureID textureID) {
        super(parent);
        setDimensions(0.05f, 0.05f);

        myTexture = textureID;
        xpos = x;
        ypos = y;
        buttonName = name;
    }

    @Override
    public void childComponentCall(String who, String what) {
    }

    @Override
    public boolean handle(MouseEvent event, float screen_x, float screen_y) {

        if(buttonLock > 0.01f)
            return false;

        float dx = screen_x - this.getParent().glx(xpos) + 0.01f;
        float dy = screen_y - this.getParent().gly(ypos) + 0.01f;

        if(dx * dx + dy * dy < 0.02f * 0.02f) {
            if(event.getButton() == 1) {
                this.getAnimatedValues().setAnimatedValue("MOUSEHOVER", 0);
                this.getParent().childComponentCall(buttonName, "PRESSED");
                buttonLock = 0.5f;
                return true;
            }

            this.getAnimatedValues().setAnimatedValue("MOUSEHOVER", 1);
            return false;
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
        gl.glEnable(SoulGL2.GL_BLEND);
        myColor.g = 1f-this.getAnimatedValues().getAnimatedValue("MOUSEHOVER");
        myColor.b = 1f-this.getAnimatedValues().getAnimatedValue("MOUSEHOVER");
        float myScale = 0.02f + this.getAnimatedValues().getAnimatedValue("MOUSEHOVER") * 0.01f;
        PrimitiveRenderer.drawTexturedSquare(this.getParent().glx(xpos) - myScale*0.5f, this.getParent().gly(ypos) - myScale*0.5f, myScale, gl, myColor, myTexture);
        gl.glDisable(SoulGL2.GL_BLEND);
    }

    @Override
    public void userTick(float dt) {
        buttonLock -= dt;
        buttonLock = (buttonLock < 0) ? 0 : buttonLock;
    }

}
