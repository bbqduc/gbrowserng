package fi.csc.microarray.client.visualisation.methods.gbrowserng.view;

import java.util.ArrayList;

import javax.media.opengl.GL2;

import fi.csc.microarray.client.visualisation.methods.gbrowserng.controller.GenoEvent;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.model.Session;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.view.track.GeneTrackView;

public class SessionView {
	
	private ArrayList<GeneTrackView> geneTrackViews;
	
	public SessionView(Session session)
	{
		this.geneTrackViews = new ArrayList<GeneTrackView>();
		for (int i = 0; i < session.geneTracks.size(); ++i)
		{
			this.geneTrackViews.add(new GeneTrackView(session.geneTracks.get(i), i));
		}
	}
	
	public void update(GenoEvent e)
	{
		for (GeneTrackView trackView : this.geneTrackViews)
		{
			trackView.update(e);
		}
	}
	
	public void draw(GL2 gl)
	{
		for (GeneTrackView trackView : this.geneTrackViews)
		{
			trackView.draw(gl);
		}
	}
}
