package com.soulaim.tech.gles.shaders;

import com.soulaim.tech.gles.SoulGL2;

import java.util.HashMap;

public class Shader {
    private int vertexShader;
    private int fragmentShader;
    private int program;

    private final String vertexShaderSource;
    private final String fragmentShaderSource;

    private final HashMap<String, Integer> attribCache = new HashMap<String, Integer>();
    private final HashMap<String, Integer> uniformCache = new HashMap<String, Integer>();


    public Shader(SoulGL2 gl, String vertexShader, String fragmentShader) {
        this.vertexShaderSource = vertexShader;
        this.fragmentShaderSource = fragmentShader;
        createProgram(gl, vertexShader, fragmentShader);
    }

    public void start(SoulGL2 gl) {
        gl.glUseProgram(program);
    }

    public void stop(SoulGL2 gl) {
        gl.glUseProgram(0);
    }

    private int loadShader(SoulGL2 gl, int shaderType, String source) {
        int shader = gl.glCreateShader(shaderType);
        if(shader == 0) {
            throw new ShaderException("Could not create shader with type: " + shaderType);
        }
        gl.glShaderSource(shader, source);
        gl.glCompileShader(shader);
        int[] compiled = new int[1];
        gl.glGetShaderiv(shader, SoulGL2.GL_COMPILE_STATUS, compiled, 0);
        if (compiled[0] == 0) {
            String infolog = gl.glGetShaderInfoLog(shader);
            gl.glDeleteShader(shader);
            throw new ShaderException("Could not compile shader: " + infolog);
        }
        return shader;
    }

    private void createProgram(SoulGL2 gl, String vertexSource, String fragmentSource) {
        vertexShader = loadShader(gl, SoulGL2.GL_VERTEX_SHADER, vertexSource);
        fragmentShader = loadShader(gl, SoulGL2.GL_FRAGMENT_SHADER, fragmentSource);
        program = linkShader(gl, vertexShader, fragmentShader);
    }

    private int linkShader(SoulGL2 gl, int vertexShader, int fragmentShader) {
        int program = gl.glCreateProgram();
        if(program == 0) {
            throw new ShaderException("Could not create program");
        }
        gl.glAttachShader(program, vertexShader);
        gl.glAttachShader(program, fragmentShader);
        gl.glLinkProgram(program);
        int[] linkStatus = new int[1];
        gl.glGetProgramiv(program, SoulGL2.GL_LINK_STATUS, linkStatus, 0);
        if (linkStatus[0] != SoulGL2.GL_TRUE) {
            String infolog = gl.glGetProgramInfoLog(program);
            gl.glDeleteProgram(program);
            throw new ShaderException("Could not link program: " + infolog);
        }
        return program;
    }

    public void delete(SoulGL2 gl) {
        gl.glDeleteProgram(program);
        gl.glDeleteShader(vertexShader);
        gl.glDeleteShader(fragmentShader);
        program = 0;
        vertexShader = 0;
        fragmentShader = 0;
    }

    public int getAttribLocation(SoulGL2 gl, String attrib) {
        Integer cachedAttrib = attribCache.get(attrib);
        if(cachedAttrib != null) {
            return cachedAttrib;
        }

        int attribLocation = gl.glGetAttribLocation(program, attrib);
        if(attribLocation == -1) {
            throw new RuntimeException("Unable to get attrib location for \"" + attrib + "\":" + vertexShaderSource + "\n" + fragmentShaderSource);
        }

        attribCache.put(attrib, attribLocation);

        return attribLocation;
    }

    public int getUniformLocation(SoulGL2 gl, String uniform) {
        Integer cachedUniform = uniformCache.get(uniform);
        if(cachedUniform != null) {
            return cachedUniform;
        }

        int uniformLocation = gl.glGetUniformLocation(program, uniform);
        if(uniformLocation == -1) {
            throw new RuntimeException("Unable to get uniform location for \"" + uniform + "\":" + vertexShaderSource + "\n" + fragmentShaderSource);
        }

        uniformCache.put(uniform, uniformLocation);

        return uniformLocation;
    }
}
