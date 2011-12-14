package fi.csc.microarray.client.visualisation.methods.gbrowserng.view.trackview;

import com.soulaim.tech.gles.Color;
import com.soulaim.tech.gles.SoulGL2;
import com.soulaim.tech.gles.renderer.PrimitiveRenderer;

import fi.csc.microarray.client.visualisation.methods.gbrowserng.data.HeatMap;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.data.Session;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces.CascadingComponent;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces.GenosideComponent;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces.VisualComponent;

public class HeatMapRenderer extends CascadingComponent implements VisualComponent {
	
	private final Session session;
	private HeatMap heatMap;
	private Color heatColor;

	public HeatMapRenderer(TrackView trackView, HeatMap heatMap) {
		super(trackView);
		this.session = trackView.getSession();
		this.heatColor = new Color(0, 0, 0, 1);
		this.heatMap = heatMap;
	}

	@Override
	public void draw(SoulGL2 gl) {
		float smoothPosition = ((TrackView)getParent()).getGenePosition();
		float halfWidth = this.session.halfSizeX;
		float height = this.session.startY + 2.5f * this.session.halfSizeY;
		
		int intPosition = (int) smoothPosition;
		float offsetPosition = smoothPosition - intPosition;

		float x = halfWidth - offsetPosition * 2 * halfWidth;

		for (int i = intPosition; i < heatMap.heat.length
				&& x < 1f + glxSize(halfWidth); ++i, x += 2 * halfWidth) {
			if (i < 0)
				continue;

			float redness = (float) heatMap.heat[i] / (float) heatMap.max;
			float blueness = 1.0f - redness;

			heatColor.r = redness;
			heatColor.b = blueness;

			PrimitiveRenderer.drawRectangle(glx(x), gly(height),
					glxSize(this.session.halfSizeX * session.payloadSize),
					glySize(this.session.halfSizeY * session.payloadSize), gl, heatColor);
		}

		x = -halfWidth - offsetPosition * 2 * halfWidth;
		for (int i = intPosition - 1; i >= 0 && x > -1f - glxSize(halfWidth); --i, x -= 2 * halfWidth) {
			if (i >= heatMap.heat.length)
				continue;

			float redness = (float) heatMap.heat[i] / (float) heatMap.max;
			float blueness = 1.0f - redness;

			heatColor.r = redness;
			heatColor.b = blueness;

			PrimitiveRenderer.drawRectangle(glx(x), gly(height),
					glxSize(this.session.halfSizeX * session.payloadSize),
					glySize(this.session.halfSizeY * session.payloadSize), gl, heatColor);
		}
		
	}

	@Override
	public void tick(float dt) {
		cascadingTick(dt);
	}
}
