package com.soulaim.desktop;

import com.soulaim.tech.gles.SoulGL2;

import javax.media.opengl.GL2ES2;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class DesktopGL2 implements SoulGL2 {

// TODO: implement unimplemented methods.

    private final GL2ES2 gl;

    public DesktopGL2(GL2ES2 gl) {
        this.gl = gl;
    }

    public void glActiveTexture(int i) {
        gl.glActiveTexture(i);
    }

    public void glAttachShader(int i, int i1) {
        gl.glAttachShader(i, i1);
    }

    public void glBindAttribLocation(int i, int i1, String s) {
        gl.glBindAttribLocation(i, i1, s);
    }

    public void glBindBuffer(int i, int i1) {
        gl.glBindBuffer(i, i1);
    }

    public void glBindFramebuffer(int i, int i1) {
        gl.glBindFramebuffer(i, i1);
    }

    public void glBindRenderbuffer(int i, int i1) {
        gl.glBindRenderbuffer(i, i1);
    }

    public void glBindTexture(int i, int i1) {
        gl.glBindTexture(i, i1);
    }

    public void glBlendColor(float v, float v1, float v2, float v3) {
        gl.glBlendColor(v, v1, v2, v3);
    }

    public void glBlendEquation(int i) {
        gl.glBlendEquation(i);
    }

    public void glBlendEquationSeparate(int i, int i1) {
        gl.glBlendEquationSeparate(i, i1);
    }

    public void glBlendFunc(int i, int i1) {
        gl.glBlendFunc(i, i1);
    }

    public void glBlendFuncSeparate(int i, int i1, int i2, int i3) {
        gl.glBlendFuncSeparate(i, i1, i2, i3);
    }

    public void glBufferData(int i, int i1, Buffer buffer, int i2) {
        gl.glBufferData(i, i1, buffer, i2);
    }

    public void glBufferSubData(int i, int i1, int i2, Buffer buffer) {
        gl.glBufferSubData(i, i1, i2, buffer);
    }

    public int glCheckFramebufferStatus(int i) {
        return gl.glCheckFramebufferStatus(i);
    }

    public void glClear(int i) {
        gl.glClear(i);
    }

    public void glClearColor(float v, float v1, float v2, float v3) {
        gl.glClearColor(v, v1, v2, v3);
    }

    public void glClearDepthf(float v) {
        gl.glClearDepthf(v);
    }

    public void glClearStencil(int i) {
        gl.glClearStencil(i);
    }

    public void glColorMask(boolean b, boolean b1, boolean b2, boolean b3) {
        gl.glColorMask(b, b1, b2, b3);
    }

    public void glCompileShader(int i) {
        gl.glCompileShader(i);
    }

    public void glCompressedTexImage2D(int i, int i1, int i2, int i3, int i4, int i5, int i6, Buffer buffer) {
        gl.glCompressedTexImage2D(i, i1, i2, i3, i4, i5, i6, buffer);
    }

    public void glCompressedTexSubImage2D(int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7, Buffer buffer) {
        gl.glCompressedTexSubImage2D(i, i1, i2, i3, i4, i5, i6, i7, buffer);
    }

    public void glCopyTexImage2D(int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
        gl.glCopyTexImage2D(i, i1, i2, i3, i4, i5, i6, i7);
    }

    public void glCopyTexSubImage2D(int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
        gl.glCopyTexSubImage2D(i, i1, i2, i3, i4, i5, i6, i7);
    }

    public int glCreateProgram() {
        return gl.glCreateProgram();
    }

    public int glCreateShader(int i) {
        return gl.glCreateShader(i);
    }

    public void glCullFace(int i) {
        gl.glCullFace(i);
    }

    public void glDeleteBuffers(int i, int[] ints, int i1) {
        gl.glDeleteBuffers(i, ints, i1);
    }

    public void glDeleteBuffers(int i, IntBuffer intBuffer) {
        gl.glDeleteBuffers(i, intBuffer);
    }

    public void glDeleteFramebuffers(int i, int[] ints, int i1) {
        gl.glDeleteFramebuffers(i, ints, i1);
    }

    public void glDeleteFramebuffers(int i, IntBuffer intBuffer) {
        gl.glDeleteFramebuffers(i, intBuffer);
    }

    public void glDeleteProgram(int i) {
        gl.glDeleteProgram(i);
    }

    public void glDeleteRenderbuffers(int i, int[] ints, int i1) {
        gl.glDeleteRenderbuffers(i, ints, i1);
    }

    public void glDeleteRenderbuffers(int i, IntBuffer intBuffer) {
        gl.glDeleteRenderbuffers(i, intBuffer);
    }

    public void glDeleteShader(int i) {
        gl.glDeleteShader(i);
    }

    public void glDeleteTextures(int i, int[] ints, int i1) {
        gl.glDeleteTextures(i, ints, i1);
    }

    public void glDeleteTextures(int i, IntBuffer intBuffer) {
        gl.glDeleteTextures(i, intBuffer);
    }

    public void glDepthFunc(int i) {
        gl.glDepthFunc(i);
    }

    public void glDepthMask(boolean b) {
        gl.glDepthMask(b);
    }

    public void glDepthRangef(float v, float v1) {
        gl.glDepthRangef(v, v1);
    }

    public void glDetachShader(int i, int i1) {
        gl.glDetachShader(i, i1);
    }

    public void glDisable(int i) {
        gl.glDisable(i);
    }

    public void glDisableVertexAttribArray(int i) {
        gl.glDisableVertexAttribArray(i);
    }

    public void glDrawArrays(int i, int i1, int i2) {
        gl.glDrawArrays(i, i1, i2);
    }

    public void glDrawElements(int i, int i1, int i2, Buffer buffer) {
        gl.glDrawElements(i, i1, i2, buffer);
    }

    public void glEnable(int i) {
        gl.glEnable(i);
    }

    public void glEnableVertexAttribArray(int i) {
        gl.glEnableVertexAttribArray(i);
    }

    public void glFinish() {
        gl.glFinish();
    }

    public void glFlush() {
        gl.glFlush();
    }

    public void glFramebufferRenderbuffer(int i, int i1, int i2, int i3) {
        gl.glFramebufferRenderbuffer(i, i1, i2, i3);
    }

    public void glFramebufferTexture2D(int i, int i1, int i2, int i3, int i4) {
        gl.glFramebufferTexture2D(i, i1, i2, i3, i4);
    }

    public void glFrontFace(int i) {
        gl.glFrontFace(i);
    }

    public void glGenBuffers(int i, int[] ints, int i1) {
        gl.glGenBuffers(i, ints, i1);
    }

    public void glGenBuffers(int i, IntBuffer intBuffer) {
        gl.glGenBuffers(i, intBuffer);
    }

    public void glGenerateMipmap(int i) {
        gl.glGenerateMipmap(i);
    }

    public void glGenFramebuffers(int i, int[] ints, int i1) {
        gl.glGenFramebuffers(i, ints, i1);
    }

    public void glGenFramebuffers(int i, IntBuffer intBuffer) {
        gl.glGenFramebuffers(i, intBuffer);
    }

    public void glGenRenderbuffers(int i, int[] ints, int i1) {
        gl.glGenRenderbuffers(i, ints, i1);
    }

    public void glGenRenderbuffers(int i, IntBuffer intBuffer) {
        gl.glGenRenderbuffers(i, intBuffer);
    }

    public void glGenTextures(int i, int[] ints, int i1) {
        gl.glGenTextures(i, ints, i1);
    }

    public void glGenTextures(int i, IntBuffer intBuffer) {
        gl.glGenTextures(i, intBuffer);
    }

    public void glGetActiveAttrib(int i, int i1, int i2, int[] ints, int i3, int[] ints1, int i4, int[] ints2, int i5, byte[] bytes, int i6) {
        gl.glGetActiveAttrib(i, i1, i2, ints, i3, ints1, i4, ints2, i5, bytes, i6);
    }

    public void glGetActiveAttrib(int i, int i1, int i2, IntBuffer intBuffer, IntBuffer intBuffer1, IntBuffer intBuffer2, byte b) {
        //gl.glGetActiveAttrib(i, i1, i2, intBuffer, intBuffer1, intBuffer2, b);
        throw new RuntimeException("Not implemented!");
    }

    public void glGetActiveUniform(int i, int i1, int i2, int[] ints, int i3, int[] ints1, int i4, int[] ints2, int i5, byte[] bytes, int i6) {
        gl.glGetActiveUniform(i, i1, i2, ints, i3, ints1, i4, ints2, i5, bytes, i6);
    }

    public void glGetActiveUniform(int i, int i1, int i2, IntBuffer intBuffer, IntBuffer intBuffer1, IntBuffer intBuffer2, byte b) {
        //gl.glGetActiveUniform(i, i1, i2, intBuffer, intBuffer1, intBuffer2, b);
        throw new RuntimeException("Not implemented!");
    }

    public void glGetAttachedShaders(int i, int i1, int[] ints, int i2, int[] ints1, int i3) {
        gl.glGetAttachedShaders(i, i1, ints, i2, ints1, i3);
    }

    public void glGetAttachedShaders(int i, int i1, IntBuffer intBuffer, IntBuffer intBuffer1) {
        gl.glGetAttachedShaders(i, i1, intBuffer, intBuffer1);
    }

    public int glGetAttribLocation(int i, String s) {
        return gl.glGetAttribLocation(i, s);
    }

    public void glGetBooleanv(int i, boolean[] booleans, int i1) {
        //gl.glGetBooleanv(i, booleans, i1);
        throw new RuntimeException("Not implemented!");
    }

    public void glGetBooleanv(int i, IntBuffer intBuffer) {
        //gl.glGetBooleanv(i, intBuffer);
        throw new RuntimeException("Not implemented!");
    }

    public void glGetBufferParameteriv(int i, int i1, int[] ints, int i2) {
        gl.glGetBufferParameteriv(i, i1, ints, i2);
    }

    public void glGetBufferParameteriv(int i, int i1, IntBuffer intBuffer) {
        gl.glGetBufferParameteriv(i, i1, intBuffer);
    }

    public int glGetError() {
        return gl.glGetError();
    }

    public void glGetFloatv(int i, float[] floats, int i1) {
        gl.glGetFloatv(i, floats, i1);
    }

    public void glGetFloatv(int i, FloatBuffer floatBuffer) {
        gl.glGetFloatv(i, floatBuffer);
    }

    public void glGetFramebufferAttachmentParameteriv(int i, int i1, int i2, int[] ints, int i3) {
        gl.glGetFramebufferAttachmentParameteriv(i, i1, i2, ints, i3);
    }

    public void glGetFramebufferAttachmentParameteriv(int i, int i1, int i2, IntBuffer intBuffer) {
        gl.glGetFramebufferAttachmentParameteriv(i, i1, i2, intBuffer);
    }

    public void glGetIntegerv(int i, int[] ints, int i1) {
        gl.glGetIntegerv(i, ints, i1);
    }

    public void glGetIntegerv(int i, IntBuffer intBuffer) {
        gl.glGetIntegerv(i, intBuffer);
    }

    public void glGetProgramiv(int i, int i1, int[] ints, int i2) {
        gl.glGetProgramiv(i, i1, ints, i2);
    }

    public void glGetProgramiv(int i, int i1, IntBuffer intBuffer) {
        gl.glGetProgramiv(i, i1, intBuffer);
    }

    public String glGetProgramInfoLog(int program) {
        int bufferSize = 2048;
        byte[] buffer = new byte[bufferSize];
        int[] length = new int[1];
        int lengthOffset = 0;
        int bufferOffset = 0;
        gl.glGetProgramInfoLog(program, bufferSize, length, lengthOffset, buffer, bufferOffset);

        String infolog = new String(buffer, 0, length[0]);
        return "Infolog for shader program #" + program + ": " + infolog;
    }

    public void glGetRenderbufferParameteriv(int i, int i1, int[] ints, int i2) {
        gl.glGetRenderbufferParameteriv(i, i1, ints, i2);
    }

    public void glGetRenderbufferParameteriv(int i, int i1, IntBuffer intBuffer) {
        gl.glGetRenderbufferParameteriv(i, i1, intBuffer);
    }

    public void glGetShaderiv(int i, int i1, int[] ints, int i2) {
        gl.glGetShaderiv(i, i1, ints, i2);
    }

    public void glGetShaderiv(int i, int i1, IntBuffer intBuffer) {
        gl.glGetShaderiv(i, i1, intBuffer);
    }

    public String glGetShaderInfoLog(int shader) {
        int bufferSize = 2048;
        byte[] buffer = new byte[bufferSize];
        int[] length = new int[1];
        int lengthOffset = 0;
        int bufferOffset = 0;
        gl.glGetShaderInfoLog(shader, bufferSize, length, lengthOffset, buffer, bufferOffset);

        String infolog = new String(buffer, 0, length[0]);
        return "Infolog for shader #" + shader + ": " + infolog;
    }

    public void glGetShaderPrecisionFormat(int i, int i1, int[] ints, int i2, int[] ints1, int i3) {
        gl.glGetShaderPrecisionFormat(i, i1, ints, i2, ints1, i3);
    }

    public void glGetShaderPrecisionFormat(int i, int i1, IntBuffer intBuffer, IntBuffer intBuffer1) {
        gl.glGetShaderPrecisionFormat(i, i1, intBuffer, intBuffer1);
    }

    public void glGetShaderSource(int i, int i1, int[] ints, int i2, byte[] bytes, int i3) {
        gl.glGetShaderSource(i, i1, ints, i2, bytes, i3);
    }

    public void glGetShaderSource(int i, int i1, IntBuffer intBuffer, byte b) {
        //gl.glGetShaderSource(i, i1, intBuffer, b);
        throw new RuntimeException("Not implemented!");
    }

    public String glGetString(int i) {
        return gl.glGetString(i);
    }

    public void glGetTexParameterfv(int i, int i1, float[] floats, int i2) {
        gl.glGetTexParameterfv(i, i1, floats, i2);
    }

    public void glGetTexParameterfv(int i, int i1, FloatBuffer floatBuffer) {
        gl.glGetTexParameterfv(i, i1, floatBuffer);
    }

    public void glGetTexParameteriv(int i, int i1, int[] ints, int i2) {
        gl.glGetTexParameteriv(i, i1, ints, i2);
    }

    public void glGetTexParameteriv(int i, int i1, IntBuffer intBuffer) {
        gl.glGetTexParameteriv(i, i1, intBuffer);
    }

    public void glGetUniformfv(int i, int i1, float[] floats, int i2) {
        gl.glGetUniformfv(i, i1, floats, i2);
    }

    public void glGetUniformfv(int i, int i1, FloatBuffer floatBuffer) {
        gl.glGetUniformfv(i, i1, floatBuffer);
    }

    public void glGetUniformiv(int i, int i1, int[] ints, int i2) {
        gl.glGetUniformiv(i, i1, ints, i2);
    }

    public void glGetUniformiv(int i, int i1, IntBuffer intBuffer) {
        gl.glGetUniformiv(i, i1, intBuffer);
    }

    public int glGetUniformLocation(int i, String s) {
        return gl.glGetUniformLocation(i, s);
    }

    public void glGetVertexAttribfv(int i, int i1, float[] floats, int i2) {
        gl.glGetVertexAttribfv(i, i1, floats, i2);
    }

    public void glGetVertexAttribfv(int i, int i1, FloatBuffer floatBuffer) {
        gl.glGetVertexAttribfv(i, i1, floatBuffer);
    }

    public void glGetVertexAttribiv(int i, int i1, int[] ints, int i2) {
        gl.glGetVertexAttribiv(i, i1, ints, i2);
    }

    public void glGetVertexAttribiv(int i, int i1, IntBuffer intBuffer) {
        gl.glGetVertexAttribiv(i, i1, intBuffer);
    }

    public void glHint(int i, int i1) {
        gl.glHint(i, i1);
    }

    public boolean glIsBuffer(int i) {
        return gl.glIsBuffer(i);
    }

    public boolean glIsEnabled(int i) {
        return gl.glIsEnabled(i);
    }

    public boolean glIsFramebuffer(int i) {
        return gl.glIsFramebuffer(i);
    }

    public boolean glIsProgram(int i) {
        return gl.glIsProgram(i);
    }

    public boolean glIsRenderbuffer(int i) {
        return gl.glIsRenderbuffer(i);
    }

    public boolean glIsShader(int i) {
        return gl.glIsShader(i);
    }

    public boolean glIsTexture(int i) {
        return gl.glIsTexture(i);
    }

    public void glLineWidth(float v) {
        gl.glLineWidth(v);
    }

    public void glLinkProgram(int i) {
        gl.glLinkProgram(i);
    }

    public void glPixelStorei(int i, int i1) {
        gl.glPixelStorei(i, i1);
    }

    public void glPolygonOffset(float v, float v1) {
        gl.glPolygonOffset(v, v1);
    }

    public void glReadPixels(int i, int i1, int i2, int i3, int i4, int i5, Buffer buffer) {
        gl.glReadPixels(i, i1, i2, i3, i4, i5, buffer);
    }

    public void glReleaseShaderCompiler() {
        gl.glReleaseShaderCompiler();
    }

    public void glRenderbufferStorage(int i, int i1, int i2, int i3) {
        gl.glRenderbufferStorage(i, i1, i2, i3);
    }

    public void glSampleCoverage(float v, boolean b) {
        gl.glSampleCoverage(v, b);
    }

    public void glScissor(int i, int i1, int i2, int i3) {
        gl.glScissor(i, i1, i2, i3);
    }

    public void glShaderBinary(int i, int[] ints, int i1, int i2, Buffer buffer, int i3) {
        gl.glShaderBinary(i, ints, i1, i2, buffer, i3);
    }

    public void glShaderBinary(int i, IntBuffer intBuffer, int i1, Buffer buffer, int i2) {
        gl.glShaderBinary(i, intBuffer, i1, buffer, i2);
    }


    public void glShaderSource(int shader, String source) {
        String[] sources = new String[1];
        sources[0] = source;
        int[] lengths = new int[1];
        lengths[0] = source.length();
        int lengthOffset = 0;
        gl.glShaderSource(shader, 1, sources, lengths, lengthOffset);
    }

    public void glStencilFunc(int i, int i1, int i2) {
        gl.glStencilFunc(i, i1, i2);
    }

    public void glStencilFuncSeparate(int i, int i1, int i2, int i3) {
        gl.glStencilFuncSeparate(i, i1, i2, i3);
    }

    public void glStencilMask(int i) {
        gl.glStencilMask(i);
    }

    public void glStencilMaskSeparate(int i, int i1) {
        gl.glStencilMaskSeparate(i, i1);
    }

    public void glStencilOp(int i, int i1, int i2) {
        gl.glStencilOp(i, i1, i2);
    }

    public void glStencilOpSeparate(int i, int i1, int i2, int i3) {
        gl.glStencilOpSeparate(i, i1, i2, i3);
    }

    public void glTexImage2D(int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7, Buffer buffer) {
        gl.glTexImage2D(i, i1, i2, i3, i4, i5, i6, i7, buffer);
    }

    public void glTexParameterf(int i, int i1, float v) {
        gl.glTexParameterf(i, i1, v);
    }

    public void glTexParameterfv(int i, int i1, float[] floats, int i2) {
        gl.glTexParameterfv(i, i1, floats, i2);
    }

    public void glTexParameterfv(int i, int i1, FloatBuffer floatBuffer) {
        gl.glTexParameterfv(i, i1, floatBuffer);
    }

    public void glTexParameteri(int i, int i1, int i2) {
        gl.glTexParameteri(i, i1, i2);
    }

    public void glTexParameteriv(int i, int i1, int[] ints, int i2) {
        gl.glTexParameteriv(i, i1, ints, i2);
    }

    public void glTexParameteriv(int i, int i1, IntBuffer intBuffer) {
        gl.glTexParameteriv(i, i1, intBuffer);
    }

    public void glTexSubImage2D(int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7, Buffer buffer) {
        gl.glTexSubImage2D(i, i1, i2, i3, i4, i5, i6, i7, buffer);
    }

    public void glUniform1f(int i, float v) {
        gl.glUniform1f(i, v);
    }

    public void glUniform1fv(int i, int i1, float[] floats, int i2) {
        gl.glUniform1fv(i, i1, floats, i2);
    }

    public void glUniform1fv(int i, int i1, FloatBuffer floatBuffer) {
        gl.glUniform1fv(i, i1, floatBuffer);
    }

    public void glUniform1i(int i, int i1) {
        gl.glUniform1i(i, i1);
    }

    public void glUniform1iv(int i, int i1, int[] ints, int i2) {
        gl.glUniform1iv(i, i1, ints, i2);
    }

    public void glUniform1iv(int i, int i1, IntBuffer intBuffer) {
        gl.glUniform1iv(i, i1, intBuffer);
    }

    public void glUniform2f(int i, float v, float v1) {
        gl.glUniform2f(i, v, v1);
    }

    public void glUniform2fv(int i, int i1, float[] floats, int i2) {
        gl.glUniform2fv(i, i1, floats, i2);
    }

    public void glUniform2fv(int i, int i1, FloatBuffer floatBuffer) {
        gl.glUniform2fv(i, i1, floatBuffer);
    }

    public void glUniform2i(int i, int i1, int i2) {
        gl.glUniform2i(i, i1, i2);
    }

    public void glUniform2iv(int i, int i1, int[] ints, int i2) {
        gl.glUniform2iv(i, i1, ints, i2);
    }

    public void glUniform2iv(int i, int i1, IntBuffer intBuffer) {
        gl.glUniform2iv(i, i1, intBuffer);
    }

    public void glUniform3f(int i, float v, float v1, float v2) {
        gl.glUniform3f(i, v, v1, v2);
    }

    public void glUniform3fv(int i, int i1, float[] floats, int i2) {
        gl.glUniform3fv(i, i1, floats, i2);
    }

    public void glUniform3fv(int i, int i1, FloatBuffer floatBuffer) {
        gl.glUniform3fv(i, i1, floatBuffer);
    }

    public void glUniform3i(int i, int i1, int i2, int i3) {
        gl.glUniform3i(i, i1, i2, i3);
    }

    public void glUniform3iv(int i, int i1, int[] ints, int i2) {
        gl.glUniform3iv(i, i1, ints, i2);
    }

    public void glUniform3iv(int i, int i1, IntBuffer intBuffer) {
        gl.glUniform3iv(i, i1, intBuffer);
    }

    public void glUniform4f(int i, float v, float v1, float v2, float v3) {
        gl.glUniform4f(i, v, v1, v2, v3);
    }

    public void glUniform4fv(int i, int i1, float[] floats, int i2) {
        gl.glUniform4fv(i, i1, floats, i2);
    }

    public void glUniform4fv(int i, int i1, FloatBuffer floatBuffer) {
        gl.glUniform4fv(i, i1, floatBuffer);
    }

    public void glUniform4i(int i, int i1, int i2, int i3, int i4) {
        gl.glUniform4i(i, i1, i2, i3, i4);
    }

    public void glUniform4iv(int i, int i1, int[] ints, int i2) {
        gl.glUniform4iv(i, i1, ints, i2);
    }

    public void glUniform4iv(int i, int i1, IntBuffer intBuffer) {
        gl.glUniform4iv(i, i1, intBuffer);
    }

    public void glUniformMatrix2fv(int i, int i1, boolean b, float[] floats, int i2) {
        gl.glUniformMatrix2fv(i, i1, b, floats, i2);
    }

    public void glUniformMatrix2fv(int i, int i1, boolean b, FloatBuffer floatBuffer) {
        gl.glUniformMatrix2fv(i, i1, b, floatBuffer);
    }

    public void glUniformMatrix3fv(int i, int i1, boolean b, float[] floats, int i2) {
        gl.glUniformMatrix3fv(i, i1, b, floats, i2);
    }

    public void glUniformMatrix3fv(int i, int i1, boolean b, FloatBuffer floatBuffer) {
        gl.glUniformMatrix3fv(i, i1, b, floatBuffer);
    }

    public void glUniformMatrix4fv(int i, int i1, boolean b, float[] floats, int i2) {
        gl.glUniformMatrix4fv(i, i1, b, floats, i2);
    }

    public void glUniformMatrix4fv(int i, int i1, boolean b, FloatBuffer floatBuffer) {
        gl.glUniformMatrix4fv(i, i1, b, floatBuffer);
    }

    public void glUseProgram(int i) {
        gl.glUseProgram(i);
    }

    public void glValidateProgram(int i) {
        gl.glValidateProgram(i);
    }

    public void glVertexAttrib1f(int i, float v) {
        gl.glVertexAttrib1f(i, v);
    }

    public void glVertexAttrib1fv(int i, float[] floats, int i1) {
        gl.glVertexAttrib1fv(i, floats, i1);
    }

    public void glVertexAttrib1fv(int i, FloatBuffer floatBuffer) {
        gl.glVertexAttrib1fv(i, floatBuffer);
    }

    public void glVertexAttrib2f(int i, float v, float v1) {
        gl.glVertexAttrib2f(i, v, v1);
    }

    public void glVertexAttrib2fv(int i, float[] floats, int i1) {
        gl.glVertexAttrib2fv(i, floats, i1);
    }

    public void glVertexAttrib2fv(int i, FloatBuffer floatBuffer) {
        gl.glVertexAttrib2fv(i, floatBuffer);
    }

    public void glVertexAttrib3f(int i, float v, float v1, float v2) {
        gl.glVertexAttrib3f(i, v, v1, v2);
    }

    public void glVertexAttrib3fv(int i, float[] floats, int i1) {
        gl.glVertexAttrib3fv(i, floats, i1);
    }

    public void glVertexAttrib3fv(int i, FloatBuffer floatBuffer) {
        gl.glVertexAttrib3fv(i, floatBuffer);
    }

    public void glVertexAttrib4f(int i, float v, float v1, float v2, float v3) {
        gl.glVertexAttrib4f(i, v, v1, v2, v3);
    }

    public void glVertexAttrib4fv(int i, float[] floats, int i1) {
        gl.glVertexAttrib4fv(i, floats, i1);
    }

    public void glVertexAttrib4fv(int i, FloatBuffer floatBuffer) {
        gl.glVertexAttrib4fv(i, floatBuffer);
    }

    public void glVertexAttribPointer(int indx, int size, int type, boolean normalized, int stride, Buffer ptr) {
        gl.glVertexAttribPointer(indx, size, type, normalized, stride, ptr);
    }

    public void glViewport(int i, int i1, int i2, int i3) {
        gl.glViewport(i, i1, i2, i3);
    }

}
