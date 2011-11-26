package com.soulaim.tech.gles.renderer;

import com.soulaim.tech.gles.Color;
import com.soulaim.tech.gles.SoulGL2;
import com.soulaim.tech.gles.TextureID;
import com.soulaim.tech.gles.primitives.PrimitiveBuffers;
import com.soulaim.tech.gles.shaders.DefaultShaders;
import com.soulaim.tech.math.Matrix4;
import com.soulaim.tech.math.Vector2;

import java.nio.FloatBuffer;

public class PrimitiveRenderer {

    private static float aspectRatio;

    private static Matrix4 modelMatrix = new Matrix4();
    private static Matrix4 projectionMatrix = new Matrix4();
    private static Matrix4 modelviewProjectionMatrix = new Matrix4();

    public static void initialize() {
        aspectRatio = 1.0f;
    }

    public static void onSurfaceChanged(int width, int height) {
        aspectRatio = width * 1.0f / height;

        PrimitiveBuffers.createBuffers();
    }

    public static void drawCube(Matrix4 viewMatrix, Matrix4 projectionMatrix, float x, float y, float z, float scale, SoulGL2 gl, Color color)
    {
        modelMatrix.makeTranslationMatrix(x, y, z);
        modelMatrix.scale(scale, scale, scale);

        modelMatrix.storeMultiplication(viewMatrix, modelMatrix);
        modelviewProjectionMatrix.storeMultiplication(projectionMatrix, modelMatrix);

        PrimitiveBuffers.cubeIndexBuffer.rewind();

        gl.glDisable(SoulGL2.GL_CULL_FACE);
        gl.glEnable(SoulGL2.GL_DEPTH_TEST);
        DefaultShaders.startColorShader3D(gl, color, PrimitiveBuffers.cubeBuffer, modelviewProjectionMatrix);
        gl.glDrawElements(SoulGL2.GL_TRIANGLE_STRIP, PrimitiveBuffers.cubeIndexBuffer.capacity(), SoulGL2.GL_UNSIGNED_SHORT, PrimitiveBuffers.cubeIndexBuffer);
        DefaultShaders.stopColorShader(gl);
        gl.glDisable(SoulGL2.GL_DEPTH_TEST);
        gl.glEnable(SoulGL2.GL_CULL_FACE);
    }

    public static void drawTexturedSquare(float x, float y, float scale, SoulGL2 gl, Color color, TextureID textureID) {
        modelMatrix.makeTranslationMatrix(x,y,0);
        modelMatrix.scale(scale, scale * aspectRatio, 1.0f);
        modelviewProjectionMatrix.storeMultiplication(projectionMatrix, modelMatrix);

        DefaultShaders.startTextureShader(gl, textureID, color, PrimitiveBuffers.squareBuffer, PrimitiveBuffers.squareTextureBuffer, modelviewProjectionMatrix);
        gl.glDrawArrays(SoulGL2.GL_TRIANGLE_STRIP, 0, PrimitiveBuffers.squareBuffer.capacity() / 2);
        DefaultShaders.stopTextureShader(gl);
    }

    public static void drawSquare(Matrix4 viewMatrix, Matrix4 projectionMatrix, float x, float y, float z, float scale, SoulGL2 gl, Color color) {
        drawRectangle(viewMatrix, projectionMatrix, x, y, z, scale, scale, gl, color);
    }

    public static void drawRectangle(Matrix4 viewMatrix, Matrix4 projectionMatrix, float x, float y, float z, float scaleX, float scaleY, SoulGL2 gl, Color color) {
        modelMatrix.makeTranslationMatrix(x, y, z);
        modelMatrix.scale(scaleX, scaleY, 1.0f);
        modelMatrix.storeMultiplication(viewMatrix, modelMatrix);
        modelviewProjectionMatrix.storeMultiplication(projectionMatrix, modelMatrix);

        DefaultShaders.startColorShader(gl, color, PrimitiveBuffers.squareBuffer, modelviewProjectionMatrix);
        gl.glDrawArrays(SoulGL2.GL_TRIANGLE_STRIP, 0, PrimitiveBuffers.squareBuffer.capacity() / 2);
        DefaultShaders.stopColorShader(gl);
    }


