package fi.csc.microarray.client.visualisation.methods.gbrowserng.data;

import java.util.ArrayList;

public class HeatMap {
	
	public int[] heat;
	public int max;
	
	public HeatMap(ReferenceSequence refSeq, ArrayList<Read> reads)
	{
		this.heat = new int[refSeq.sequence.length];
		this.max = 0;
		
		for (Read read : reads)
		{
			for (int j=0; j < refSeq.sequence.length; ++j)
			{
				if (j >= read.position && j < read.position + read.genome.length)
				{
					++this.heat[j];
					if (this.max < this.heat[j])
						this.max = this.heat[j];
				}
			}
		}
	}
}
