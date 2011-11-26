package com.soulaim.desktop;

import com.soulaim.tech.managers.AssetManager;

import java.io.InputStream;

public class DesktopAssetManager extends AssetManager {

    @Override
    protected InputStream getInternal(String asset) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(asset);
        if(inputStream == null) {
            throw new RuntimeException("Could not load file: " + asset);
        }
        return inputStream;
    }
}

