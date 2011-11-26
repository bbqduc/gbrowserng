package fi.csc.microarray.client.visualisation.methods.gbrowserng.model;

import java.util.Random;

public class GeneTrack {
	public static char geneChars[] = { 'A', 'G', 'T', 'C' };
	public static Random randomGen = new Random(42);
	
	public char[] genome;
	
	public GeneTrack(int len)
	{
		generateGenome(len);
	}
	
	private void generateGenome(int len)
	{
		this.genome = new char[len];
		for (int i = 0; i < this.genome.length; ++i)
		{
			this.genome[i] = geneChars[GeneTrack.randomGen.nextInt(geneChars.length)];
		}
	}
}
