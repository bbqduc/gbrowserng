package fi.csc.microarray.client.visualisation.methods.gbrowserng.view.trackview;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.MouseEvent;
import com.soulaim.tech.gles.Color;
import com.soulaim.tech.gles.SoulGL2;
import com.soulaim.tech.gles.renderer.PrimitiveRenderer;

import fi.csc.microarray.client.visualisation.methods.gbrowserng.data.HeatMap;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.data.Read;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.data.ReferenceSequence;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.data.Session;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces.GenosideHudComponent;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces.GenosideVisualComponent;

public class TrackView implements GenosideHudComponent, GenosideVisualComponent {

	private Session session;
	private float zoom;
	private float y;
	private float sizeY;
	private float sizeX;
	private float baseSize;
	private float borderSizeY;
	private float baseBorderSizeX;
	private float borderSizeX;
	
	private static final float cA[] = {0.5f, 0.2f, 0.2f, 1.0f};
	private static final float cC[] = {0.6f, 1.0f, 0.2f, 1.0f};
	private static final float cG[] = {0.1f, 0.5f, 0.3f, 1.0f};
	private static final float cT[] = {0.2f, 0.1f, 1.0f, 1.0f};

	public TrackView(Session session) {
		this.session = session;
		this.y = -0.6f;
		this.sizeY = 0.08f;
		this.baseSize = 0.1f;
		this.zoom = 1.0f;
		this.borderSizeY = 0.008f;
		this.baseBorderSizeX = 0.008f;
		setZoom(this.zoom);
	}
		
	public void setZoom(float zoom) {
		this.zoom = zoom;
		this.sizeX = this.zoom * this.baseSize;
		this.borderSizeX = this.zoom * this.baseBorderSizeX;
	}

	public void draw(SoulGL2 gl) {
		PrimitiveRenderer.drawRectangle(0.0f, 0.0f, 0.5f, 0.5f, gl, Color.BLUE);
		
		for (int i = 0; i < this.session.reads.size(); ++i) {
			Read read = this.session.reads.get(i);
			float bot_y = this.y + (i + 1) * sizeY;
			drawRead(gl, bot_y, read);
		}
		drawRefSeq(this.y, this.session.referenceSequence);
		drawHeatMap(this.y - sizeY, this.session.heatMap);
		drawCoordinates(this.y - 2*sizeY, this.session.referenceSequence);
	}

	private void drawRead(SoulGL2 gl, float bot_y, Read read) {
		float bot_x = sizeX * read.position;

		for (int i = 0; i < read.genome.length; ++i) {

			char c = read.genome[i];
			if (read.snp[i]) {
				PrimitiveRenderer.drawRectangle(bot_x, bot_y, sizeX, sizeY, gl, Color.BLUE);
				//GL11.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
				//Quad.drawQuad(bot_x, bot_y, bot_x + sizeX, bot_y + sizeY, 0.0f);
			}
			/*
			if (c == 'A')
				GL11.glColor4f(cA[0], cA[1], cA[2], cA[3]);
			else if (c == 'G')
				GL11.glColor4f(cG[0], cG[1], cG[2], cG[3]);
			else if (c == 'C')
				GL11.glColor4f(cC[0], cC[1], cC[2], cC[3]);
			else
				GL11.glColor4f(cT[0], cT[1], cT[2], cT[3]);

			Quad.drawQuad(bot_x + borderSizeX, bot_y + borderSizeY, bot_x
					+ sizeX - borderSizeX, bot_y + sizeY - borderSizeY, 0.0f);
			if (this.sizeX >= this.sizeY)
			{
				float posX = bot_x + borderSizeX + (sizeX - 2*borderSizeX)/2 - sizeY/2;
				float posY = bot_y + borderSizeY;
				float fontSize = sizeY - 2*borderSizeY;
				TextRenderer.drawACharacter(c, posX, posY, 0.0f, fontSize);
			}
			bot_x += sizeX;
			*/
		}
	}

	private void drawRefSeq(float bot_y, ReferenceSequence refSeq) {
		/*
		float bot_x = 0.0f;

		for (int i = 0; i < refSeq.sequence.length; ++i) {

			char c = refSeq.sequence[i];

			if (c == 'A')
				GL11.glColor4f(cA[0], cA[1], cA[2], cA[3]);
			else if (c == 'G')
				GL11.glColor4f(cG[0], cG[1], cG[2], cG[3]);
			else if (c == 'C')
				GL11.glColor4f(cC[0], cC[1], cC[2], cC[3]);
			else
				GL11.glColor4f(cT[0], cT[1], cT[2], cT[3]);

			Quad.drawQuad(bot_x + borderSizeX, bot_y + borderSizeY, bot_x
					+ sizeX - borderSizeX, bot_y + sizeY - borderSizeY, 0.0f);
			
			if (this.sizeX >= this.sizeY)
			{
				float posX = bot_x + borderSizeX + (sizeX - 2*borderSizeX)/2 - sizeY/2;
				float posY = bot_y + borderSizeY;
				float fontSize = sizeY - 2*borderSizeY;
				TextRenderer.drawACharacter(c, posX, posY, 0.0f, fontSize);
			}
			bot_x += sizeX;
		}
		*/
	}

	private void drawHeatMap(float bot_y, HeatMap heatMap) {
		/*
		float bot_x = 0.0f;

		for (int i = 0; i < heatMap.heat.length; ++i) {
			float redness = (float) heatMap.heat[i] / (float) heatMap.max;
			float blueness = 1.0f - redness;
			
			GL11.glColor4f(redness, 0.0f, blueness, 1.0f);

			Quad.drawQuad(bot_x + borderSizeX, bot_y + borderSizeY, bot_x
					+ sizeX - borderSizeX, bot_y + sizeY - borderSizeY, 0.0f);
			bot_x += sizeX;
		}
		*/
	}
	
	private void drawCoordinates(float bot_y, ReferenceSequence refSeq)
	{
		/*
		float bot_x = borderSizeX + (sizeX - 2*borderSizeX)/2 - sizeY/2;
		int step = 10;
		
		for (int i = 0; i < refSeq.sequence.length; i += step) {
			String pos = Integer.toString(i);
			
			GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
			TextRenderer.drawString(pos, bot_x, bot_y, 0.0f, sizeY);
			
			while (sizeX * step < pos.length()*sizeY)
				step += 5;
			bot_x += sizeX * step;	
		}
		*/
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
