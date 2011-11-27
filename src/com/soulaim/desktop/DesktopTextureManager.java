package com.soulaim.desktop;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import com.soulaim.tech.gles.SoulGL2;
import com.soulaim.tech.gles.TextureID;
import com.soulaim.tech.managers.TextureManager;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.EnumMap;
import java.util.Map;

import static com.soulaim.tech.gles.TextureID.*;

public class DesktopTextureManager extends TextureManager {

    String path = "resources/images/";

    private Map<TextureID, Texture> textures;
    protected boolean ready;

    public DesktopTextureManager() {
        super();
        textures = new EnumMap<TextureID, Texture>(TextureID.class);
    }


    private void loadTextures(SoulGL2 gl) {
        makeTexture(gl, "font.png", FONT);
	}

    protected void internalInit(SoulGL2 gl) {
        if(!ready) {
            ready = true;
            loadTextures(gl);
        }
    }

    protected void makeTexture(SoulGL2 gl, String filename, TextureID index) {
        try {
            // URL url = getClass().getClassLoader().getResource(path + filename);
            Texture texture = TextureIO.newTexture(new File(path + filename), false);
            textures.put(index, texture);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        gl.glTexParameterf(SoulGL2.GL_TEXTURE_2D, SoulGL2.GL_TEXTURE_MIN_FILTER, SoulGL2.GL_LINEAR);
        gl.glTexParameterf(SoulGL2.GL_TEXTURE_2D, SoulGL2.GL_TEXTURE_MAG_FILTER, SoulGL2.GL_LINEAR);

        gl.glTexParameterf(SoulGL2.GL_TEXTURE_2D, SoulGL2.GL_TEXTURE_WRAP_S, SoulGL2.GL_REPEAT);
        gl.glTexParameterf(SoulGL2.GL_TEXTURE_2D, SoulGL2.GL_TEXTURE_WRAP_T, SoulGL2.GL_REPEAT);
    }

    protected void internalBindTexture(SoulGL2 gl, TextureID textureID) {
        Texture texture = textures.get(textureID);
        assert texture != null : "Texture with id " + textureID + " has not been loaded!";
        //texture.bind();
    }

    // Never called. Maybe is ok.
    protected void internalShutdown(SoulGL2 gl) {
    }
}
