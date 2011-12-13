package fi.csc.microarray.client.visualisation.methods.gbrowserng.view.trackview;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.MouseEvent;
import com.soulaim.tech.gles.Color;
import com.soulaim.tech.gles.SoulGL2;
import com.soulaim.tech.gles.TextureID;
import com.soulaim.tech.gles.renderer.PrimitiveRenderer;
import com.soulaim.tech.gles.renderer.TextRenderer;

import fi.csc.microarray.client.visualisation.methods.gbrowserng.GlobalVariables;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.data.*;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces.GenosideComponent;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.view.common.GenoButton;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.view.common.GenoVisualBorder;

import java.util.ArrayList;

public class TrackView extends GenosideComponent {

    TrackSession trackSession;
    ReadRenderer readRenderer;
    ReferenceRenderer referenceRenderer;

	private boolean isActive;
	private GenoButton deleteButton;
	private GenoButton minimizeButton;
	private GenoButton maximizeButton;
	private final GenoVisualBorder borderComponent = new GenoVisualBorder(this);

	private Session session;

	Color heatColor = new Color(0, 0, 0, 1);


    public float getGenePosition() {
        return this.getParent().getAnimatedValues().getAnimatedValue("POSITION");
    }

	public TrackView(GenosideComponent parent, Session session) {
		super(parent);

        referenceRenderer = new ReferenceRenderer(this, session);
        trackSession = new TrackSession(session.referenceSequence);
        readRenderer = new ReadRenderer(session, trackSession.getReads(), this);

        this.readRenderer.setDimensions(2, 2);
		this.session = session;

		this.isActive = true;
		this.minimizeButton = new GenoButton(this, "MIN_BUTTON", -1.0f, 1.0f,
				+0.04f, -0.04f, TextureID.SHRINK_BUTTON);
		this.deleteButton = new GenoButton(this, "DEL_BUTTON", -1.0f, 1.0f,
				+0.04f, -0.04f, TextureID.QUIT_BUTTON);
		this.maximizeButton = new GenoButton(this, "MAX_BUTTON", -1.0f, 1.0f,
				+0.04f, -0.09f, TextureID.MAXIMIZE_BUTTON);
	}

	public void draw(SoulGL2 gl) {
		if (isActive) {
			float y = this.session.startY;
			drawCoordinates(gl, y, this.session.referenceSequence);
			y += 2.5f * this.session.halfSizeY;
			drawHeatMap(gl, y, this.trackSession.getHeatMap());
			y += 2.5f * this.session.halfSizeY;

            this.referenceRenderer.draw(gl);
            this.readRenderer.draw(gl);
			this.minimizeButton.draw(gl);
		} else {
			this.maximizeButton.draw(gl);
			this.deleteButton.draw(gl);
		}

		borderComponent.draw(gl);
	}

	private void drawHeatMap(SoulGL2 gl, float y, HeatMap heatMap) {

		float smoothPosition = getGenePosition();
		int intPosition = (int) smoothPosition;
		float offsetPosition = smoothPosition - intPosition;

		float x = this.session.halfSizeX - offsetPosition * 2
				* this.session.halfSizeX;

		for (int i = intPosition; i < heatMap.heat.length
				&& x < 1f + glxSize(this.session.halfSizeX); ++i, x += 2 * this.session.halfSizeX) {
			if (i < 0)
				continue;

			float redness = (float) heatMap.heat[i] / (float) heatMap.max;
			float blueness = 1.0f - redness;

			heatColor.r = redness;
			heatColor.b = blueness;

			PrimitiveRenderer.drawRectangle(glx(x), gly(y),
					glxSize(this.session.halfSizeX * session.payloadSize),
					glySize(this.session.halfSizeY * session.payloadSize), gl, heatColor);
		}

		x = -this.session.halfSizeX - offsetPosition * 2 * this.session.halfSizeX;
		for (int i = intPosition - 1; i >= 0 && x > -1f - glxSize(this.session.halfSizeX); --i, x -= 2 * this.session.halfSizeX) {
			if (i >= heatMap.heat.length)
				continue;

			float redness = (float) heatMap.heat[i] / (float) heatMap.max;
			float blueness = 1.0f - redness;

			heatColor.r = redness;
			heatColor.b = blueness;

			PrimitiveRenderer.drawRectangle(glx(x), gly(y),
					glxSize(this.session.halfSizeX * session.payloadSize),
					glySize(this.session.halfSizeY * session.payloadSize), gl, heatColor);
		}
	}

	private void drawCoordinates(SoulGL2 gl, float y, ReferenceSequence refSeq) {
		/*
		int step = 5;

		float smoothPosition = getGenePosition();
		int intPosition = (int) smoothPosition;
		float offsetPosition = smoothPosition - intPosition;

		float x = this.session.halfSizeX - offsetPosition
				* this.session.halfSizeX * 2;
		for (int i = intPosition; i < refSeq.sequence.length
				&& x < 1f + glxSize(this.session.halfSizeX); i += step, x += 2
				* session.halfSizeX * step) {
			if (i < 0)
				continue;
			String pos = Integer.toString(i);
			TextRenderer.getInstance().drawText(gl, pos, glx(x), gly(y),
					glySize(this.session.halfSizeY * 20));
			while (this.session.halfSizeX * 2 * step < pos.length()
					* this.session.halfSizeY * 2)
				++step;
		}

		x = -this.session.halfSizeX * (1 + 2 * (step - 1)) - offsetPosition
				* this.session.halfSizeX * 2;
		for (int i = intPosition - step; i >= 0
				&& x > -1f - glxSize(this.session.halfSizeX); i -= step, x -= 2
				* session.halfSizeX * step) {
			if (i >= refSeq.sequence.length)
				continue;
			String pos = Integer.toString(i);
			TextRenderer.getInstance().drawText(gl, pos, glx(x), gly(y),
					glySize(this.session.halfSizeY * 20));
		}
				*/
	}

	@Override
	public void childComponentCall(String who, String what) {
		if (who.equals("MIN_BUTTON")) {
			isActive = false;
			this.getParent().childComponentCall("TRACKVIEW", "MINIMIZE");
		} else if (who.equals("MAX_BUTTON")) {
			isActive = true;
			this.getParent().childComponentCall("TRACKVIEW", "MAXIMIZE");
		}
	}

	@Override
	public void userTick(float dt) {
		if (isActive) {
            this.referenceRenderer.tick(dt);
            this.readRenderer.tick(dt);
			this.minimizeButton.tick(dt);
		} else {
			this.deleteButton.tick(dt);
			this.maximizeButton.tick(dt);
		}
	}

	@Override
	public boolean handle(MouseEvent event, float screen_x, float screen_y) {

		if (isActive) {
			if (this.minimizeButton.handle(event, screen_x, screen_y))
				return true;
			return false;
		} else {
			if (this.deleteButton.handle(event, screen_x, screen_y))
				return true;
			return this.maximizeButton.handle(event, screen_x, screen_y);
		}
	}

	@Override
	public boolean handle(KeyEvent event) {
		return false;
	}

	public boolean isActive() {
		return this.isActive;
	}
}
