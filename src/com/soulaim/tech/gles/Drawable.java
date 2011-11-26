package com.soulaim.tech.gles;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public interface Drawable
{
    public ShortBuffer getIndices();
    public FloatBuffer getVertices();
	public FloatBuffer getTexCoords();
	public FloatBuffer getColors();
}
