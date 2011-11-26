package com.soulaim.tech.gles.primitives;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class ColoredVisualQuad extends TexturedVisualQuad {
    private float r;
    private float g;
    private float b;
    private float alpha;

    public ColoredVisualQuad(float bottomX, float bottomY, float topX, float topY) {
        super(bottomX, bottomY, topX, topY);

		r = g = b = alpha = 1.0f;
		this.updateColor();
    }

    public void setColor(float r, float g, float b)
    {
        this.r = r;
        this.g = g;
        this.b = b;

        updateColor();
    }

    private void updateColor()
    {
        colorBuffer.rewind();
        float color[] = {r, g, b, alpha};
        colorBuffer.put(color);
        colorBuffer.put(color);
        colorBuffer.put(color);
        colorBuffer.put(color);
        colorBuffer.rewind();
    }

    public void setColor(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        alpha = a;
        updateColor();
    }

    @Override
    public void initBuffers() {
        super.initBuffers();

        ByteBuffer cbb = ByteBuffer.allocateDirect(1 * 4 * 4 * 4);
        cbb.order(ByteOrder.nativeOrder());
        colorBuffer = cbb.asFloatBuffer();
    }

    @Override
    public FloatBuffer getColors() {
        return colorBuffer;
    }

    @Override
    public FloatBuffer getTexCoords() {
        return texBuffer;
    }
}
