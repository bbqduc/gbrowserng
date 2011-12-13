package fi.csc.microarray.client.visualisation.methods.gbrowserng.view.trackview;

import com.soulaim.tech.gles.Color;
import com.soulaim.tech.gles.SoulGL2;
import com.soulaim.tech.gles.renderer.PrimitiveRenderer;
import com.soulaim.tech.gles.renderer.TextRenderer;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.GlobalVariables;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.data.Read;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.data.Session;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces.CascadingComponent;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces.GenosideComponent;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces.VisualComponent;

import java.util.ArrayList;

public class ReadRenderer extends CascadingComponent implements VisualComponent {

    private final ArrayList<Read> reads;
    private final Session session;
    float smoothPosition = 0;

    public ReadRenderer(Session session, ArrayList<Read> reads, CascadingComponent parent) {
        super(parent);
        this.session = session;
        this.reads = reads;
    }

    private void drawRead(SoulGL2 gl, float y, Read read) {
		int intPosition = (int) smoothPosition;
		float offsetPosition = smoothPosition - intPosition;

		// positive direction
		float x = this.session.halfSizeX - offsetPosition * 2 * this.session.halfSizeX;
		for (int i = intPosition - read.position; i < read.genome.length
				&& x < 1f + glxSize(this.session.halfSizeX); ++i, x += 2 * this.session.halfSizeX) {
			if (i < 0)
				continue;

			char c = read.genome[i];
			if (read.snp[i]) {
				PrimitiveRenderer.drawRectangle(glx(x), gly(y),
                        glxSize(session.halfSizeX), glySize(session.halfSizeY), gl,
                        Color.RED);
			}

			PrimitiveRenderer.drawRectangle(glx(x), gly(y),
					glxSize(this.session.halfSizeX * session.payloadSize),
					glySize(this.session.halfSizeY * session.payloadSize), gl, GlobalVariables.genomeColor(c));
			if (this.session.halfSizeX >= this.session.halfSizeY) {
				TextRenderer.getInstance().drawText(gl, Character.toString(c),
						glx(x), gly(y), glySize(20 * this.session.halfSizeY));
			}
		}

		// negative direction
		x = -this.session.halfSizeX - offsetPosition * 2
				* this.session.halfSizeX;
		for (int i = intPosition - read.position - 1; i >= 0
				&& x > -1f - glxSize(this.session.halfSizeX); --i, x -= 2 * this.session.halfSizeX) {
			if (i >= read.genome.length)
				continue;

			char c = read.genome[i];
			if (read.snp[i]) {
				PrimitiveRenderer.drawRectangle(glx(x), gly(y),
						glxSize(session.halfSizeX), glySize(session.halfSizeY), gl,
						Color.RED);
			}

			PrimitiveRenderer.drawRectangle(glx(x), gly(y),
					glxSize(this.session.halfSizeX * session.payloadSize),
					glySize(this.session.halfSizeY * session.payloadSize), gl, GlobalVariables.genomeColor(c));
			if (this.session.halfSizeX >= this.session.halfSizeY) {
				TextRenderer.getInstance().drawText(gl, Character.toString(c),
						glx(x), gly(y), glySize(20 * this.session.halfSizeY));
			}
		}
	}


    public void draw(SoulGL2 gl) {
        float y = 0.0f;
        for (int i = 0; i < this.reads.size(); ++i, y += 2.5f * this.session.halfSizeY) {
            Read read = this.reads.get(i);
            drawRead(gl, y, read);
        }
    }

    public void tick(float dt) {
        // the minimum effort: update window dimensions
        // could also change parameters for drawing, to keep things cool.
        cascadingTick(dt);
        smoothPosition = ((TrackView)getParent()).getGenePosition();
    }
}
