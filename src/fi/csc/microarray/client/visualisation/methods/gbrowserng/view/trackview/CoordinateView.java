package fi.csc.microarray.client.visualisation.methods.gbrowserng.view.trackview;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.MouseEvent;
import com.soulaim.tech.gles.SoulGL2;

import fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces.GenosideComponent;

public class CoordinateView extends GenosideComponent {
	
	private int start;
	private int end;

	public CoordinateView(GenosideComponent parent) {
		super(parent);
		this.start = this.end = 0;
	}

	public void update(int start, int end)
	{
		this.start = start;
		this.end = end;
	}
	
	@Override
	public void childComponentCall(String who, String what) {	
	}

	@Override
	public void draw(SoulGL2 gl) {
	}

	@Override
	public boolean handle(MouseEvent event, float screenX, float screenY) {
		return false;
	}

	@Override
	public boolean handle(KeyEvent event) {
		return false;
	}

	@Override
	public void userTick(float dt) {
	}

}
