package fi.csc.microarray.client.visualisation.methods.gbrowserng.data;

import java.util.Random;

public class GenomeGenerator {
	public static final Random rnd = new Random();
	public static final String genomeLetters = "AGCT";
	
	public static char[] generateSequence(int size) {
		char[] seq = new char[size];
		for (int i = 0; i < size; ++i)
			seq[i] = getRandomGenomeLetter();
		return seq;
	}
	
	public static char getRandomGenomeLetter()
	{
		return genomeLetters.charAt(Math.abs(rnd.nextInt()) % genomeLetters.length());
	}
	
	public static Read generateRead(ReferenceSequence referenceSequence)
	{
		int start = Math.abs(rnd.nextInt()) % referenceSequence.sequence.length;
		int end = start + (Math.abs(rnd.nextInt()) % (referenceSequence.sequence.length - start));
		int len = end-start;
		char[] readSeq = new char[len];
		boolean[] snp = new boolean[len];
		
		for (int i = 0; i < len; ++i)
		{
			if (rnd.nextFloat() > 0.1f)
				readSeq[i] = referenceSequence.sequence[i+start];
			else
				readSeq[i] = getRandomGenomeLetter();
			snp[i] = readSeq[i] != referenceSequence.sequence[i+start];
		}
		
		return new Read(referenceSequence.chromosome, start, readSeq, snp);
	}

}
