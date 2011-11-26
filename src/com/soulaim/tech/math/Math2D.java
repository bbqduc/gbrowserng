package com.soulaim.tech.math;

import com.soulaim.tech.math.lines.LineSegment;
import com.soulaim.tech.math.polygon.Polygon;

public class Math2D {
    private static Vector2 v0 = new Vector2();
    private static Vector2 v1 = new Vector2();
    private static Vector2 v2 = new Vector2();

    private static Vector2 intersection = new Vector2();

    public static Vector2 lineSegmentIntersect(Vector2 a1, Vector2 a2, Vector2 b1, Vector2 b2) {
        float r_top = (a1.y - b1.y) * (b2.x - b1.x) - (a1.x - b1.x) * (b2.y - b1.y);
        float s_top = (a1.y - b1.y) * (a2.x - a1.x) - (a1.x - b1.x) * (a2.y - a1.y);
        float bot = (a2.x - a1.x) * (b2.y - b1.y) - (a2.y - a1.y) * (b2.x - b1.x);
        float r = r_top / bot;
        float s = s_top / bot;

        intersection.x = a1.x + r * (a2.x - a1.x);
        intersection.y = a1.y + r * (a2.y - a1.y);

        if ((r >= 0 && r <= 1) && (s >= 0 && s <= 1))
            return new Vector2(intersection);
        return null;
    }

    public static boolean pointInTriangle(Vector2 point, Vector2 t1, Vector2 t2, Vector2 t3) {

        v0.x = t3.x - t1.x;
        v0.y = t3.y - t1.y;

        v1.x = t2.x - t1.x;
        v1.y = t2.y - t1.y;

        v2.x = point.x - t1.x;
        v2.y = point.y - t1.y;

        float dot00 = v0.x * v0.x + v0.y * v0.y;
        float dot01 = v0.x * v1.x + v0.y * v1.y;
        float dot02 = v0.x * v2.x + v0.y * v2.y;
        float dot11 = v1.x * v1.x + v1.y * v1.y;
        float dot12 = v1.x * v2.x + v1.y * v2.y;

        // Compute barycentric coordinates
        float invDenom = 1.0f / (dot00 * dot11 - dot01 * dot01);
        float u = (dot11 * dot02 - dot01 * dot12) * invDenom;
        float v = (dot00 * dot12 - dot01 * dot02) * invDenom;

        // Check if point is in triangle
        return (u > 0) && (v > 0) && (u + v < 1);
    }

    // TODO: Return value is determined by the side on which the point lies, but not sure which value is returned.
    public static boolean pointLeftOfLine(Vector2 point, Vector2 t1, Vector2 t2) {
        return (t2.x - t1.x) * (point.y - t1.y) - (t2.y - t1.y) * (point.x - t1.x) > 0;
    }

    /*
    public static boolean pointInPolygon(Polygon p, float x, float y) {
        for(PolygonTesselator.Triangle t : p.getTriangles()) {
            if(pointInTriangle(new Vector2(x, y), p.getVertexDirect(t.a), p.getVertexDirect(t.b), p.getVertexDirect(t.c)))
                return true;
        }
        return false;
    }
    */

    public static boolean pointNearPolygonVertex(Polygon p, float x, float y, float range) {
        Vector2 point = new Vector2(x, y);
        for(int i=0; i<p.numVertices(); ++i) {
            if(point.distance(p.getVertexDirect(i)) < range)
                return true;
        }

        return false;
    }

    public static float pointDistanceLineSegment(Vector2 p, Vector2 v, Vector2 w) {
        float l2 = v.distanceSquared(w);

        float t = Vector2.dot(p.minus(v), w.minus(v)) / l2;
        if (t < 0.0) return p.distance(v);       // Beyond the 'v' end of the segment
        else if (t > 1.0) return p.distance(w);  // Beyond the 'w' end of the segment
        Vector2 projection = v.plus((w.minus(v)).scale(t));  // Projection falls on the segment
        return p.distance(projection);
    }

    public static Vector2 lineSegmentIntersect(LineSegment segment1, LineSegment segment2) {
        return lineSegmentIntersect(segment1.p1, segment1.p2, segment2.p1, segment2.p2);
    }
}
