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

    TrackSession trackSession;
    ReadRenderer readRenderer;
    HeatMapRenderer heatMapRenderer;
    ReferenceRenderer referenceRenderer;

	private boolean isActive;
	private boolean heatMapMode;
	private GenoButton deleteButton;
	private GenoButton minimizeButton;
	private GenoButton maximizeButton;
	private final GenoVisualBorder borderComponent = new GenoVisualBorder(this);

	private Session session;

    public float getGenePosition() {
        return this.getParent().getAnimatedValues().getAnimatedValue("POSITION");
    }
    
    public float getHalfWidth() {
        return this.getParent().getAnimatedValues().getAnimatedValue("ZOOM");
    }
    
    public Session getSession() {
    	return this.session;
    }

	public TrackView(GenosideComponent parent, Session session) {
		super(parent);
		
		this.session = session;

        referenceRenderer = new ReferenceRenderer(this, session);
        trackSession = new TrackSession(session.referenceSequence);
        readRenderer = new ReadRenderer(session, trackSession.getReads(), this);
        heatMapRenderer = new HeatMapRenderer(this, trackSession.getHeatMap());

        this.readRenderer.setDimensions(2, 2);
        this.heatMapRenderer.setDimensions(2, 2);

		this.isActive = true;
		this.minimizeButton = new GenoButton(this, "MIN_BUTTON", -1.0f, 1.0f,
				+0.04f, -0.04f, TextureID.SHRINK_BUTTON);
		this.deleteButton = new GenoButton(this, "DEL_BUTTON", -1.0f, 1.0f,
				+0.04f, -0.04f, TextureID.QUIT_BUTTON);
		this.maximizeButton = new GenoButton(this, "MAX_BUTTON", -1.0f, 1.0f,
				+0.04f, -0.09f, TextureID.MAXIMIZE_BUTTON);
	}

	public void draw(SoulGL2 gl) {

        if(!inScreen())
            return;

		if (isActive) {
			if (!heatMapMode)
			{
	            this.referenceRenderer.draw(gl);
	            this.readRenderer.draw(gl);
				this.minimizeButton.draw(gl);
			}
			else
			{
				this.heatMapRenderer.draw(gl);
				this.maximizeButton.draw(gl);
				this.deleteButton.draw(gl);
			}
		} else {
			this.maximizeButton.draw(gl);
			this.deleteButton.draw(gl);
		}

		borderComponent.draw(gl);
	}

	@Override
	public void childComponentCall(String who, String what) {
		if (who.equals("MIN_BUTTON")) {
			isActive = false;
			this.getParent().childComponentCall("TRACKVIEW", "MINIMIZE");
		} else if (who.equals("MAX_BUTTON")) {
			isActive = true;
			this.getParent().childComponentCall("MAXIMIZE", Integer.toString(this.getId()));
		} else if (who.equals("DEL_BUTTON")) {
			this.getParent().childComponentCall("DELETE", Integer.toString(this.getId()));
		}
	}

	@Override
	public void userTick(float dt) {
		if (isActive) {
			if (!heatMapMode)
			{
	            this.referenceRenderer.tick(dt);
	            this.readRenderer.tick(dt);
				this.minimizeButton.tick(dt);
			}
			else
			{
				this.heatMapRenderer.tick(dt);
				this.deleteButton.tick(dt);
				this.maximizeButton.tick(dt);
			}
		} else {
			this.deleteButton.tick(dt);
			this.maximizeButton.tick(dt);
		}
	}

	@Override
	public boolean handle(MouseEvent event, float screen_x, float screen_y) {

		if (isActive) {
			if (this.heatMapMode)
			{
				if (this.deleteButton.handle(event, screen_x, screen_y))
					return true;
				return this.maximizeButton.handle(event, screen_x, screen_y);
			} else {
				if (this.minimizeButton.handle(event, screen_x, screen_y))
					return true;
				return false;
			}
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
	
	public void setHeatMapMode(boolean heatMapMode)
	{
		this.heatMapMode = heatMapMode;
	}

	public boolean isActive() {
		return this.isActive;
	}
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
}
