package fi.csc.microarray.client.visualisation.methods.gbrowserng.view;

import javax.media.opengl.GL2;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.MouseEvent;

import fi.csc.microarray.client.visualisation.methods.gbrowserng.controller.GenoEvent;

public class Camera {

	public float x;
	public float y;
	public float z;

	public Camera(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void update(GenoEvent e) {
		if (e.event instanceof MouseEvent) {
			MouseEvent event = (MouseEvent) e.event;
			if (event.getEventType() == MouseEvent.EVENT_MOUSE_CLICKED) {
				if (event.getButton() == MouseEvent.BUTTON4) {
					this.z -= 0.1f;
				} else if (event.getButton() == MouseEvent.BUTTON5) {
					this.z += 0.1f;
				}
			}
		} else if (e.event instanceof KeyEvent) {
			KeyEvent keyEvent = (KeyEvent) e.event;
			if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
				this.x -= 0.1f;
			} else if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
				this.x += 0.1f;
			} else if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
				this.y += 0.1f;
			} else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
				this.y -= 0.1f;
			}
		}
	}

	public void draw(GL2 gl) {
		gl.glTranslatef(-this.x, -this.y, -this.z);
	}
}
