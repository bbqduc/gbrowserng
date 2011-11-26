package com.soulaim.tech.gles.primitives;

import com.soulaim.tech.gles.Drawable;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class Mesh implements Drawable {

    protected ShortBuffer indexBuffer;
	protected FloatBuffer vertexBuffer;
	protected FloatBuffer texBuffer;

    public Mesh(int vertices, int indices) {
        this.reserve(vertices, indices);
    }

    private void reserve(int vertices, int indices) {
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices * 4 * 3);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();

        ByteBuffer tbb = ByteBuffer.allocateDirect(vertices * 4 * 2);
        tbb.order(ByteOrder.nativeOrder());
        texBuffer = tbb.asFloatBuffer();

        ByteBuffer ibb = ByteBuffer.allocateDirect(indices * 2);
        ibb.order(ByteOrder.nativeOrder());
        indexBuffer = ibb.asShortBuffer();
    }

    public void putVertex(float x, float y, float z) {
        vertexBuffer.put(x);
        vertexBuffer.put(y);
        vertexBuffer.put(z);
    }

    public void putUVCoord(float u, float v) {
        texBuffer.put(u);
        texBuffer.put(v);
    }

    public void putTriangleIndices(int i1, int i2, int i3) {
        indexBuffer.put((short)i1);
        indexBuffer.put((short)i2);
        indexBuffer.put((short)i3);
    }

    public ShortBuffer getIndices() {
        return indexBuffer;
    }

    public FloatBuffer getVertices() {
        return vertexBuffer;
    }

    public FloatBuffer getTexCoords() {
        return texBuffer;
    }

	public FloatBuffer getColors() {
        throw new UnsupportedOperationException("This should never be called");
    }

    public void rewind() {
        indexBuffer.rewind();
        vertexBuffer.rewind();
        texBuffer.rewind();
    }

}