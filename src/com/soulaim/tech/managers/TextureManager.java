package com.soulaim.tech.managers;

import com.soulaim.tech.gles.SoulGL2;
import com.soulaim.tech.gles.TextureID;

public abstract class TextureManager {

    protected abstract void internalInit(SoulGL2 gl);

    protected abstract void internalBindTexture(SoulGL2 gl, TextureID texture);

    protected abstract void internalShutdown(SoulGL2 gl);


    public static void init(SoulGL2 gl) {
        singleton.internalInit(gl);
    }

    public static void bindTexture(SoulGL2 gl, TextureID texture) {
        singleton.internalBindTexture(gl, texture);
    }

    public static void shutdown(SoulGL2 gl) {
        singleton.internalShutdown(gl);
    }

    private static TextureManager singleton;

    public static void setInstance(TextureManager textureManager) {
        singleton = textureManager;
    }
}
