package fi.csc.microarray.client.visualisation.methods.gbrowserng.model;

public class GeneTrackSelection {
	public int start;
	public int end;
	
	public GeneTrackSelection()
	{
		this.start = this.end = -1;
	}
	
	public GeneTrackSelection(int start, int end)
	{
		this.start = start;
		this.end = end;
	}
	
	public void reset()
	{
		this.start = this.end = -1;
	}
	
	private void swapEnds()
	{
		int tmp = this.end;
		this.end = this.start;
		this.start = tmp;
	}
	
	public void update(int pos)
	{
		if (this.start < 0)
		{
			this.start = pos;
		}
		else if (this.end < 0)
		{
			this.end = pos;
			if (this.start > this.end)
				swapEnds();
		}
		else
		{
			reset();
		}
		
	}
	
	public String toString()
	{
		return "selection: " + this.start + " - " + this.end;
	}
}
