package com.soulaim.tech.gles.primitives;

import com.soulaim.tech.gles.Drawable;
import com.soulaim.tech.math.Vector2;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class VisualQuad implements Drawable {

    protected ShortBuffer indexBuffer;
	protected FloatBuffer vertexBuffer;
	protected FloatBuffer texBuffer;
    protected FloatBuffer colorBuffer;


    /**
     * VisualQuads have constant z-coordinate, i,e., they are drawn on a 2D-plane.
     */
    private static final float PLANE_Z = 0.0f;

    // TODO: these should be protected.
    public float rotation;
    public float bx;
    public float by;
    public float tx;
    public float ty;
    public float target_x;
    public float target_y;

    protected Vector2 base;
    protected Vector2 offset;

    public VisualQuad(float top_y, float bottom_x, float top_x, float bottom_y) {
        ty = top_y;
        target_y = (ty - by) * 0.5f;
        base = new Vector2();
        bx = bottom_x;
        tx = top_x;
        target_x = (tx - bx) * 0.5f;
        offset = new Vector2();
        rotation = 0.0f;
        by = bottom_y;

        this.initBuffers();
		this.updatePosition();
    }

    // Move quaD into its place with fluent motion.
    public void approach(float percentage)
    {
        float distx = (target_x - (tx + bx) * 0.5f);
        float disty = (target_y - (ty + by) * 0.5f);

        float dx = distx * percentage;
        float dy = disty * percentage;

        tx += dx;
        bx += dx;
        ty += dy;
        by += dy;

        updatePosition();
    }

    public void updatePosition()
	{
		vertexBuffer.rewind();
		base.x = bx + (tx - bx) * 0.5f;
		base.y = by + (ty - by) * 0.5f;

		float hx = (tx - bx) * 0.5f;
		float hy = (ty - by) * 0.5f;

		offset.x = +hx;
		offset.y = +hy;
		putVertex(base, offset);

		offset.x = +hx;
		offset.y = -hy;
		putVertex(base, offset);

		offset.x = -hx;
		offset.y = -hy;
		putVertex(base, offset);

		offset.x = -hx;
		offset.y = +hy;
		putVertex(base, offset);
		vertexBuffer.rewind();
	}

    public void initBuffers()
    {
        ByteBuffer vbb = ByteBuffer.allocateDirect(1 * 4 * 3 * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();

        ByteBuffer tbb = ByteBuffer.allocateDirect(1 * 4 * 2 * 4);
        tbb.order(ByteOrder.nativeOrder());
        texBuffer = tbb.asFloatBuffer();

        ByteBuffer ibb = ByteBuffer.allocateDirect(1 * 6 * 2);
        ibb.order(ByteOrder.nativeOrder());
        indexBuffer = ibb.asShortBuffer();

        indexBuffer.rewind();
        indexBuffer.put((short) 0);
        indexBuffer.put((short) 2);
        indexBuffer.put((short) 1);

        indexBuffer.put((short) 0);
        indexBuffer.put((short) 3);
        indexBuffer.put((short) 2);
        indexBuffer.rewind();
    }

    private void putVertex(Vector2 base, Vector2 offset)
    {
        offset.rotate(rotation);

        vertexBuffer.put(base.x + offset.x);
        vertexBuffer.put(base.y + offset.y);
        vertexBuffer.put(PLANE_Z);
    }

    public ShortBuffer getIndices() {
        return indexBuffer;
    }

    public FloatBuffer getVertices() {
        return vertexBuffer;
    }

    public FloatBuffer getTexCoords() {
        throw new UnsupportedOperationException("This should never be called");
    }
	public FloatBuffer getColors() {
        throw new UnsupportedOperationException("This should never be called");
    }
}
