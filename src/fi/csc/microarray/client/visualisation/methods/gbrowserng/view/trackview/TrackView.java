package fi.csc.microarray.client.visualisation.methods.gbrowserng.view.trackview;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.MouseEvent;
import com.soulaim.tech.gles.Color;
import com.soulaim.tech.gles.SoulGL2;
import com.soulaim.tech.gles.TextureID;
import com.soulaim.tech.gles.renderer.PrimitiveRenderer;
import com.soulaim.tech.gles.renderer.TextRenderer;

import fi.csc.microarray.client.visualisation.methods.gbrowserng.data.HeatMap;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.data.Read;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.data.ReferenceSequence;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.data.Session;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces.GenosideComponent;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.view.common.GenoButton;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.view.common.GenoVisualBorder;

public class TrackView extends GenosideComponent {

	private boolean isActive;
	private GenoButton deleteButton;
	private GenoButton minimizeButton;
	private GenoButton maximizeButton;
	private final GenoVisualBorder borderComponent = new GenoVisualBorder(this);

	private Session session;
	private int position;

	private float startY;
	private float halfSizeX;
	private float targetZoomLevel;

	private float halfSizeY;
	private float payloadSize;

	Color heatColor = new Color(0, 0, 0, 1);

	public TrackView(GenosideComponent parent, Session session) {
		super(parent);

		this.position = 0;
		this.session = session;
		this.startY = -0.7f;
		this.halfSizeY = 0.05f;
		this.targetZoomLevel = this.halfSizeX = 0.05f;
		this.getAnimatedValues().setAnimatedValue("ZOOM", this.targetZoomLevel);
        this.getAnimatedValues().setAnimatedValue("POSITION", this.position);

		this.payloadSize = 0.85f;
		this.isActive = true;
		this.minimizeButton = new GenoButton(this, "MIN_BUTTON", -0.95f, 0.95f,
				TextureID.SHRINK_BUTTON);
		this.deleteButton = new GenoButton(this, "DEL_BUTTON", 0.8f, 0.7f,
				TextureID.QUIT_BUTTON);
		this.maximizeButton = new GenoButton(this, "MAX_BUTTON", -0.8f, 0.7f,
				TextureID.SHRINK_BUTTON);
	}

	public void draw(SoulGL2 gl) {
		if (isActive) {
			float y = this.startY;
			drawCoordinates(gl, y, this.session.referenceSequence);
			y += 2.5f * this.halfSizeY;
			drawHeatMap(gl, y, this.session.heatMap);
			y += 2.5f * this.halfSizeY;
			drawRefSeq(gl, y, this.session.referenceSequence);
			y += 2.5f * this.halfSizeY;

			for (int i = 0; i < this.session.reads.size(); ++i, y += 2.5f * this.halfSizeY) {
				Read read = this.session.reads.get(i);
				drawRead(gl, y, read);
			}

			this.minimizeButton.draw(gl);
		} else {
			this.maximizeButton.draw(gl);
			this.deleteButton.draw(gl);
		}

		borderComponent.draw(gl);
	}

	private Color genomeColor(char c) {
		if (c == 'A')
			return Color.BLUE;
		else if (c == 'G')
			return Color.CYAN;
		else if (c == 'C')
			return Color.ORANGE;
		else
			// (c == 'T')
			return Color.MAGENTA;
	}

