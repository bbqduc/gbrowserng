package com.soulaim.tech.managers;

import com.soulaim.tech.gles.SoulGL2;
import com.soulaim.tech.gles.shaders.Shader;
import com.soulaim.tech.gles.shaders.ShaderException;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.Map;
import java.util.Scanner;

public class ShaderManager {

    public static enum ShaderID {
        DEFAULT_COLORED,
        DEFAULT_TEXTURED,
        DEFAULT_COLOREDTEXTURED,
        AURA,
        EXPLOSION,
        RING,
        HILIGHT,
        FALLING_METEOR,
        TORRENT,
        TEXTURE_CHANNELS
    }

    private static final String path = "shaders/";

    /**
     * Loads and compiles all shaders using the shader manager that is given by the setInstance() method.
     * @param gl The graphics system.
     */
    public static void createPrograms(SoulGL2 gl) {
        System.out.println("Default Colored");
        programs.put(ShaderID.DEFAULT_COLORED, loadProgram(gl, "default_colored.vert", "default_colored.frag"));
        System.out.println("Default Textured");
        programs.put(ShaderID.DEFAULT_TEXTURED, loadProgram(gl, "default_textured.vert", "default_textured.frag"));
        System.out.println("Default color texture");
        programs.put(ShaderID.DEFAULT_COLOREDTEXTURED, loadProgram(gl, "default_coloredtextured.vert", "default_coloredtextured.frag"));
        System.out.println("Aura");
        programs.put(ShaderID.AURA, loadProgram(gl, "aura.vert", "aura.frag"));
        System.out.println("Explosion");
        programs.put(ShaderID.EXPLOSION, loadProgram(gl, "explosion.vert", "explosion.frag"));
        System.out.println("Ring");
        programs.put(ShaderID.RING, loadProgram(gl, "ring.vert", "ring.frag"));
        System.out.println("Hilight");
        programs.put(ShaderID.HILIGHT, loadProgram(gl, "hilight.vert", "hilight.frag"));
        System.out.println("TextureChannels");
        programs.put(ShaderID.TEXTURE_CHANNELS, loadProgram(gl, "texture_channels.vert", "texture_channels.frag"));
        System.out.println("Meteor");
        programs.put(ShaderID.FALLING_METEOR, loadProgram(gl, "meteor.vert", "meteor.frag"));
        System.out.println("Torrent");
        programs.put(ShaderID.TORRENT, loadProgram(gl, "torrent.vert", "torrent.frag"));
        System.out.println("ALL OK");

        for(Map.Entry<ShaderID, Shader> entry : programs.entrySet()) {
            if(entry.getValue() == null) {
                throw new RuntimeException("Error: shader #" + entry.getKey() + " not created");
            }
        }
    }

    protected static Shader loadProgram(SoulGL2 gl, String vertexFilename, String fragmentFilename) {
        try {
            String vertexShader = readFile(path + vertexFilename);
            String fragmentShader = readFile(path + fragmentFilename);

            return createProgram(gl, vertexShader, fragmentShader);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: ", e);
        } catch(ShaderException e) {
            throw new RuntimeException("Could not compile shader: " + vertexFilename + "/" + fragmentFilename, e);
        }
    }

    private static String readFile(String filename) throws FileNotFoundException {
        InputStream inputStream = AssetManager.get(filename);
        if(inputStream == null) {
            throw new ShaderException("Could not load file: " + filename);
        }

        return new Scanner(inputStream).useDelimiter("\\Z").next();
    }


    public static void removePrograms(SoulGL2 gl) {
        for(Shader shader : programs.values()) {
            shader.delete(gl);
        }
        programs.clear();
    }

    public static Shader getProgram(ShaderID program) {
        return programs.get(program);
    }

    protected static final Map<ShaderID, Shader> programs = new EnumMap<ShaderID, Shader>(ShaderID.class);

    protected static Shader createProgram(SoulGL2 gl, String vertexShader, String fragmentShader) {
        return new Shader(gl, vertexShader, fragmentShader);
    }
}
