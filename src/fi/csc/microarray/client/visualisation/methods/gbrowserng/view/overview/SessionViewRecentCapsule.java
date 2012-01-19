package fi.csc.microarray.client.visualisation.methods.gbrowserng.view.overview;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.MouseEvent;
import com.soulaim.tech.gles.Color;
import com.soulaim.tech.gles.SoulGL2;
import com.soulaim.tech.gles.renderer.PrimitiveRenderer;
import com.soulaim.tech.math.Vector2;

import fi.csc.microarray.client.visualisation.methods.gbrowserng.GlobalVariables;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.data.Session;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.interfaces.GenosideComponent;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.view.trackview.SessionView;

public class SessionViewRecentCapsule extends GenosideComponent {
	
	private int			Position;
	private Session		session;
	private SessionView sessionView;

	public SessionViewRecentCapsule(int position, SessionView sessionview, Session session) {
        super(null);
        this.Position=position;
        this.session=session;
        this.sessionView = sessionview;
	}

	public SessionView getSession()
	{
		return this.sessionView;
	}
	@Override
	public void childComponentCall(String who, String what) {}

	@Override
	public boolean handle(MouseEvent event, float screen_x, float screen_y) {
        Vector2 dimensions = sessionView.getDimensions();
        Vector2 position = sessionView.getPosition();

        if(screen_x > position.x - dimensions.x * 0.5f && screen_x < position.x + dimensions.x * 0.5f)
        {
            if(screen_y > position.y - dimensions.y * 0.5f && screen_y < position.y + dimensions.y * 0.5f) {
                	this.getAnimatedValues().setAnimatedValue("MOUSEHOVER", 1);
                return true;
            }
        }
        this.getAnimatedValues().setAnimatedValue("MOUSEHOVER", 0);
        return false;
	}

	@Override
	public boolean handle(KeyEvent event) {
		return false;
	}

	@Override
	public void draw(SoulGL2 gl) {
		float r=this.getAnimatedValues().getAnimatedValue("MOUSEHOVER");
		Color c=new Color(r,r,r);
		PrimitiveRenderer.drawRectangle(this.sessionView.getPosition().x, this.sessionView.getPosition().y, 0.05f, 0.05f/GlobalVariables.aspectRatio, gl, c);
		sessionView.draw(gl);
	}

	@Override
	public void userTick(float dt) {
		Vector2 dimensions=this.sessionView.getDimensions();
		this.sessionView.setPosition(-1.0f+(dimensions.x * 0.5f)+(Position*0.125f), 1.0f-0.05f);
		sessionView.tick(dt);
	}

}
