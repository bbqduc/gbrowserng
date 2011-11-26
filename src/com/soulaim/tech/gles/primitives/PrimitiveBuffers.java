package com.soulaim.tech.gles.primitives;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class PrimitiveBuffers {
    public static FloatBuffer circleBuffer;
    public static FloatBuffer circleTextureBuffer;

    public static FloatBuffer squareBuffer;
    public static FloatBuffer squareTextureBuffer;
    public static FloatBuffer pointBuffer;

    public static FloatBuffer cubeBuffer;
    public static ShortBuffer cubeIndexBuffer;
    public static FloatBuffer cubeTextureBuffer; // ..?

    public static FloatBuffer lineBuffer;

    public static FloatBuffer allocateFloats(int floats) {
        ByteBuffer buffer = ByteBuffer.allocateDirect(4 * floats);
        buffer.order(ByteOrder.nativeOrder());
        return buffer.asFloatBuffer();
    }

    private static void createPointBuffer() {
        pointBuffer = allocateFloats(2);
        pointBuffer.put(0);
        pointBuffer.put(0);
    }

    private static final int circlePoints = 32;

    private static void createCircleBuffer() {
        circleBuffer = allocateFloats((circlePoints + 2) * 2);

        circleBuffer.put(0);
        circleBuffer.put(0);
        for (int i = 0; i < circlePoints + 1; ++i) {
            float x = -(float) Math.sin(2 * Math.PI * i / circlePoints);
            float y = (float) Math.cos(2 * Math.PI * i / circlePoints);

            circleBuffer.put(x);
            circleBuffer.put(y);
        }

        createCircleTextureBuffer();
    }

    private static void createCircleTextureBuffer() {
        circleTextureBuffer = allocateFloats((circlePoints + 2) * 2);

        circleTextureBuffer.put(0.5f);
        circleTextureBuffer.put(0.5f);
        for (int i = 0; i < circlePoints + 1; ++i) {
            float x = -(float) Math.sin(2 * Math.PI * i / circlePoints);
            float y = (float) Math.cos(2 * Math.PI * i / circlePoints);

            circleTextureBuffer.put(x * 0.5f + 0.5f);
            circleTextureBuffer.put(y * -0.5f + 0.5f);
        }
    }

    private static void createSquareBuffer() {
        squareBuffer = allocateFloats(4 * 2);
        squareBuffer.put(-1); squareBuffer.put(-1);
        squareBuffer.put(1);  squareBuffer.put(-1);
        squareBuffer.put(-1); squareBuffer.put(1);
        squareBuffer.put(1);  squareBuffer.put(1);
    }

    private static void allocateLineBuffer() {
        lineBuffer = allocateFloats(2 * 2);
    }

    private static void putVertex3f(FloatBuffer b, float x, float y, float z) {
        b.put(x);
        b.put(y);
        b.put(z);
    }

    private static void putShort(ShortBuffer b, int val) {
        b.put((short)val);
    }

    private static void createCubeBuffers() {
        cubeBuffer = allocateFloats(8 * 3);
        cubeIndexBuffer = allocateShorts(14);

        // 0, 1, 2, 3, 7, 1, 5, 4, 7, 6, 2, 4, 0, 1
        putShort(cubeIndexBuffer, 0);
        putShort(cubeIndexBuffer, 1);
        putShort(cubeIndexBuffer, 2);
        putShort(cubeIndexBuffer, 3);
        putShort(cubeIndexBuffer, 7);
        putShort(cubeIndexBuffer, 1);
        putShort(cubeIndexBuffer, 5);
        putShort(cubeIndexBuffer, 4);
        putShort(cubeIndexBuffer, 7);
        putShort(cubeIndexBuffer, 6);
        putShort(cubeIndexBuffer, 2);
        putShort(cubeIndexBuffer, 4);
        putShort(cubeIndexBuffer, 0);
        putShort(cubeIndexBuffer, 1);



        putVertex3f(cubeBuffer, -1, -1, -1);
        putVertex3f(cubeBuffer, +1, -1, -1);
        putVertex3f(cubeBuffer, -1, +1, -1);
        putVertex3f(cubeBuffer, +1, +1, -1);

        putVertex3f(cubeBuffer, -1, -1, +1);
        putVertex3f(cubeBuffer, +1, -1, +1);
        putVertex3f(cubeBuffer, -1, +1, +1);
        putVertex3f(cubeBuffer, +1, +1, +1);
    }

    public static ShortBuffer allocateShorts(int shorts) {
        ByteBuffer buffer = ByteBuffer.allocateDirect(shorts * 2);
        buffer.order(ByteOrder.nativeOrder());
        return buffer.asShortBuffer();
    }

    private static void createSquareTextureBuffer() {
        squareTextureBuffer = allocateFloats(4 * 2);
        squareTextureBuffer.put(0);
        squareTextureBuffer.put(1);
        squareTextureBuffer.put(1);
        squareTextureBuffer.put(1);
        squareTextureBuffer.put(0);
        squareTextureBuffer.put(0);
        squareTextureBuffer.put(1);
        squareTextureBuffer.put(0);
    }

    /**
     * Creates a floatbuffer that contains the coordinates of a line segment. The buffer is
     * valid until this method is called again.
     *
     * @param x1 X-coordinate of the starting point of the line.
     * @param y1 Y-coordinate of the starting point of the line.
     * @param x2 X-coordinate of the ending point of the line.
     * @param y2 Y-coordinate of the ending point of the line.
     * @return The buffer that contains the coordinate of the line.
     */
    public static FloatBuffer createLineBuffer(float x1, float y1, float x2, float y2) {
        assert lineBuffer.capacity() == 4;
        lineBuffer.rewind();
        lineBuffer.put(x1);
        lineBuffer.put(y1);
        lineBuffer.put(x2);
        lineBuffer.put(y2);
        lineBuffer.rewind();
        return lineBuffer;
    }

    public static void createBuffers() {
        createCircleBuffer();
        createSquareBuffer();
        createSquareTextureBuffer();
        createPointBuffer();
        createCubeBuffers();
        allocateLineBuffer();
    }

    public static void releaseBuffers() {
        pointBuffer = null;
        circleBuffer = null;
        squareBuffer = null;
        squareTextureBuffer = null;
    }
}
