package fi.csc.microarray.client.visualisation.methods.gbrowserng.view;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.MouseEvent;
import com.soulaim.desktop.DesktopAssetManager;
import com.soulaim.desktop.DesktopGL2;
import com.soulaim.desktop.DesktopTextureManager;
import com.soulaim.tech.gles.SoulGL2;

import com.soulaim.tech.gles.primitives.PrimitiveBuffers;
import com.soulaim.tech.gles.renderer.PrimitiveRenderer;
import com.soulaim.tech.gles.renderer.TextRenderer;
import com.soulaim.tech.managers.AssetManager;
import com.soulaim.tech.managers.ShaderManager;
import com.soulaim.tech.managers.TextureManager;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.GlobalVariables;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces.GenosideHudComponent;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.model.GenoSideTimer;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.view.overview.OverView;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.view.trackview.TrackView;


public class GenoGLListener implements GLEventListener, GenosideHudComponent {

    private GenoSideTimer timer = new GenoSideTimer();
    private OverView overView;
    private TrackView trackView;

    public boolean handle(MouseEvent event, float screen_x, float screen_y) {
        return overView.handle(event, screen_x, screen_y);
    }

    public boolean handle(KeyEvent event) {
        return overView.handle(event);
    }

	public GenoGLListener(OverView overView, TrackView trackView) {
		this.overView = overView;
		this.trackView = trackView;
	}

	public void display(GLAutoDrawable drawable) {
		SoulGL2 gl = new DesktopGL2(drawable.getGL().getGL2());
		gl.glClear(GL2.GL_DEPTH_BUFFER_BIT | GL2.GL_COLOR_BUFFER_BIT);

        float dt = timer.getDT();

        //overView.tick(dt);
        //overView.draw(gl);
        
        trackView.tick(dt);
        trackView.draw(gl);
	}

	public void dispose(GLAutoDrawable drawable) {
	}

	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();

        TextRenderer.createInstance();
        PrimitiveBuffers.createBuffers();
        AssetManager.setInstance(new DesktopAssetManager());
        TextureManager.setInstance(new DesktopTextureManager());
        TextureManager.init(new DesktopGL2(gl));
        ShaderManager.createPrograms(new DesktopGL2(gl));

		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glClearDepth(1.0f);
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        SoulGL2 gl = new DesktopGL2(drawable.getGL().getGL2());
        gl.glViewport(0, 0, width, height);

        gl.glEnable(SoulGL2.GL_CULL_FACE);
        gl.glDisable(SoulGL2.GL_DEPTH_TEST);

        PrimitiveRenderer.onSurfaceChanged(width, height);
        TextRenderer.getInstance().onSurfaceChanged(width, height);

        float aspectRatio = width * 1.0f / height;
        GlobalVariables.aspectRatio = aspectRatio;
	}
}
