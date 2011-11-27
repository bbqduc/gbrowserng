package fi.csc.microarray.client.visualisation.methods.gbrowserng.data;

public class ReferenceSequence {
	public final int chromosome;
	public final char[] sequence;
	
	public ReferenceSequence(int size)
	{
		this.chromosome = 1;
		this.sequence = GenomeGenerator.generateSequence(size);
	}
	
	public String toString()
	{
		return "Reference sequence: " + new String(this.sequence);
	}
}
