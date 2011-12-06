package com.soulaim.tech.math;

/**
 * This class represents a point in the 2D-space or a 2D-vector depending on the context.
 */
public class Vector2
{
    public float x;
    public float y;

    /**
     * Rotates the current vector counterclockwise around the origin for given angle.
     * @param rad The given angle in radians.
     */
    public void rotate(float rad)
    {
        /*
          cos a,  -sin a    x
          sin a,   cos a    y
        */

        float x_new = (float) (x * Math.cos(rad) - y * Math.sin(rad));
        float y_new = (float) (x * Math.sin(rad) + y * Math.cos(rad));

        x = x_new;
        y = y_new;
    }


    public Vector2(Vector2 v) {
        this.x = v.x;
        this.y = v.y;
    }

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2 scaled(float s) {
        return new Vector2(x * s, y * s);
    }

    public Vector2 scale(float s) {
        this.x *= s;
        this.y *= s;
        return this;
    }

    public float distance(float x, float y) {
        float dx = this.x - x;
        float dy = this.y - y;

        return (float)Math.sqrt(dx*dx + dy*dy);
    }

    public float distance(Vector2 v) {
        return distance(v.x, v.y);
    }

    public boolean clockwise(Vector2 n1, Vector2 n2) {
        return x * n1.y - y * n1.x + y * n2.x
                - x * n2.y + n1.x * n2.y - n2.x * n1.y < 0;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public float dot(Vector2 v) {
        return this.x * v.x + this.y * v.y;
    }

    public float length() {
        return (float) Math.sqrt(dot(this));
    }

    /**
     * Returns the angle in radians between this vector and a given vector.
     * The angle is from range [0, PI[.
     *
     * @param v A given vector.
     * @return The angle between this vector and the given vector.
     */
    public float angle(Vector2 v) {
        return (float) Math.acos(dot(v) / length() / v.length());
    }

    /**
     * Returns the relative angle in radians between this vector and a given vector.
     * If the angle is a straight angle, then either the value PI or -PI is returned.
     * Otherwise the angle is from range ]-PI, PI[, where positive value denotes a
     * counterclockwise turn.
     *
     * @param v A given vector.
     * @return The relative angle between this vector and the given vector.
     */
    public float relativeAngle(Vector2 v) {
        float angle = (float) Math.atan2(v.y,v.x) - (float) Math.atan2(this.y,this.x);
        if(angle <= -Math.PI) {
            angle += 2 * Math.PI;
        }
        else if(angle > Math.PI) {
            angle -= 2 * Math.PI;
        }
        return angle;
    }


    public Vector2 normalVector() {
        return new Vector2(-y, x);
    }

    public void normalize() {
        scale(1.0f / (length() + 0.000000001f));
    }


    public void increase(Vector2 v) {
        this.x += v.x;
        this.y += v.y;
    }

    public void decrease(Vector2 v) {
        this.x -= v.x;
        this.y -= v.y;
    }

    public Vector2 minus(Vector2 v) {
        return new Vector2(this.x - v.x, this.y - v.y);
    }

    public Vector2 plus(Vector2 v) {
        return new Vector2(this.x + v.x, this.y + v.y);
    }

    public static Vector2 rightVector = new Vector2(1, 0);

    public Vector2 normalized() {
        Vector2 v = new Vector2(this.x, this.y);
        v.normalize();
        return v;
    }

    public static void scale(Vector2 input, float scale, Vector2 output) {
        output.x = input.x * scale;
        output.y = input.y * scale;
    }

    public static void sum(Vector2 term1, Vector2 term2, Vector2 output) {
        output.x = term1.x + term2.x;
        output.y = term1.y + term2.y;
    }

    public void copyFrom(Vector2 v) {
        this.x = v.x;
        this.y = v.y;
    }

    public float distanceSquared(Vector2 w) {
        return (x-w.x)*(x-w.x)+(y-w.y)*(y-w.y);
    }

    public static float dot(Vector2 v, Vector2 w) {
        return v.x*w.x + v.y*w.y;
    }

    public float lengthSquared() {
        return x*x+y*y;
    }

    public float crossProduct(Vector2 v) {
        return (x * v.y - y * v.x);
    }

    /**
     * Rotates vector v by angle, and stores the result in vector result. Does not change the state of vector v.
     * @param v
     * @param angle
     * @param result
     */
    public static void rotate(Vector2 v, float angle, Vector2 result) {
        float x_new = (float) (v.x * Math.cos(angle) - v.y * Math.sin(angle));
        float y_new = (float) (v.x * Math.sin(angle) + v.y * Math.cos(angle));

        result.x = x_new;
        result.y = y_new;
    }

    public Vector2 increase(float dx, float dy) {
        x += dx;
        y += dy;
        return this;
    }

    public void approach(Vector2 target, float dt, float base) {
        float diff_x = target.x - x;
        float diff_y = target.y - y;
        float dx = (float)(1 - Math.pow(base, dt)) * diff_x;
        float dy = (float)(1 - Math.pow(base, dt)) * diff_y;
        increase(dx, dy);
    }

    public Vector2 scale(Vector2 v) {
        x *= v.x;
        y *= v.y;
        return this;
    }
}
