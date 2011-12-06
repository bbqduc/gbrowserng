package fi.csc.microarray.client.visualisation.methods.gbrowserng.view.common;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.MouseEvent;
import com.soulaim.tech.gles.Color;
import com.soulaim.tech.gles.SoulGL2;
import com.soulaim.tech.gles.TextureID;
import com.soulaim.tech.gles.renderer.PrimitiveRenderer;
import com.soulaim.tech.math.Vector2;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces.GenosideComponent;

public class GenoButton extends GenosideComponent {

    TextureID myTexture = null;
    Color myColor = new Color(1, 1, 1, 1);

    float xpos = 1;
    float ypos = 1;

    public GenoButton(GenosideComponent parent) {
        super(parent);
        setDimensions(0.05f, 0.05f);
        myTexture = TextureID.QUIT_BUTTON;
    }

    @Override
    public boolean handle(MouseEvent event, float screen_x, float screen_y) {
        float dx = screen_x - this.getParent().glx(xpos) + 0.02f;
        float dy = screen_y - this.getParent().gly(ypos) + 0.02f;

        if(dx * dx + dy * dy < 0.02f * 0.02f) {
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
        PrimitiveRenderer.drawTexturedSquare(this.getParent().glx(xpos) - 0.02f, this.getParent().gly(ypos) - 0.02f, 0.02f, gl, myColor, myTexture);
        gl.glDisable(SoulGL2.GL_BLEND);
    }

    @Override
    public void userTick(float dt) {
    }

}
