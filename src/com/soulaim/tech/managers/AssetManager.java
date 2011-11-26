package com.soulaim.tech.managers;

import java.io.InputStream;

public abstract class AssetManager {

    public static void setInstance(AssetManager assetManager) {
        singleton = assetManager;
    }

    public static InputStream get(String asset) {
        return singleton.getInternal(asset);
    }

    protected abstract InputStream getInternal(String asset);

    private static AssetManager singleton;
}
