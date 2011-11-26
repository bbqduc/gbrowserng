package fi.csc.microarray.client.visualisation.methods.gbrowserng.view.hud;

import javax.media.opengl.GL2;

import fi.csc.microarray.client.visualisation.methods.gbrowserng.view.TextureHandler;

public class TextRenderer {
	static String fontTextureName = "FONT";
	
	static {
		TextureHandler.loadTexture(fontTextureName, "resources/images/font.bmp");
	}

	public static void drawString(GL2 gl, String s, float x, float y, float z, float size) {
		gl.glEnable(GL2.GL_BLEND);
		gl.glEnable(GL2.GL_TEXTURE_2D);
		TextureHandler.bindTexture(gl, fontTextureName);
		for (int i = 0; i < s.length(); ++i, x += size) {
			gl.glColor4f(1.0f, 1.0f, 1.0f, 0.5f);
			drawCharacter(gl, s.charAt(i), x, y, z, size);
		}
		gl.glDisable(GL2.GL_TEXTURE_2D);
		gl.glDisable(GL2.GL_BLEND);
	}

	private static void drawCharacter(GL2 gl, char c, float x, float y, float z, float size) {
		c -= 32;

		float char_size = 0.0625f;
		float char_y = (float) (c / 16) / 16.0f;
		float char_x = (float) (c % 16) / 16.0f;

		gl.glBegin(GL2.GL_QUADS);
			gl.glTexCoord2f(char_x, char_y + char_size); /* Bottom Left */
			gl.glVertex3f(x, y, z);
	
			gl.glTexCoord2f(char_x + char_size, char_y + char_size); /* Bottom Right */
			gl.glVertex3f(x + size, y, z);
	
			gl.glTexCoord2f(char_x + char_size, char_y); /* Top Right */
			gl.glVertex3f(x + size, y + size, z);
	
			gl.glTexCoord2f(char_x, char_y); /* Top Left */
			gl.glVertex3f(x, y + size, z);
		gl.glEnd();
	}
	
	public static void drawACharacter(GL2 gl, char c, float x, float y, float z, float size) {
		gl.glBlendFunc(GL2.GL_ONE, GL2.GL_ONE);
		gl.glEnable(GL2.GL_BLEND);
		
		Quad.drawQuad(gl, x, y, x + size, y + size, z);
		
		gl.glEnable(GL2.GL_TEXTURE_2D);
		
		gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		drawCharacter(gl, c, x, y, z, size);
		
		gl.glDisable(GL2.GL_TEXTURE_2D);
		gl.glDisable(GL2.GL_BLEND);
	}
	
}
