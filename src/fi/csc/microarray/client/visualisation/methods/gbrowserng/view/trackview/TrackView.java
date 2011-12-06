package fi.csc.microarray.client.visualisation.methods.gbrowserng.view.trackview;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.MouseEvent;
import com.soulaim.tech.gles.Color;
import com.soulaim.tech.gles.SoulGL2;
import com.soulaim.tech.gles.renderer.PrimitiveRenderer;
import com.soulaim.tech.gles.renderer.TextRenderer;
import com.soulaim.tech.gles.view.Camera;
import com.soulaim.tech.math.Matrix4;

import fi.csc.microarray.client.visualisation.methods.gbrowserng.data.HeatMap;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.data.Read;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.data.ReferenceSequence;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.data.Session;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces.GenosideComponent;

public class TrackView extends GenosideComponent {

	private Session session;
	private Camera camera;
	private float startY;
	private float halfSize;
	private float halfSizeX;
	private float halfSizeY;
	private float payloadSize;
	
	Matrix4 viewMatrix;
	Matrix4 projectionMatrix;

	public TrackView(GenosideComponent parent, Session session) {
		super(parent);
		this.camera = new Camera();
		this.viewMatrix = new Matrix4();
		this.projectionMatrix = new Matrix4();
		
		this.session = session;
		this.startY = -0.6f;
		this.halfSize = 0.05f;
		this.halfSizeY = this.halfSizeX = this.halfSize;
		this.payloadSize = 0.8f;
	}
	
	public void draw(SoulGL2 gl) {
		
		for (int i = 0; i < this.session.reads.size(); ++i) {
			Read read = this.session.reads.get(i);
			float y = this.startY + (i + 1) * this.halfSize*2;
			drawRead(gl, y, read);
		}
		
		drawRefSeq(gl, this.startY, this.session.referenceSequence);
		drawHeatMap(gl, this.startY - 2*this.halfSize, this.session.heatMap);
		drawCoordinates(gl, this.startY - 4*this.halfSize, this.session.referenceSequence);
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
		float x = 2 * this.halfSizeX * read.position;
		
		for (int i = 0; i < read.genome.length; ++i) {
			char c = read.genome[i];
			if (read.snp[i]) {
				PrimitiveRenderer.drawRectangle(viewMatrix, projectionMatrix, x, y, 0, this.halfSizeX, this.halfSizeY, gl, Color.RED);
			}
			Color genomeColor = genomeColor(c);
			PrimitiveRenderer.drawRectangle(viewMatrix, projectionMatrix, x, y, 0, this.halfSizeX*payloadSize, this.halfSizeY*payloadSize, gl, genomeColor);
			/*
			if (this.sizeX >= this.sizeY)
			{
				float fontSize = sizeY*payloadSize;
				TextRenderer.getInstance().drawText(gl, viewMatrix, projectionMatrix, Character.toString(c), x, y, fontSize, Color.WHITE);
			}
			*/
			x += 2*this.halfSizeX;
		}
	}

	private void drawRefSeq(SoulGL2 gl, float y, ReferenceSequence refSeq) {

		float x = 0.0f;

		for (int i = 0; i < refSeq.sequence.length; ++i) {

			char c = refSeq.sequence[i];
			Color genomeColor = genomeColor(c);
			PrimitiveRenderer.drawRectangle(viewMatrix, projectionMatrix, x, y, 0, this.halfSizeX*payloadSize, this.halfSizeY*payloadSize, gl, genomeColor);
			/*
 			if (this.sizeX >= this.sizeY)
			{
				float fontSize = sizeY*payloadSize;
				TextRenderer.getInstance().drawText(gl, viewMatrix, projectionMatrix, Character.toString(c), x, y, fontSize, Color.WHITE);
			}
			*/
			x += 2*this.halfSizeX;
			
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
			
			PrimitiveRenderer.drawRectangle(viewMatrix, projectionMatrix, x, y, 0, this.halfSizeX*payloadSize, this.halfSizeX*payloadSize, gl, heatColor);

			x += 2*this.halfSizeX;
		}
	}
	
	private void drawCoordinates(SoulGL2 gl, float y, ReferenceSequence refSeq)
	{
		float x = 0.0f;
		int step = 10;
		
		for (int i = 0; i < refSeq.sequence.length; i += step) {
			String pos = Integer.toString(i);
			/*
			TextRenderer.getInstance().drawText(gl, viewMatrix, projectionMatrix, pos, x, y, sizeY, Color.WHITE);
			
			while (sizeX * step < pos.length()*sizeY)
				step += 5;
			x += sizeX * step;
			*/
		}
	}

	@Override
	public boolean handle(MouseEvent event, float screen_x, float screen_y) {
		// TODO Auto-generated method stub
		return false;
	}
	
    @Override
    protected void userTick(float dt) {
    	this.viewMatrix.makeTranslationMatrix(this.camera.getX(), this.camera.getY(), this.camera.getZ());
    }

	@Override
	public boolean handle(KeyEvent event) {
		if (KeyEvent.VK_LEFT == event.getKeyCode())
		{
			this.camera.x += 0.1f;
			return true;
		} else if (KeyEvent.VK_RIGHT == event.getKeyCode()) {
			this.camera.x -= 0.1f;
			return true;
		}
		return false;
	}
}