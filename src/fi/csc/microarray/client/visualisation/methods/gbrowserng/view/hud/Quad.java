package fi.csc.microarray.client.visualisation.methods.gbrowserng.view.hud;

import javax.media.opengl.GL2;

import fi.csc.microarray.client.visualisation.methods.gbrowserng.view.TextureHandler;

public class Quad {
	public static void drawQuad(GL2 gl, float bot_x, float bot_y, float top_x,
			float top_y, float z) {
		gl.glBegin(GL2.GL_QUADS);
		gl.glVertex3f(top_x, top_y, z);
		gl.glVertex3f(bot_x, top_y, z);
		gl.glVertex3f(bot_x, bot_y, z);
		gl.glVertex3f(top_x, bot_y, z);
		gl.glEnd();
	}

	public static void drawTexturedQuad(GL2 gl, String textureName, float x,
			float y, float z, float size) {
		TextureHandler.bindTexture(gl, textureName);

		gl.glBegin(GL2.GL_QUADS);
		gl.glVertex3f(x, y, z);
		gl.glTexCoord2f(0.0f, 0.0f);
		gl.glVertex3f(x + size, y, z);
		gl.glTexCoord2f(1.0f, 0.0f);
		gl.glVertex3f(x + size, y - size, z);
		gl.glTexCoord2f(1.0f, 1.0f);
		gl.glVertex3f(x, y - size, z);
		gl.glTexCoord2f(0.0f, 1.0f);
		gl.glEnd();
	}
}