    public static void drawSquare(float x, float y, float scale, SoulGL2 gl, Color color) {
        drawRectangle(x, y, scale, scale, gl, color);
    }

    public static void drawRectangle(float x, float y, float scaleX, float scaleY, SoulGL2 gl, Color color) {
        modelMatrix.makeTranslationMatrix(x, y, 0);
        modelMatrix.scale(scaleX, scaleY * aspectRatio, 1.0f);
        modelviewProjectionMatrix.storeMultiplication(projectionMatrix, modelMatrix);

        DefaultShaders.startColorShader(gl, color, PrimitiveBuffers.squareBuffer, modelviewProjectionMatrix);
        gl.glDrawArrays(SoulGL2.GL_TRIANGLE_STRIP, 0, PrimitiveBuffers.squareBuffer.capacity() / 2);
        DefaultShaders.stopColorShader(gl);
    }


    public static void drawCircle(float x, float y, float radius, SoulGL2 gl, Color color) {
        modelMatrix.makeTranslationMatrix(x, y, 0);
        modelMatrix.scale(radius, radius * aspectRatio, 1.0f);
        modelviewProjectionMatrix.storeMultiplication(projectionMatrix, modelMatrix);

        DefaultShaders.startColorShader(gl, color, PrimitiveBuffers.circleBuffer, modelviewProjectionMatrix);
//        gl.glVertexPointer(2, SoulGL2.GL_FLOAT, 0, circleBuffer);
//        gl.glDrawArrays(SoulGL2.GL_LINE_LOOP, 0, circleBuffer.capacity() / 2);
        gl.glDrawArrays(SoulGL2.GL_TRIANGLE_FAN, 0, PrimitiveBuffers.circleBuffer.capacity() / 2);
        DefaultShaders.stopColorShader(gl);
    }

    public static void drawLine(float x1, float y1, float x2, float y2, SoulGL2 gl, Color color) {
        modelviewProjectionMatrix.makeIdentityMatrix();

        FloatBuffer lineBuffer = PrimitiveBuffers.createLineBuffer(x1, y1, x2, y2);

        DefaultShaders.startColorShader(gl, color, lineBuffer, modelviewProjectionMatrix);
        gl.glDrawArrays(SoulGL2.GL_LINES, 0, lineBuffer.capacity() / 2);
        DefaultShaders.stopColorShader(gl);
    }

    public static void drawLine(Matrix4 viewMatrix, Matrix4 projectionMatrix, float x1, float y1, float x2, float y2, SoulGL2 gl, Color color) {
        modelviewProjectionMatrix.storeMultiplication(projectionMatrix, viewMatrix);

        FloatBuffer lineBuffer = PrimitiveBuffers.createLineBuffer(x1, y1, x2, y2);

        DefaultShaders.startColorShader(gl, color, lineBuffer, modelviewProjectionMatrix);
        gl.glDrawArrays(SoulGL2.GL_LINES, 0, lineBuffer.capacity() / 2);
        DefaultShaders.stopColorShader(gl);
    }


    public static void drawCircle(Matrix4 viewMatrix, Matrix4 projectionMatrix, float x, float y, float radius, SoulGL2 gl, Color color) {
        modelMatrix.makeTranslationMatrix(x, y, 0);
        modelMatrix.scale(radius, radius, 1.0f);
        modelMatrix.storeMultiplication(viewMatrix, modelMatrix);
        modelviewProjectionMatrix.storeMultiplication(projectionMatrix, modelMatrix);

        DefaultShaders.startColorShader(gl, color, PrimitiveBuffers.circleBuffer, modelviewProjectionMatrix);
//        gl.glVertexPointer(2, SoulGL2.GL_FLOAT, 0, circleBuffer);
//        gl.glDrawArrays(SoulGL2.GL_LINE_LOOP, 0, circleBuffer.capacity() / 2);
        gl.glDrawArrays(SoulGL2.GL_TRIANGLE_FAN, 0, PrimitiveBuffers.circleBuffer.capacity() / 2);
        DefaultShaders.stopColorShader(gl);
    }

    public static void drawLine(Matrix4 viewMatrix, Matrix4 projectionMatrix, Vector2 a, Vector2 b, SoulGL2 gl, Color color) {
        drawLine(viewMatrix, projectionMatrix, a.x, a.y, b.x, b.y, gl, color);
    }
}
