package fi.csc.microarray.client.visualisation.methods.gbrowserng.view.trackview;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.MouseEvent;
import com.soulaim.tech.gles.Color;
import com.soulaim.tech.gles.SoulGL2;
import com.soulaim.tech.gles.renderer.PrimitiveRenderer;
import com.soulaim.tech.gles.renderer.TextRenderer;

import fi.csc.microarray.client.visualisation.methods.gbrowserng.data.HeatMap;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.data.Read;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.data.ReferenceSequence;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.data.Session;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces.GenosideHudComponent;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces.GenosideVisualComponent;

public class TrackView implements GenosideHudComponent, GenosideVisualComponent {

	private Session session;
	private float zoom;
	private float startY;
	private float sizeY;
	private float sizeX;
	private float baseSize;
	private float borderSizeY;
	private float baseBorderSizeX;
	private float borderSizeX;

	public TrackView(Session session) {
		this.session = session;
		this.startY = -0.6f;
		this.sizeY = 0.08f;
		this.baseSize = 0.1f;
		this.zoom = 1.0f;
		this.borderSizeY = 0.028f;
		this.baseBorderSizeX = 0.028f;
		setZoom(zoom);
	}
		
	public void setZoom(float zoom) {
		this.zoom = zoom;
		this.sizeX = this.zoom * this.baseSize;
		this.borderSizeX = this.zoom * this.baseBorderSizeX;
	}

	public void draw(SoulGL2 gl) {
		
		for (int i = 0; i < this.session.reads.size(); ++i) {
			Read read = this.session.reads.get(i);
			float y = this.startY + (i + 1) * sizeY;
			drawRead(gl, y, read);
		}
		
		drawRefSeq(gl, this.startY, this.session.referenceSequence);
		drawHeatMap(gl, this.startY - sizeY, this.session.heatMap);
		drawCoordinates(gl, this.startY - 2*sizeY, this.session.referenceSequence);
	}
	
	private Color genomeColor(char c)
	{
		if (c == 'A')
			return Color.BLUE;
		else if (c == 'G')
			return Color.CYAN;
		else if (c == 'C')
			return Color.ORANGE;
		else // (c == 'T')
			return Color.MAGENTA;
	}

	private void drawRead(SoulGL2 gl, float y, Read read) {
		float x = sizeX * read.position;
		
		for (int i = 0; i < read.genome.length; ++i) {
			char c = read.genome[i];
			if (read.snp[i]) {
				PrimitiveRenderer.drawRectangle(x, y, sizeX, sizeY, gl, Color.RED);
			}
			Color genomeColor = genomeColor(c);
			PrimitiveRenderer.drawRectangle(x, y, sizeX - 2*borderSizeX, sizeY - 2*borderSizeY, gl, genomeColor);

			if (this.sizeX >= this.sizeY)
			{
				float fontSize = sizeY - 2*borderSizeY;
				TextRenderer.getInstance().drawText(gl, Character.toString(c), x, y, fontSize);
			}
			x += sizeX;
		}
	}

	private void drawRefSeq(SoulGL2 gl, float y, ReferenceSequence refSeq) {

		float x = 0.0f;

		for (int i = 0; i < refSeq.sequence.length; ++i) {

			char c = refSeq.sequence[i];
			Color genomeColor = genomeColor(c);
			PrimitiveRenderer.drawRectangle(x, y, sizeX - 2*borderSizeX, sizeY - 2*borderSizeY, gl, genomeColor);
			
			if (this.sizeX >= this.sizeY)
			{
				float fontSize = sizeY - 2*borderSizeY;
				TextRenderer.getInstance().drawText(gl, Character.toString(c), x, y, fontSize);
			}
			x += sizeX;
		}
	}

	private void drawHeatMap(SoulGL2 gl, float y, HeatMap heatMap) {
		float x = 0.0f;

		for (int i = 0; i < heatMap.heat.length; ++i) {
			float redness = (float) heatMap.heat[i] / (float) heatMap.max;
			float blueness = 1.0f - redness;
			
			Color heatColor = Color.BLUE;
			if (redness > blueness)
				heatColor = Color.RED;

			PrimitiveRenderer.drawRectangle(x, y, sizeX - 2*borderSizeX, sizeY - 2*borderSizeY, gl, heatColor);
			x += sizeX;
		}
	}
	
	private void drawCoordinates(SoulGL2 gl, float y, ReferenceSequence refSeq)
	{
		//float bot_x = borderSizeX + (sizeX - 2*borderSizeX)/2 - sizeY/2;
		float x = 0.0f;
		int step = 10;
		
		for (int i = 0; i < refSeq.sequence.length; i += step) {
			String pos = Integer.toString(i);
			
			TextRenderer.getInstance().drawText(gl, pos, x, y, sizeY);
			
			while (sizeX * step < pos.length()*sizeY)
				step += 5;
			x += sizeX * step;
		}
	}
	
	@Override
	public void tick(float dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean handle(MouseEvent event, float screen_x, float screen_y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean handle(KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
