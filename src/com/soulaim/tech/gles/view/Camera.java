package com.soulaim.tech.gles.view;

public class Camera {
    public float x;
    public float y;
    public float z;

    public Camera() {
        x = y = 0;
        z = 1;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

}
