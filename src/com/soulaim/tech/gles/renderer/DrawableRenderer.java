package com.soulaim.tech.gles.renderer;

import com.soulaim.tech.gles.Drawable;
import com.soulaim.tech.gles.SoulGL2;
import com.soulaim.tech.managers.ShaderManager;
import com.soulaim.tech.gles.primitives.ColoredVisualQuad;
import com.soulaim.tech.gles.shaders.Shader;
import com.soulaim.tech.math.Matrix4;

public class DrawableRenderer
{
    private static final ColoredVisualQuad fullScreen = new ColoredVisualQuad(-1.0f, -1.0f, +1.0f, +1.0f);

    private static int textureCoordinateHandle;
    private static int vertexPositionHandle;
    private static int MVPMatrixHandle;


    public static void startDrawingDrawable(SoulGL2 gl, Matrix4 modelviewProjectionMatrix) {
        Shader shader = ShaderManager.getProgram(ShaderManager.ShaderID.DEFAULT_TEXTURED);
        shader.start(gl);

        textureCoordinateHandle = shader.getAttribLocation(gl, "textureCoordinate");
        vertexPositionHandle = shader.getAttribLocation(gl, "vertexPosition");
        MVPMatrixHandle = shader.getUniformLocation(gl, "MVPMatrix");

        gl.glEnable(SoulGL2.GL_TEXTURE_2D);
        gl.glEnableVertexAttribArray(textureCoordinateHandle);

        gl.glEnable(SoulGL2.GL_BLEND);
        gl.glBlendFunc(SoulGL2.GL_SRC_ALPHA, SoulGL2.GL_ONE_MINUS_SRC_ALPHA);

        gl.glUniformMatrix4fv(MVPMatrixHandle, 1, false, modelviewProjectionMatrix.data, 0);

    }

    public static void stopDrawingDrawable(SoulGL2 gl) {
        Shader shader = ShaderManager.getProgram(ShaderManager.ShaderID.DEFAULT_TEXTURED);

        gl.glDisable(SoulGL2.GL_TEXTURE_2D);
        gl.glDisable(SoulGL2.GL_BLEND);
        gl.glDisableVertexAttribArray(textureCoordinateHandle);
        gl.glDisableVertexAttribArray(vertexPositionHandle);

        shader.stop(gl);
    }

    public static void draw(SoulGL2 gl, Drawable drawable, boolean colored, boolean textured, boolean blend)
    {
        assert !colored;
        assert textured;

        int coordinatesPerVertex = 3;

        gl.glEnableVertexAttribArray(textureCoordinateHandle);
        gl.glVertexAttribPointer(textureCoordinateHandle, 2, SoulGL2.GL_FLOAT, false, 0, drawable.getTexCoords());
        gl.glEnableVertexAttribArray(vertexPositionHandle);
        gl.glVertexAttribPointer(vertexPositionHandle, coordinatesPerVertex, SoulGL2.GL_FLOAT, false, 0, drawable.getVertices());
        gl.glDrawElements(SoulGL2.GL_TRIANGLES, drawable.getIndices().capacity(), SoulGL2.GL_UNSIGNED_SHORT, drawable.getIndices());
        gl.glDisableVertexAttribArray(textureCoordinateHandle);
        gl.glDisableVertexAttribArray(vertexPositionHandle);
    }

    public static void drawFullscreen(SoulGL2 gl, boolean colored, boolean textured, boolean blend) {
        Matrix4 modelviewProjectionMatrix = new Matrix4();

        startDrawingDrawable(gl, modelviewProjectionMatrix);
        draw(gl, fullScreen, colored, textured, blend);
        stopDrawingDrawable(gl);
    }
}
