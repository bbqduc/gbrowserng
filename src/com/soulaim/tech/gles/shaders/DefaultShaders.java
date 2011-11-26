package com.soulaim.tech.gles.shaders;

import com.soulaim.tech.gles.Color;
import com.soulaim.tech.gles.SoulGL2;
import com.soulaim.tech.gles.TextureID;
import com.soulaim.tech.managers.ShaderManager;
import com.soulaim.tech.managers.TextureManager;
import com.soulaim.tech.math.Matrix4;

import java.nio.FloatBuffer;

public class DefaultShaders {
    public static void startTextureShader3D(SoulGL2 gl, TextureID textureID, Color color, FloatBuffer vertexBuffer, FloatBuffer textureCoordinateBuffer, Matrix4 modelviewProjectionMatrix) {
        startTextureShaderInternal(gl, textureID, color, vertexBuffer, textureCoordinateBuffer, modelviewProjectionMatrix, 3);
    }

    public static void startTextureShader(SoulGL2 gl, TextureID textureID, Color color, FloatBuffer vertexBuffer, FloatBuffer textureCoordinateBuffer, Matrix4 modelviewProjectionMatrix) {
        startTextureShaderInternal(gl, textureID, color, vertexBuffer, textureCoordinateBuffer, modelviewProjectionMatrix, 2);
    }

    private static void startTextureShaderInternal(SoulGL2 gl, TextureID textureID, Color color, FloatBuffer vertexBuffer, FloatBuffer textureCoordinateBuffer, Matrix4 modelviewProjectionMatrix, int coordinatesPerVertex) {
        TextureManager.bindTexture(gl, textureID);

        Shader shader = ShaderManager.getProgram(ShaderManager.ShaderID.DEFAULT_COLOREDTEXTURED);
        shader.start(gl);

        vertexBuffer.rewind();
        textureCoordinateBuffer.rewind();
        int vertexPositionHandle = shader.getAttribLocation(gl, "vertexPosition");
        int textureCoordinateHandle = shader.getAttribLocation(gl, "textureCoordinate");
        int MVPMatrixHandle = shader.getUniformLocation(gl, "MVPMatrix");
        int colorHandle = shader.getUniformLocation(gl, "color");

        gl.glEnableVertexAttribArray(textureCoordinateHandle);
        gl.glEnableVertexAttribArray(vertexPositionHandle);
        gl.glUniformMatrix4fv(MVPMatrixHandle, 1, false, modelviewProjectionMatrix.data, 0);
        gl.glUniform4f(colorHandle, color.r, color.g, color.b, color.a);
        gl.glVertexAttribPointer(vertexPositionHandle, coordinatesPerVertex, SoulGL2.GL_FLOAT, false, 0, vertexBuffer);
        gl.glVertexAttribPointer(textureCoordinateHandle, 2, SoulGL2.GL_FLOAT, false, 0, textureCoordinateBuffer);
    }

    public static void stopTextureShader(SoulGL2 gl) {
        Shader shader = ShaderManager.getProgram(ShaderManager.ShaderID.DEFAULT_COLOREDTEXTURED);
        int vertexPositionHandle = shader.getAttribLocation(gl, "vertexPosition");
        int textureCoordinateHandle = shader.getAttribLocation(gl, "textureCoordinate");

        gl.glDisableVertexAttribArray(vertexPositionHandle);
        gl.glDisableVertexAttribArray(textureCoordinateHandle);
        shader.stop(gl);
    }

    public static int startColorShader3D(SoulGL2 gl, Color color, FloatBuffer vertexBuffer, Matrix4 modelviewProjectionMatrix) {
        Shader shader = ShaderManager.getProgram(ShaderManager.ShaderID.DEFAULT_COLORED);
        shader.start(gl);

        int vertexPositionHandle = shader.getAttribLocation(gl, "vertexPosition");
        int colorHandle = shader.getUniformLocation(gl, "color");
        int MVPHandle = shader.getUniformLocation(gl, "MVPMatrix");
        gl.glEnableVertexAttribArray(vertexPositionHandle);
        gl.glUniformMatrix4fv(MVPHandle, 1, false, modelviewProjectionMatrix.data, 0);
        gl.glUniform4f(colorHandle, color.r, color.g, color.b, color.a);

        vertexBuffer.rewind();
        gl.glVertexAttribPointer(vertexPositionHandle, 3, SoulGL2.GL_FLOAT, false, 0, vertexBuffer);

        return vertexPositionHandle;
    }

    public static int startColorShader(SoulGL2 gl, Color color, FloatBuffer vertexBuffer, Matrix4 modelviewProjectionMatrix) {
        Shader shader = ShaderManager.getProgram(ShaderManager.ShaderID.DEFAULT_COLORED);
        shader.start(gl);

        int vertexPositionHandle = shader.getAttribLocation(gl, "vertexPosition");
        int colorHandle = shader.getUniformLocation(gl, "color");
        int MVPHandle = shader.getUniformLocation(gl, "MVPMatrix");
        gl.glEnableVertexAttribArray(vertexPositionHandle);
        gl.glUniformMatrix4fv(MVPHandle, 1, false, modelviewProjectionMatrix.data, 0);
        gl.glUniform4f(colorHandle, color.r, color.g, color.b, color.a);
        vertexBuffer.rewind();
        gl.glVertexAttribPointer(vertexPositionHandle, 2, SoulGL2.GL_FLOAT, false, 0, vertexBuffer);

        return vertexPositionHandle;
    }

    public static void stopColorShader(SoulGL2 gl) {
        Shader shader = ShaderManager.getProgram(ShaderManager.ShaderID.DEFAULT_COLORED);
        int vertexPositionHandle = shader.getAttribLocation(gl, "vertexPosition");
        gl.glDisableVertexAttribArray(vertexPositionHandle);
        shader.stop(gl);
    }
}
