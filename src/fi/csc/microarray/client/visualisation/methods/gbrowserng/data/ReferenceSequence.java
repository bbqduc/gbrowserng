package fi.csc.microarray.client.visualisation.methods.gbrowserng.data;

public class ReferenceSequence {
	public final int chromosome;
	public final char[] sequence;
	
	public ReferenceSequence(int chromosome, int size) {
		this.chromosome = chromosome;
		this.sequence = GenomeGenerator.generateSequence(size);
	}
	
	public String toString() {
		return "Reference sequence: " + new String(this.sequence);
	}
}
