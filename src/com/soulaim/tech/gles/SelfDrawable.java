package com.soulaim.tech.gles;

import com.soulaim.tech.math.Matrix4;

public interface SelfDrawable
{
    public void draw(SoulGL2 gl, Matrix4 viewMatrix, Matrix4 projectionMatrix);
}
