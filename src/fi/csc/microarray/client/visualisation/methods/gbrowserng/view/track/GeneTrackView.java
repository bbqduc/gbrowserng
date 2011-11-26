package fi.csc.microarray.client.visualisation.methods.gbrowserng.view.track;

import javax.media.opengl.GL2;

import com.jogamp.newt.event.MouseEvent;

import fi.csc.microarray.client.visualisation.methods.gbrowserng.controller.GenoEvent;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.model.GeneTrack;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.model.Selection;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.view.hud.TextRenderer;

public class GeneTrackView {

	private static float glTrackHeight = 0.25f;
	private static float glFontSize = 0.2f;
	private int trackNum;

	private final GeneTrack track;
	private Selection selection = new Selection();
	private int mouseOverIndex = -1;
	private boolean selected = false;
	
	private float botLeftY;

	public GeneTrackView(GeneTrack track, int trackNum)
	{
		this.track = track;
		this.trackNum = trackNum;
		this.botLeftY = 0.5f - glTrackHeight * this.trackNum;
	}

	public void update(GenoEvent e) {
		if (e.event instanceof MouseEvent) {
			MouseEvent mouseEvent = (MouseEvent) e.event;
			float mouseX = e.getMouseGLX();
			float mouseY = e.getMouseGLY();

			this.mouseOverIndex = (int) ((Math.abs(-1.0f) + mouseX) / glFontSize);
			this.selected = mouseOverMe(mouseY);

			if (mouseEvent.getEventType() == MouseEvent.EVENT_MOUSE_CLICKED
					&& this.selected
					&& mouseEvent.getButton() == MouseEvent.BUTTON1) {
				this.selection.update(this.mouseOverIndex);
			}
		}
	}

	public void draw(GL2 gl) {
		float glXPos = -1.0f;

		for (int i = 0; i < this.track.genome.length; ++i)
		{
			if (this.selected && this.mouseOverIndex == i)
				gl.glColor4f(0.2f, 0.2f, 0.2f, 0.5f);
			else if (this.selection.start <= i && i <= this.selection.end)
				gl.glColor4f(0.6f, 0.2f, 0.2f, 0.5f);
			else
				gl.glColor4f(0.1f, 0.1f, 0.1f, 0.1f);

			TextRenderer.drawACharacter(gl, this.track.genome[i], glXPos,
					this.botLeftY, 0.0f, glFontSize);
			glXPos += glFontSize;
		}
	}

	private boolean mouseOverMe(float mouseY) {
		return mouseY < -glTrackHeight * (this.trackNum - 1.0f)
				&& mouseY > -glTrackHeight * this.trackNum;
	}

}
