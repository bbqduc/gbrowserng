package com.soulaim.tech.gles.primitives;

import java.nio.FloatBuffer;

public class TexturedVisualQuad extends VisualQuad
{
    private float tex_bx;
	private float tex_by;
	private float tex_tx;
	private float tex_ty;

    public void updateTexCoords(float bx, float tx, float by, float ty)
	{
		tex_bx = bx;
		tex_tx = tx;
		tex_by = by;
		tex_ty = ty;
		
		texBuffer.rewind();
		putTexCoord(tex_tx, tex_by);
		putTexCoord(tex_tx, tex_ty);
		putTexCoord(tex_bx, tex_ty);
		putTexCoord(tex_bx, tex_by);
		texBuffer.rewind();
	}
	
	public void adjustTexCoords(float dbx, float dtx, int dby, int dty)
	{
		tex_bx += dbx;
		tex_tx += dtx;
		tex_by += dby;
		tex_ty += dty;
		
		texBuffer.rewind();
		putTexCoord(tex_tx, tex_by);
		putTexCoord(tex_tx, tex_ty);
		putTexCoord(tex_bx, tex_ty);
		putTexCoord(tex_bx, tex_by);
		texBuffer.rewind();
	}


    public TexturedVisualQuad(float bottom_x, float bottom_y, float top_x, float top_y)
	{
        super(top_y, bottom_x, top_x, bottom_y);

		this.updateTexCoords(0, 1, 0, 1);
	}

    @Override
    public FloatBuffer getColors() {
        throw new UnsupportedOperationException("This should never be called");
    }

    @Override
    public FloatBuffer getTexCoords() {
        return super.texBuffer;
    }

    private void putTexCoord(float x, float y)
	{	
		texBuffer.put(x);
		texBuffer.put(y);
	}

}
