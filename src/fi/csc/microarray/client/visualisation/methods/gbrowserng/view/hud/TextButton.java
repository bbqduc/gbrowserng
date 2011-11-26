package fi.csc.microarray.client.visualisation.methods.gbrowserng.view.hud;

import javax.media.opengl.GL2;

public class TextButton {

	private String text;
	private float botLeftX;
	private float botLeftY;
	private float topRightY;
	private float topRightX;
	private float textBotY;
	private float textBotX;
	private float textSize;
	private float z;
	private boolean mouseOver;

	public TextButton(String text, float botLeftX, float botLeftY,
			float topRightX, float topRightY, float z) {
		this.text = text;
		this.botLeftX = botLeftX;
		this.botLeftY = botLeftY;
		this.topRightX = topRightX;
		this.topRightY = topRightY;
		this.z = z;
		this.mouseOver = false;
		positionText();
	}

	private final void positionText() {
		float deltaY = this.topRightY - this.botLeftY;
		float deltaX = this.topRightX - this.botLeftX;

		this.textSize = Math.min(deltaX / this.text.length(), deltaY);
		this.textBotX = this.botLeftX
				+ (deltaX - this.textSize * this.text.length()) / 2;
		this.textBotY = this.botLeftY + (deltaY - this.textSize) / 2;
	}

	public void update(float mouseX, float mouseY) {
		this.mouseOver = this.botLeftX <= mouseX && mouseX <= this.topRightX
				&& this.botLeftY <= mouseY && mouseY <= this.topRightY;
	}

	public void draw(GL2 gl) {
		if (this.mouseOver) {
			gl.glColor4f(0.1f, 0.1f, 0.1f, 0.1f);
		} else {
			gl.glColor4f(0.5f, 0.1f, 0.1f, 0.1f);
		}

		Quad.drawQuad(gl, this.botLeftX, this.botLeftY, this.topRightX,
				this.topRightY, this.z);

		gl.glColor4f(0.5f, 0.5f, 0.5f, 0.5f);
		TextRenderer.drawString(gl, this.text, this.textBotX, this.textBotY,
				this.z, this.textSize);
	}
}
