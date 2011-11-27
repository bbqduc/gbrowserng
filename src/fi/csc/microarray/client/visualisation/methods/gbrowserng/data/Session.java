package fi.csc.microarray.client.visualisation.methods.gbrowserng.data;

import java.util.ArrayList;

public class Session {
	
	public ArrayList<Read> reads;
	public ReferenceSequence referenceSequence;
	public HeatMap heatMap;
	
	public Session()
	{
		this.reads = new ArrayList<Read>();
		this.referenceSequence = new ReferenceSequence(200);
		
		for (int i=0; i < 10; ++i)
		{
			this.reads.add(GenomeGenerator.generateRead(this.referenceSequence));
		}
		
		this.heatMap = new HeatMap(this.referenceSequence, this.reads);
	}
}
