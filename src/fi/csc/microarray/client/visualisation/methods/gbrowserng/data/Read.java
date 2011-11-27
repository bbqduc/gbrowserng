package fi.csc.microarray.client.visualisation.methods.gbrowserng.data;

public class Read {
	public int chromosome;
	public int position;
	public char genome[];
	public boolean snp[];
	
	public Read(int chromosome, int position, char genome[], boolean snp[])
	{
		this.chromosome = chromosome;
		this.position = position;
		this.genome = genome;
		this.snp = snp;
	}

	public String toString()
	{
		return "Read: " + new String(this.genome);
	}
}
