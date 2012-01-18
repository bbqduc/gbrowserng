package fi.csc.microarray.client.visualisation.methods.gbrowserng.view.overview;

import com.soulaim.tech.gles.Color;
import com.soulaim.tech.gles.SoulGL2;
import com.soulaim.tech.gles.primitives.PrimitiveBuffers;
import com.soulaim.tech.gles.renderer.PrimitiveRenderer;
import com.soulaim.tech.gles.shaders.Shader;
import com.soulaim.tech.gles.shaders.ShaderMemory;
import com.soulaim.tech.managers.ShaderManager;
import com.soulaim.tech.math.Matrix4;
import com.soulaim.tech.math.Vector2;
import fi.csc.microarray.client.visualisation.methods.gbrowserng.model.GeneCircle;

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
        Shader shader = ShaderManager.getProgram(ShaderManager.ShaderID.GENE_CIRCLE);
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
        
        for(float i : geneCircle.getChromosomeBoundaries()) {
            float x = (float)Math.cos(2*Math.PI*i), y = (float)Math.sin(2*Math.PI*i);
            PrimitiveRenderer.drawLine(0.40f*x, 0.40f*y, 0.55f*x, 0.55f*y, gl, Color.GREEN);
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
