package com.soulaim.tech.math.lines;

import com.soulaim.tech.math.Vector2;

public class LineSegment {
    public Vector2 p1 = new Vector2();
    public Vector2 p2 = new Vector2();

    public LineSegment() {

    }

    public LineSegment(Vector2 a, Vector2 b) {
        p1.copyFrom(a);
        p2.copyFrom(b);
    }

    public LineSegment(LineSegment lineSegment) {
        p1.copyFrom(lineSegment.p1);
        p2.copyFrom(lineSegment.p2);
    }

    public Vector2 normal() {
        Vector2 line = new Vector2(p1.x - p2.x, p1.y - p2.y);
        return new Vector2(-line.y, line.x);
    }
}
