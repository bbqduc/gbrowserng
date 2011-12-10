package fi.csc.microarray.client.visualisation.methods.gbrowserng.view.common;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.MouseEvent;
import com.soulaim.tech.gles.Color;
import com.soulaim.tech.gles.SoulGL2;
import com.soulaim.tech.gles.renderer.PrimitiveRenderer;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.GlobalVariables;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces.GenosideComponent;

public class GenoVisualBorder extends GenosideComponent {

    public GenoVisualBorder(GenosideComponent parent) {
        super(parent);
    }

    @Override
    public void childComponentCall(String who, String what) {
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
    public void draw(SoulGL2 gl) {
        PrimitiveRenderer.drawRectangle(this.getParent().glx(0), this.getParent().gly(-1), this.getParent().glxSize(1.0f), 0.003f, gl, Color.WHITE);
        PrimitiveRenderer.drawRectangle(this.getParent().glx(0), this.getParent().gly(+1), this.getParent().glxSize(1.0f), 0.003f, gl, Color.WHITE);
        PrimitiveRenderer.drawRectangle(this.getParent().glx(+1), this.getParent().gly(0), 0.003f, this.getParent().glySize(1.0f / GlobalVariables.aspectRatio), gl, Color.WHITE);
        PrimitiveRenderer.drawRectangle(this.getParent().glx(-1), this.getParent().gly(0), 0.003f, this.getParent().glySize(1.0f / GlobalVariables.aspectRatio), gl, Color.WHITE);
    }

    @Override
    public void userTick(float dt) {
    }
}