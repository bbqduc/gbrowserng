package fi.csc.microarray.client.visualisation.methods.gbrowserng.view;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.gl2.GLUgl2;

import fi.csc.microarray.client.visualisation.methods.gbrowserng.view.hud.Hud;

public class GenoGLListener implements GLEventListener {

	private Camera camera;
	private Hud hud;
	private SessionView sessionView;

	long current_time = 0;
	long last_time = 0;

	public GenoGLListener(Camera camera, Hud hud, SessionView sessionView) {
		this.camera = camera;
		this.hud = hud;
		this.sessionView = sessionView;
	}

	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_DEPTH_BUFFER_BIT | GL2.GL_COLOR_BUFFER_BIT);
		gl.glLoadIdentity();

		this.camera.draw(gl);
		this.sessionView.draw(gl);
		this.hud.draw(gl);
	}

	public void dispose(GLAutoDrawable drawable) {
	}

	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glBlendFunc(GL2.GL_ONE, GL2.GL_ONE);
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glClearDepth(1.0f);
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		GL2 gl = drawable.getGL().getGL2();
		
		gl.glViewport(0, 0, width, height);
		
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		
		new GLUgl2().gluPerspective(45.0f, (float)width/(float)height, 0.1f, 10.0f);
		
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
	}
}
