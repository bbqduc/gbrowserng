package com.soulaim.tech.gles.renderer;

import com.soulaim.tech.gles.Color;
import com.soulaim.tech.gles.Font;
import com.soulaim.tech.gles.SoulGL2;
import com.soulaim.tech.gles.TextureID;
import com.soulaim.tech.managers.ShaderManager;
import com.soulaim.tech.gles.shaders.Shader;
import com.soulaim.tech.managers.TextureManager;
import com.soulaim.tech.math.Matrix4;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class TextRenderer {

    public static int MAX_TEXT_LENGTH = 100;

    private FloatBuffer textBuffer;
    private FloatBuffer textTextureBuffer;
    private float aspectRatio;
    private Matrix4 modelMatrix = new Matrix4();
    private Matrix4 modelviewProjectionMatrix = new Matrix4();


    private static TextRenderer singleton;

    public static TextRenderer getInstance() {
        return singleton;
    }

    public static void createInstance() {
        singleton = new TextRenderer();
    }


    private TextRenderer() {
    }

    public void onSurfaceChanged(int w, int h) {
        this.aspectRatio = (float)w / h;

        allocateTextBuffer();
        allocateTextTextureBuffer();
    }

    private void allocateTextBuffer() {
        ByteBuffer buffer = ByteBuffer.allocateDirect(4 * 6 * MAX_TEXT_LENGTH);
        buffer.order(ByteOrder.nativeOrder());
        textBuffer = buffer.asFloatBuffer();
    }

    private void allocateTextTextureBuffer() {
        ByteBuffer buffer = ByteBuffer.allocateDirect(4 * 6 * MAX_TEXT_LENGTH);
        buffer.order(ByteOrder.nativeOrder());
        textTextureBuffer = buffer.asFloatBuffer();
    }

    public void drawText(SoulGL2 gl, String text, float x, float y, float scale) {
        modelviewProjectionMatrix.makeIdentityMatrix();

        fillTextBuffers(text, x, y, scale);
        TextureManager.bindTexture(gl, TextureID.FONT);
        drawTextBuffers(gl, text.length());
    }

    public void drawDebugText(SoulGL2 gl, String text, float x, float y, float scale) {
        modelviewProjectionMatrix.makeIdentityMatrix();
                
        fillTextBuffers(text, x, y, scale);
        TextureManager.bindTexture(gl, TextureID.DEFAULT);
        drawTextBuffers(gl, text.length());
    }

    private void drawTextBuffers(SoulGL2 gl, int textLength) {

        int coordinatesPerVertex = 2;

        Shader shader = ShaderManager.getProgram(ShaderManager.ShaderID.DEFAULT_TEXTURED);
        shader.start(gl);

        int textureCoordinateHandle = shader.getAttribLocation(gl, "textureCoordinate");
        int vertexPositionHandle = shader.getAttribLocation(gl, "vertexPosition");
        int MVPMatrixHandle = shader.getUniformLocation(gl, "MVPMatrix");

        gl.glEnableVertexAttribArray(textureCoordinateHandle);
        gl.glVertexAttribPointer(textureCoordinateHandle, 2, SoulGL2.GL_FLOAT, false, 0, textTextureBuffer);

        gl.glEnable(SoulGL2.GL_BLEND);
        gl.glBlendFunc(SoulGL2.GL_SRC_ALPHA, SoulGL2.GL_ONE_MINUS_SRC_ALPHA);

        gl.glUniformMatrix4fv(MVPMatrixHandle, 1, false, modelviewProjectionMatrix.data, 0);

        gl.glEnableVertexAttribArray(vertexPositionHandle);
        gl.glVertexAttribPointer(vertexPositionHandle, coordinatesPerVertex, SoulGL2.GL_FLOAT, false, 0, textBuffer);

        gl.glDrawArrays(SoulGL2.GL_TRIANGLES, 0, 2 * 3 * textLength);

        gl.glDisable(SoulGL2.GL_BLEND);
        gl.glDisableVertexAttribArray(textureCoordinateHandle);
        gl.glDisableVertexAttribArray(vertexPositionHandle);

        shader.stop(gl);
    }

    private void fillTextBuffers(String text, float x, float y, float scale) {
        scale *= 0.1f;
        textBuffer.rewind();
        textTextureBuffer.rewind();

        float height = aspectRatio * scale;

        float halfTextLength = 0f;
        for (int i = 0; i < text.length(); ++i) {
            halfTextLength += Font.width(text.charAt(i)) * 0.5f * scale;
        }

        for (int i = 0; i < text.length(); ++i) {
            char c = text.charAt(i);
            float charWidth = Font.width(c);

            fillCoordinates(textBuffer, x - halfTextLength, y - height * 0.5f, charWidth * scale, height);
            fillTextureCoordinates(c, textTextureBuffer);

            x += charWidth * scale;
            //y -= 0.05f;
        }
        textBuffer.rewind();
        textTextureBuffer.rewind();
    }

    private void fillCoordinates(FloatBuffer buffer, float x, float y, float charWidth, float charHeight) {
        buffer.put(x            ); buffer.put(y);
        buffer.put(x + charWidth); buffer.put(y);
        buffer.put(x + charWidth); buffer.put(y + charHeight);

        buffer.put(x + charWidth); buffer.put(y + charHeight);
        buffer.put(x            ); buffer.put(y + charHeight);
        buffer.put(x            ); buffer.put(y);
    }

    private void fillTextureCoordinates(char c, FloatBuffer buffer) {
        int x = Font.getColumn(c);
        int y = Font.getRow(c);
        float charWidth = Font.width(c);
        float dx = (1.0f - charWidth) / 2.0f;

        float sizeX = 1.0f / Font.getRows();
        float sizeY = 1.0f / Font.getColumns();

        buffer.put((x + 0 + dx) * sizeX); buffer.put((y + 1) * sizeY);
        buffer.put((x + 1 - dx) * sizeX); buffer.put((y + 1) * sizeY);
        buffer.put((x + 1 - dx) * sizeX); buffer.put((y + 0) * sizeY);

        buffer.put((x + 1 - dx) * sizeX); buffer.put((y + 0) * sizeY);
        buffer.put((x + 0 + dx) * sizeX); buffer.put((y + 0) * sizeY);
        buffer.put((x + 0 + dx) * sizeX); buffer.put((y + 1) * sizeY);
    }

    public void drawText(SoulGL2 gl, Matrix4 viewMatrix, Matrix4 projectionMatrix, String text, float x, float y, float scale, Color color) {
        fillTextBuffers(text, 0, 0, scale);
        TextureManager.bindTexture(gl, TextureID.FONT);

        modelMatrix.makeTranslationMatrix(x, y, 0);
        modelMatrix.scale(scale, scale, 1.0f);
        modelMatrix.storeMultiplication(viewMatrix, modelMatrix);
        modelviewProjectionMatrix.storeMultiplication(projectionMatrix, modelMatrix);

        int textLength = text.length();

        int coordinatesPerVertex = 2;

        Shader shader = ShaderManager.getProgram(ShaderManager.ShaderID.DEFAULT_COLOREDTEXTURED);
        shader.start(gl);

        int textureCoordinateHandle = shader.getAttribLocation(gl, "textureCoordinate");
        int vertexPositionHandle = shader.getAttribLocation(gl, "vertexPosition");
        int MVPMatrixHandle = shader.getUniformLocation(gl, "MVPMatrix");
        int colorHandle = shader.getUniformLocation(gl, "color");

        gl.glEnableVertexAttribArray(textureCoordinateHandle);
        gl.glVertexAttribPointer(textureCoordinateHandle, 2, SoulGL2.GL_FLOAT, false, 0, textTextureBuffer);

        gl.glEnable(SoulGL2.GL_BLEND);
        gl.glBlendFunc(SoulGL2.GL_SRC_ALPHA, SoulGL2.GL_ONE_MINUS_SRC_ALPHA);

        gl.glUniformMatrix4fv(MVPMatrixHandle, 1, false, modelviewProjectionMatrix.data, 0);
        gl.glUniform4f(colorHandle, color.r, color.g, color.b, color.a);

        gl.glEnableVertexAttribArray(vertexPositionHandle);
        gl.glVertexAttribPointer(vertexPositionHandle, coordinatesPerVertex, SoulGL2.GL_FLOAT, false, 0, textBuffer);

        gl.glDrawArrays(SoulGL2.GL_TRIANGLES, 0, 2 * 3 * textLength);

        gl.glDisable(SoulGL2.GL_BLEND);
        gl.glDisableVertexAttribArray(textureCoordinateHandle);
        gl.glDisableVertexAttribArray(vertexPositionHandle);

        shader.stop(gl);
    }
}