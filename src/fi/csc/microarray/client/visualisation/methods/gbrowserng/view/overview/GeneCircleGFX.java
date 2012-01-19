package fi.csc.microarray.client.visualisation.methods.gbrowserng.view.overview;

import fi.csc.microarray.client.visualisation.methods.gbrowserng.model.GeneCircle;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.view.ids.GenoShaders;
import gles.Color;
import gles.SoulGL2;
import gles.primitives.PrimitiveBuffers;
import gles.renderer.PrimitiveRenderer;
import gles.shaders.Shader;
import gles.shaders.ShaderMemory;
import managers.ShaderManager;
import math.Matrix4;
import math.Vector2;

public class GeneCircleGFX {

    public float time = 0;
    private float hilight = 0;
    private float hilightTarget = 0;
    private Vector2 mousePos = new Vector2();
    private GeneCircle geneCircle;
    
    public GeneCircleGFX(GeneCircle geneCircle) {
        this.geneCircle = geneCircle;
    }

    public void draw(SoulGL2 gl, Matrix4 modelMatrix, Vector2 mousePosition) {
        Shader shader = ShaderManager.getProgram(GenoShaders.GenoShaderID.GENE_CIRCLE);
        shader.start(gl);

        mousePos.copyFrom(mousePosition);
        mousePos.normalize();

        /*
        mousePos.scale(1.2f);
        if(mousePos.length() > 0.6f) {
            mousePos.scale( Math.max(0.1f, 1.6f - mousePos.length()) );
        }
        */

        ShaderMemory.setUniformVec1(gl, shader, "time", time);
        ShaderMemory.setUniformVec1(gl, shader, "hilight", hilight);
        ShaderMemory.setUniformVec2(gl, shader, "mouse", mousePos.x, mousePos.y);
        ShaderMemory.setUniformMat4(gl, shader, "modelMatrix", modelMatrix);

        int vertexPositionHandle = shader.getAttribLocation(gl, "vertexPosition");
        PrimitiveBuffers.circleBuffer.rewind();
        gl.glEnableVertexAttribArray(vertexPositionHandle);
        gl.glVertexAttribPointer(vertexPositionHandle, 2, SoulGL2.GL_FLOAT, false, 0, PrimitiveBuffers.circleBuffer);
        gl.glDrawArrays(SoulGL2.GL_TRIANGLE_FAN, 0, PrimitiveBuffers.circleBuffer.capacity() / 2);
        gl.glDisableVertexAttribArray(vertexPositionHandle);
        shader.stop(gl);
        
        for(Vector2 vec : geneCircle.getChromosomeBoundariesPositions()) {
            PrimitiveRenderer.drawLine(0.40f*vec.x, 0.40f*vec.y, 0.55f*vec.x, 0.55f*vec.y, gl, Color.GREEN);
        }
    }

    public void tick(float dt) {
        time += dt;
        hilight += (1.0f - Math.pow(0.1f, dt)) * (hilightTarget - hilight);
    }

    public void setHilight(float value) {
        hilightTarget = value;
    }
}
