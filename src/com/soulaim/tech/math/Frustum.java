package com.soulaim.tech.math;

public class Frustum {
    public float left;
    public float right;
    public float bottom;
    public float top;
    public float near;
    public float far;

    @Override
    public String toString() {
        return "Frustum{" +
                "left=" + left +
                ", right=" + right +
                ", bottom=" + bottom +
                ", top=" + top +
                ", near=" + near +
                ", far=" + far +
                '}';
    }
}
