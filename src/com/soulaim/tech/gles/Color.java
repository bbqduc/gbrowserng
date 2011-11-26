package com.soulaim.tech.gles;

/**
 * Contains a single color in the RGBA-format.
 */
public class Color {

    public static final Color BLACK = new Color(0,0,0);
    public static final Color BLUE = new Color(0,0,1);
    public static final Color GREEN = new Color(0,1,0);
    public static final Color CYAN = new Color(0,1,1);
    public static final Color RED = new Color(1,0,0);
    public static final Color DARK_RED = new Color(0.5f,0,0);
    public static final Color MAGENTA = new Color(1,0,1);
    public static final Color YELLOW = new Color(1,1,0);
    public static final Color WHITE = new Color(1,1,1);
    public static final Color ORANGE = new Color(1, 0.5f, 0.25f);
    public static final Color LIGHT_BLUE = new Color(0.5f, 1.0f, 1.0f);
    public static final Color LIGHT_RED = new Color(1.0f, 0.5f, 0.3f);
    public static final Color BROWN = new Color(0.5f, 0.2f, 0.1f);

    public float r;
    public float g;
    public float b;
    public float a;



    public Color(Color color) {
        this.r = color.r;
        this.g = color.g;
        this.b = color.b;
        this.a = color.a;
    }

    public Color(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = 1.0f;
    }

    public Color(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
}
