package fi.csc.microarray.client.visualisation.methods.gbrowserng.data;

import fi.csc.microarray.client.visualisation.methods.gbrowserng.model.GeneTrack;

public class AbstractChromosome {
    private long size;
    private int id;
    GeneTrack geneTrack;

    public AbstractChromosome(int id, long size) {
        this.id = id;
        this.size = size;
        geneTrack = new GeneTrack((int)size);
    }

    public long length() {
        return size;
    }

    public GeneTrack getGeneTrack() {
        return geneTrack;
    }
}