	private void drawRead(SoulGL2 gl, float y, Read read) {

        float smoothPosition = this.getAnimatedValues().getAnimatedValue("POSITION");
        int intPosition = (int)smoothPosition;
        float offsetPosition = smoothPosition - intPosition;

		// positive direction
		float x = this.halfSizeX - offsetPosition * 2 * this.halfSizeX;
		for (int i = intPosition - read.position; i < read.genome.length
				&& x < 1.1f; ++i, x += 2 * this.halfSizeX) {
			if (i < 0)
				continue;

			char c = read.genome[i];
			if (read.snp[i]) {
				PrimitiveRenderer.drawRectangle(glx(x), gly(y),
						glxSize(halfSizeX), glySize(halfSizeY), gl, Color.RED);
			}

			PrimitiveRenderer.drawRectangle(glx(x), gly(y),
					glxSize(this.halfSizeX * payloadSize),
					glySize(this.halfSizeY * payloadSize), gl, genomeColor(c));
			if (this.halfSizeX >= this.halfSizeY) {
				TextRenderer.getInstance().drawText(gl, Character.toString(c),
						glx(x), gly(y), glySize(20 * this.halfSizeY));
			}
		}

		// negative direction
		x = -this.halfSizeX - offsetPosition * 2 * this.halfSizeX;
		for (int i = intPosition - read.position - 1; i >= 0 && x > -1.1f; --i, x -= 2 * this.halfSizeX) {
			if (i >= read.genome.length)
				continue;

			char c = read.genome[i];
			if (read.snp[i]) {
				PrimitiveRenderer.drawRectangle(glx(x), gly(y),
						glxSize(halfSizeX), glySize(halfSizeY), gl, Color.RED);
			}

			PrimitiveRenderer.drawRectangle(glx(x), gly(y),
					glxSize(this.halfSizeX * payloadSize),
					glySize(this.halfSizeY * payloadSize), gl, genomeColor(c));
			if (this.halfSizeX >= this.halfSizeY) {
				TextRenderer.getInstance().drawText(gl, Character.toString(c),
						glx(x), gly(y), glySize(20 * this.halfSizeY));
			}
		}
	}

	private void drawRefSeq(SoulGL2 gl, float y, ReferenceSequence refSeq) {

        float smoothPosition = this.getAnimatedValues().getAnimatedValue("POSITION");
        int intPosition = (int)smoothPosition;
        float offsetPosition = smoothPosition - intPosition;

		float x = this.halfSizeX - offsetPosition * 2 * this.halfSizeX;

		// positive direction -->
		for (int i = intPosition; i < refSeq.sequence.length && x < 1.1f; ++i, x += 2 * this.halfSizeX) {
			if (i < 0)
				continue;
			char c = refSeq.sequence[i];
			PrimitiveRenderer.drawRectangle(glx(x), gly(y),
					glxSize(this.halfSizeX * payloadSize),
					glySize(this.halfSizeY * payloadSize), gl, genomeColor(c));
			if (this.halfSizeX >= this.halfSizeY) {
				TextRenderer.getInstance().drawText(gl, Character.toString(c),
						glx(x), gly(y), glySize(20 * this.halfSizeY));
			}

		}

		x = -this.halfSizeX - offsetPosition * 2 * this.halfSizeX;
		// negative direction <--
		for (int i = intPosition - 1; i >= 0 && x > -1.1f; --i) {
			if (i < refSeq.sequence.length) {
				char c = refSeq.sequence[i];
				Color genomeColor = genomeColor(c);
				PrimitiveRenderer.drawRectangle(glx(x), gly(y),
						glxSize(this.halfSizeX * payloadSize),
						glySize(this.halfSizeY * payloadSize), gl, genomeColor);
				if (this.halfSizeX >= this.halfSizeY) {
					TextRenderer.getInstance().drawText(gl,
							Character.toString(c), glx(x), gly(y),
							glySize(20 * this.halfSizeY));
				}
			}
			x -= 2 * this.halfSizeX;
		}
	}

