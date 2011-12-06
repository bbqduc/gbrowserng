package com.soulaim.tech.gles.shaders;

import com.soulaim.tech.gles.SoulGL2;
import com.soulaim.tech.math.Matrix4;

public class ShaderMemory {
    public static void setUniformVec1(SoulGL2 gl, Shader shader, String name, float value) {
        int pos = shader.getUniformLocation(gl, name);
        gl.glUniform1f(pos, value);
    }

    public static void setUniformVec2(SoulGL2 gl, Shader shader, String name, float value1, float value2) {
        int pos = shader.getUniformLocation(gl, name);
        gl.glUniform2f(pos, value1, value2);
    }

    public static void setUniformVec3(SoulGL2 gl, Shader shader, String name, float value1, float value2, float value3) {
        int pos = shader.getUniformLocation(gl, name);
        gl.glUniform3f(pos, value1, value2, value3);
    }

    public static void setUniformVec4(SoulGL2 gl, Shader shader, String name, float value1, float value2, float value3, float value4) {
        int pos = shader.getUniformLocation(gl, name);
        gl.glUniform4f(pos, value1, value2, value3, value4);
    }

    public static void setUniformMat4(SoulGL2 gl, Shader shader, String name, Matrix4 matrix) {
        int pos = shader.getUniformLocation(gl, name);
        gl.glUniformMatrix4fv(pos, 1, false, matrix.data, 0);
    }
}
