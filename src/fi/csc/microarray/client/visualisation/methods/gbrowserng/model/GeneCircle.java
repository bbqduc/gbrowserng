package fi.csc.microarray.client.visualisation.methods.gbrowserng.model;

import fi.csc.microarray.client.visualisation.methods.gbrowserng.data.AbstractChromosome;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.data.AbstractGenome;

public class GeneCircle {
    
    private float minimumChromosomeSlice = 0.08f; // TODO should check that this is not too big
    
    private AbstractChromosome chromosome = AbstractGenome.getChromosome(0);
    private long chromosomePosition = 0;
    private float[] chromosomeBoundaries;
    
    public GeneCircle(){
        chromosomeBoundaries = new float[AbstractGenome.getNumChromosomes()+1];
        float sliceSizeLeft = 1.0f - AbstractGenome.getNumChromosomes() * minimumChromosomeSlice;
        assert(sliceSizeLeft >= 0);
        
        chromosomeBoundaries[0] = 0;
        for(int i = 1; i <= AbstractGenome.getNumChromosomes(); ++i)
            chromosomeBoundaries[i] = chromosomeBoundaries[i-1] + minimumChromosomeSlice + sliceSizeLeft * AbstractGenome.getChromosome(i-1).length()/AbstractGenome.getTotalLength();
        }
        
    public void updatePosition(float pointerGenePosition) {
        // TODO : fix this for something better than linear?
        for(int i=1; i<chromosomeBoundaries.length; ++i)
            if(chromosomeBoundaries[i] >= pointerGenePosition) {
                chromosome = AbstractGenome.getChromosome(i-1);
                chromosomePosition = (long) (getChromosome().length() * (pointerGenePosition - chromosomeBoundaries[i-1]));
                break;
            }        
    }

    public AbstractChromosome getChromosome() {
        return chromosome;
    }

    public long getChromosomePosition() {
        return chromosomePosition;
    }
    
    public float[] getChromosomeBoundaries() {
        return chromosomeBoundaries;
    }
    
 }