	private void drawHeatMap(SoulGL2 gl, float y, HeatMap heatMap) {

        float smoothPosition = this.getAnimatedValues().getAnimatedValue("POSITION");
        int intPosition = (int)smoothPosition;
        float offsetPosition = smoothPosition - intPosition;

        float x = this.halfSizeX - offsetPosition * 2 * this.halfSizeX;

		for (int i = intPosition; i < heatMap.heat.length && x < 1.1f; ++i, x += 2 * this.halfSizeX) {
			if (i < 0)
				continue;

			float redness = (float) heatMap.heat[i] / (float) heatMap.max;
			float blueness = 1.0f - redness;

			heatColor.r = redness;
			heatColor.b = blueness;

			PrimitiveRenderer.drawRectangle(glx(x), gly(y),
					glxSize(this.halfSizeX * payloadSize),
					glySize(this.halfSizeY * payloadSize), gl, heatColor);
		}

		x = -this.halfSizeX - offsetPosition * 2 * this.halfSizeX;
		for (int i = intPosition - 1; i >= 0 && i < heatMap.heat.length
				&& x > -1.1f; --i, x -= 2 * this.halfSizeX) {

			float redness = (float) heatMap.heat[i] / (float) heatMap.max;
			float blueness = 1.0f - redness;

			heatColor.r = redness;
			heatColor.b = blueness;

			PrimitiveRenderer.drawRectangle(glx(x), gly(y),
					glxSize(this.halfSizeX * payloadSize),
					glySize(this.halfSizeY * payloadSize), gl, heatColor);
		}
	}

	private void drawCoordinates(SoulGL2 gl, float y, ReferenceSequence refSeq) {
		int step = 5;

        float smoothPosition = this.getAnimatedValues().getAnimatedValue("POSITION");
        int intPosition = (int)smoothPosition;
        float offsetPosition = smoothPosition - intPosition;


		float x = this.halfSizeX - offsetPosition * this.halfSizeX * 2;
		for (int i = intPosition; i < refSeq.sequence.length && x < 1.1f; i += step, x += 2 * halfSizeX * step) {
			if (i < 0)
				continue;
			String pos = Integer.toString(i);
			TextRenderer.getInstance().drawText(gl, pos, glx(x), gly(y),
					glySize(this.halfSizeY * 20));
			while (this.halfSizeX*2*step < pos.length()*this.halfSizeY*2)
				++step;
		}

		x = -this.halfSizeX * (1 + 2 * (step-1)) - offsetPosition * this.halfSizeX * 2;
		for (int i = intPosition - step; i >= 0 && x > -1.1f; i -= step, x -= 2 * halfSizeX * step) {
			if (i >= refSeq.sequence.length)
				continue;
			String pos = Integer.toString(i);
			TextRenderer.getInstance().drawText(gl, pos, glx(x), gly(y),
					glySize(this.halfSizeY * 20));
		}
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
			this.halfSizeX = this.getAnimatedValues().getAnimatedValue("ZOOM");
			this.minimizeButton.tick(dt);
		} else {
			this.deleteButton.tick(dt);
		}
	}

	@Override
	public boolean handle(MouseEvent event, float screen_x, float screen_y) {
		if (isActive) {
			return this.minimizeButton.handle(event, screen_x, screen_y);
		} else {
			if (this.deleteButton.handle(event, screen_x, screen_y))
				return true;
			return this.maximizeButton.handle(event, screen_x, screen_y);
		}
	}

	@Override
	public boolean handle(KeyEvent event) {
		if (KeyEvent.VK_LEFT == event.getKeyCode()) {
			this.position -= 0.05f / this.getAnimatedValues().getAnimatedValue("ZOOM");
            this.getAnimatedValues().setAnimatedValue("POSITION", this.position);
			return true;
		} else if (KeyEvent.VK_RIGHT == event.getKeyCode()) {
			this.position += 0.05f / this.getAnimatedValues().getAnimatedValue("ZOOM");
            this.getAnimatedValues().setAnimatedValue("POSITION", this.position);
			return true;
		} else if (KeyEvent.VK_UP == event.getKeyCode()) {
			this.targetZoomLevel *= 0.9f;
			this.getAnimatedValues().setAnimatedValue("ZOOM",
					this.targetZoomLevel);
			return true;
		} else if (KeyEvent.VK_DOWN == event.getKeyCode()) {
			this.targetZoomLevel *= 1.0f / 0.9f;
			this.getAnimatedValues().setAnimatedValue("ZOOM",
					this.targetZoomLevel);
			return true;
		}
		return false;
	}

	public boolean isActive() {
		return this.isActive;
	}
}
