package com.soulaim.desktop;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import com.soulaim.tech.gles.SoulGL2;
import com.soulaim.tech.gles.TextureID;
import com.soulaim.tech.managers.TextureManager;

import java.io.IOException;
import java.net.URL;
import java.util.EnumMap;
import java.util.Map;

import static com.soulaim.tech.gles.TextureID.*;

public class DesktopTextureManager extends TextureManager {

    String path = "drawable/";

    private Map<TextureID, Texture> textures;
    protected boolean ready;

    public DesktopTextureManager() {
        super();
        textures = new EnumMap<TextureID, Texture>(TextureID.class);
    }


    private void loadTextures(SoulGL2 gl) {
        makeTexture(gl, "avatar_1024.png", TILESET_UNIT);
        makeTexture(gl, "defeat_img_hd.png", DEFEAT);
        makeTexture(gl, "perfect.png", PERFECT);
        makeTexture(gl, "paused.png", PAUSED);
        makeTexture(gl, "health_icon.png", HEALTH_ICON);
        makeTexture(gl, "numbers.png", NUMBERS);
        makeTexture(gl, "particle.png", PARTICLE);
        makeTexture(gl, "ground_jungle_clean.png", BG_FIRE);
        makeTexture(gl, "mm_main_buttons_hd.png", MM_BUTTONS);
        makeTexture(gl, "mm_difficulty_buttons_hd.png", DIFF_BUTTONS);
        makeTexture(gl, "mm_options_buttons_hd.png", OPTIONS_BUTTONS);
        makeTexture(gl, "mm_level_icons.png", LEVEL_ICONS);
        makeTexture(gl, "icon_nospell.png", ICON_NO_SPELL);
        makeTexture(gl, "directions.png", DIRECTIONS);

        makeTexture(gl, "leveltiles.png", LEVELTILES);
        makeTexture(gl, "default_texture.png", DEFAULT);
        makeTexture(gl, "font_texture.png", FONT);
        makeTexture(gl, "goblin_animation.png", GOBLIN_ANIMATION);
        makeTexture(gl, "duckling_animation.png", DUCKLING_ANIMATION);
        makeTexture(gl, "spell_icons.png", SPELL_ICONS);
        makeTexture(gl, "magic_symbol.png", MAGIC_SYMBOL);
	}

    protected void internalInit(SoulGL2 gl) {
        if(!ready) {
            ready = true;
            loadTextures(gl);
        }
    }

    protected void makeTexture(SoulGL2 gl, String filename, TextureID index) {
        try {
            URL url = getClass().getClassLoader().getResource(path + filename);
            Texture texture = TextureIO.newTexture(url, false, "png");
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
