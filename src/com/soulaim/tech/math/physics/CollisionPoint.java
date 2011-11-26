package com.soulaim.tech.math.physics;

import com.soulaim.tech.math.Vector2;
import com.soulaim.tech.math.lines.LineSegment;

public class CollisionPoint {
    public LineSegment lineSegment = new LineSegment();
    public Vector2 point = new Vector2();

    public CollisionPoint(LineSegment lineSegment, Vector2 point) {
        this.lineSegment = lineSegment;
        this.point = point;
    }
}
