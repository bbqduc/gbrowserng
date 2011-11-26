package fi.csc.microarray.client.visualisation.methods.gbrowserng.view.hud;

import javax.media.opengl.GL2;

import com.jogamp.newt.event.MouseEvent;

import fi.csc.microarray.client.visualisation.methods.gbrowserng.controller.GenoEvent;

public class Hud {
	private TextButton banner;
	private TextButton plusButton;
	private TextButton minusButton;
	
	public Hud()
	{
		this.banner = new TextButton("GenomeBrowserNG", -1.0f, -1.0f, -0.7f, -0.9f, 0.0f);
		this.plusButton = new TextButton("+", -1.0f, 0.8f, -0.9f, 0.9f, 0.0f);
		this.minusButton = new TextButton("-", -1.0f, 0.7f, -0.9f, 0.8f, 0.0f);
	}

	public void update(GenoEvent e)
	{
		if (e.event instanceof MouseEvent)
		{
			float mouseX = e.getMouseGLX();
			float mouseY = e.getMouseGLY();
			this.minusButton.update(mouseX, mouseY);
			this.plusButton.update(mouseX, mouseY);
		}
	}
	
	public void draw(GL2 gl)
	{
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glPushMatrix();
		gl.glLoadIdentity();
		
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glPushMatrix();
		gl.glLoadIdentity();
		
		this.banner.draw(gl);
		this.plusButton.draw(gl);
		this.minusButton.draw(gl);
		
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glPopMatrix();
		
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glPopMatrix();
	}
}
