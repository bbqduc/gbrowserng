package fi.csc.microarray.client.visualisation.methods.gbrowserng.view.trackview;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.MouseEvent;
import com.soulaim.tech.gles.SoulGL2;
import com.soulaim.tech.gles.TextureID;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.data.Session;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.data.TrackSession;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces.GenosideComponent;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.view.common.GenoButton;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.view.common.GenoVisualBorder;

public class TrackView extends GenosideComponent {

    private TrackSession trackSession;
    private ReadRenderer readRenderer;
    private HeatMapRenderer heatMapRenderer;
    private ReferenceRenderer referenceRenderer;
    
	private GenoButton deleteButton;
	private GenoButton minimizeButton;
	private GenoButton maximizeButton;
	
	private int trackViewMode;
	
	public final static int MINIMIZED = 1;
	public final static int HEATMAP = 2;
	public final static int READ = 4;
	
	private final GenoVisualBorder borderComponent = new GenoVisualBorder(this);

	private Session session;

	public TrackView(GenosideComponent parent, Session session) {
		super(parent);
		
		this.session = session;

        referenceRenderer = new ReferenceRenderer(this, session);
        trackSession = new TrackSession(session.referenceSequence);
        readRenderer = new ReadRenderer(session, trackSession.getReads(), this);
        heatMapRenderer = new HeatMapRenderer(this, trackSession.getHeatMap());

        this.readRenderer.setDimensions(2, 2);
        this.heatMapRenderer.setDimensions(2, 2);

		this.minimizeButton = new GenoButton(this, "MIN_BUTTON", -1.0f, 1.0f,
				+0.04f, -0.04f, TextureID.SHRINK_BUTTON);
		this.deleteButton = new GenoButton(this, "DEL_BUTTON", -1.0f, 1.0f,
				+0.04f, -0.04f, TextureID.QUIT_BUTTON);
		this.maximizeButton = new GenoButton(this, "MAX_BUTTON", -1.0f, 1.0f,
				+0.04f, -0.09f, TextureID.MAXIMIZE_BUTTON);
		
		this.trackViewMode = TrackView.READ;
	}
	
    public float getGenePosition() {
        return this.getParent().getAnimatedValues().getAnimatedValue("POSITION");
    }
    
    public float getHalfWidth() {
        return this.getParent().getAnimatedValues().getAnimatedValue("ZOOM");
    }
    
    public Session getSession() {
    	return this.session;
    }

	public void draw(SoulGL2 gl) {
		switch (this.trackViewMode) {
			case TrackView.MINIMIZED:
				this.maximizeButton.draw(gl);
				this.deleteButton.draw(gl);
				break;
				
			case TrackView.HEATMAP:
				this.heatMapRenderer.draw(gl);
				this.maximizeButton.draw(gl);
				this.deleteButton.draw(gl);
				break;
				
			case TrackView.READ:
	            this.referenceRenderer.draw(gl);
	            this.readRenderer.draw(gl);
				this.minimizeButton.draw(gl);
				break;
		}
		
		this.borderComponent.draw(gl);
	}

	@Override
	public void childComponentCall(String who, String what) {
		if (who.equals("MIN_BUTTON")) {
			this.trackViewMode = TrackView.MINIMIZED;
			this.getParent().childComponentCall("TRACKVIEW", "MINIMIZE");
		} else if (who.equals("MAX_BUTTON")) {
			this.trackViewMode = TrackView.READ;
			this.getParent().childComponentCall("MAXIMIZE", Integer.toString(this.getId()));
		} else if (who.equals("DEL_BUTTON")) {
			this.getParent().childComponentCall("DELETE", Integer.toString(this.getId()));
		}
	}

	@Override
	public void userTick(float dt) {
		switch (this.trackViewMode)
		{
			case TrackView.HEATMAP:
				this.heatMapRenderer.tick(dt);
				this.deleteButton.tick(dt);
				this.maximizeButton.tick(dt);
				break;
				
			case TrackView.MINIMIZED:
				this.deleteButton.tick(dt);
				this.maximizeButton.tick(dt);
				break;
				
			case TrackView.READ:
	            this.referenceRenderer.tick(dt);
	            this.readRenderer.tick(dt);
				this.minimizeButton.tick(dt);
				break;
				
			default:
				throw new RuntimeException("Invalid TrackViewMode");
		}
	}

	@Override
	public boolean handle(MouseEvent event, float screen_x, float screen_y) {
		switch (this.trackViewMode)
		{
			case TrackView.MINIMIZED:
				if (this.deleteButton.handle(event, screen_x, screen_y))
					return true;
				return this.maximizeButton.handle(event, screen_x, screen_y);
				
			case TrackView.READ:
				if (this.minimizeButton.handle(event, screen_x, screen_y))
					return true;
				return false;
				
			case TrackView.HEATMAP:
				if (this.deleteButton.handle(event, screen_x, screen_y))
					return true;
				return this.maximizeButton.handle(event, screen_x, screen_y);
				
			default:
				return false;
		}
	}

	@Override
	public boolean handle(KeyEvent event) {
		return false;
	}
	
	public void setTrackViewMode(int mode) {
		this.trackViewMode = mode;
		/*
		switch (mode)
		{
			case TrackView.MINIMIZED:
			case TrackView.HEATMAP:
			case TrackView.READ:
			break;
		}
		*/
	}
	
	public boolean isActive()
	{
		return this.trackViewMode != TrackView.MINIMIZED;
	}
	
	public int getTrackViewMode()
	{
		return this.trackViewMode;
	}
}
