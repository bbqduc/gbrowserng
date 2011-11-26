package fi.csc.microarray.client.visualisation.methods.gbrowserng.model;

import java.util.ArrayList;

public class Session {
	
	public ArrayList<GeneTrack> geneTracks;
	
	public Session()
	{
		this.geneTracks = new ArrayList<GeneTrack>();
		for (int i = 0; i < 5; ++i)
		{
			this.geneTracks.add(new GeneTrack(200));
		}
	}
}
