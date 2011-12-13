package fi.csc.microarray.client.visualisation.methods.gbrowserng.data;

import java.util.ArrayList;

public class Session {

	public ReferenceSequence referenceSequence;
    public float position = 0;

    public float targetZoomLevel = 0.05f;
    public float halfSizeX = 0.05f;

    public float startY = -0.7f;
	public float halfSizeY = 0.05f;
	public float payloadSize = 0.85f;

    public Session() {
		this.referenceSequence = new ReferenceSequence(1, 200);
	}
}

