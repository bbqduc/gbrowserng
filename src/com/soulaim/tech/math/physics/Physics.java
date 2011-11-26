package com.soulaim.tech.math.physics;

import com.soulaim.tech.math.Math2D;
import com.soulaim.tech.math.Vector2;
import com.soulaim.tech.math.lines.LineSegment;
import com.soulaim.tech.math.polygon.Polygon;

import java.util.ArrayList;

public class Physics {

    private static LineSegment collisionSegment = new LineSegment();
    private static float segmentDistance;

    private static Vector2 tmp1 = new Vector2();
    private static Vector2 tmp2 = new Vector2();

    // copy to ensure data integrity
    private static void storeCollision(float dist, Vector2 vector2, Vector2 p1, Vector2 p2) {
        if(segmentDistance > dist) {
            segmentDistance = dist;
            collisionSegment.p1.copyFrom(p1);
            collisionSegment.p2.copyFrom(p2);
        }
    }


    public static LineSegment collides(Polygon polygon, LineSegment lineSegment) {
        segmentDistance = 40000;

        Vector2 p1 = lineSegment.p1;
        Vector2 p2 = lineSegment.p2;

        for(int i=0; i<polygon.getPolygonVertices().size()-1; ++i) {
            Vector2 intersection = Math2D.lineSegmentIntersect(p1, p2, polygon.getPolygonVertices().get(i), polygon.getPolygonVertices().get(i + 1));
            if(intersection != null)
            {
                float dist = intersection.distance(p1);
                storeCollision(dist, intersection, polygon.getPolygonVertices().get(i), polygon.getPolygonVertices().get(i+1));
            }
        }

        Vector2 intersection = Math2D.lineSegmentIntersect(p1, p2, polygon.getPolygonVertices().get(polygon.getPolygonVertices().size() - 1), polygon.getPolygonVertices().get(0));
        if(intersection != null)
        {
            float dist = intersection.distance(p1);
            storeCollision(dist, intersection, polygon.getPolygonVertices().get(polygon.getPolygonVertices().size() - 1), polygon.getPolygonVertices().get(0));
        }

        if(segmentDistance < 39999)
            return collisionSegment;
        return null;
    }

    public static Vector2 collides(Polygon poly1, Polygon poly2) {

        ArrayList<Vector2> vertices1 = poly1.getPolygonVertices();
        ArrayList<Vector2> vertices2 = poly2.getPolygonVertices();

        for(int i=0; i<vertices1.size() - 1; ++i) {
            for(int k=0; k<vertices2.size() - 1; ++k) {
                Vector2 point = Math2D.lineSegmentIntersect(vertices1.get(i), vertices1.get(i+1), vertices2.get(k), vertices2.get(k+1));
                if(point != null)
                    return point;
            }
            Vector2 point = Math2D.lineSegmentIntersect(vertices1.get(i), vertices1.get(i+1), vertices2.get(vertices2.size()-1), vertices2.get(0));
            if(point != null)
                return point;
        }

        int i = vertices1.size()-1;
        for(int k=0; k<vertices2.size() - 1; ++k) {
            Vector2 point = Math2D.lineSegmentIntersect(vertices1.get(i), vertices1.get(0), vertices2.get(k), vertices2.get(k+1));
            if(point != null)
                return point;
        }
        Vector2 point = Math2D.lineSegmentIntersect(vertices1.get(i), vertices1.get(0), vertices2.get(vertices2.size()-1), vertices2.get(0));
        if(point != null)
            return point;

        return null;
    }

    public static CollisionPoint collides(Motion motion, Polygon shape, Polygon poly) {

        ArrayList<Vector2> vertices1 = shape.getPolygonVertices();
        ArrayList<Vector2> vertices2 = poly.getPolygonVertices();


        for(int i=0; i<vertices1.size() - 1; ++i) {
            for(int k=0; k<vertices2.size() - 1; ++k) {
                Vector2.rotate(vertices1.get(i), motion.angle, tmp1);   tmp1.increase(motion.position);
                Vector2.rotate(vertices1.get(i+1), motion.angle, tmp2); tmp2.increase(motion.position);
                Vector2 point = Math2D.lineSegmentIntersect(tmp1, tmp2, vertices2.get(k), vertices2.get(k+1));
                if(point != null)
                    return new CollisionPoint(new LineSegment(vertices2.get(k), vertices2.get(k+1)), point);
            }
            Vector2.rotate(vertices1.get(i), motion.angle, tmp1);   tmp1.increase(motion.position);
            Vector2.rotate(vertices1.get(i+1), motion.angle, tmp2); tmp2.increase(motion.position);
            Vector2 point = Math2D.lineSegmentIntersect(tmp1, tmp2, vertices2.get(vertices2.size()-1), vertices2.get(0));
            if(point != null)
                return new CollisionPoint(new LineSegment(vertices2.get(vertices2.size()-1), vertices2.get(0)), point);
        }

        int i = vertices1.size()-1;
        for(int k=0; k<vertices2.size() - 1; ++k) {
            Vector2.rotate(vertices1.get(i), motion.angle, tmp1);   tmp1.increase(motion.position);
            Vector2.rotate(vertices1.get(0), motion.angle, tmp2); tmp2.increase(motion.position);
            Vector2 point = Math2D.lineSegmentIntersect(tmp1, tmp2, vertices2.get(k), vertices2.get(k+1));
            if(point != null)
                return new CollisionPoint(new LineSegment(vertices2.get(k), vertices2.get(k+1)), point);
        }
        Vector2.rotate(vertices1.get(i), motion.angle, tmp1);   tmp1.increase(motion.position);
        Vector2.rotate(vertices1.get(0), motion.angle, tmp2); tmp2.increase(motion.position);
        Vector2 point = Math2D.lineSegmentIntersect(tmp1, tmp2, vertices2.get(vertices2.size()-1), vertices2.get(0));
        if(point != null)
            return new CollisionPoint(new LineSegment(vertices2.get(vertices2.size()-1), vertices2.get(0)), point);

        return null;
    }

    public static float basicCollision(Motion to, Motion from, Vector2 p1, Vector2 p2) {
        to.position.copyFrom(from.position);

        float x_normal = (p1.y - p2.y);
        float y_normal = (p2.x - p1.x);
        float length = (float) Math.sqrt(x_normal * x_normal + y_normal * y_normal);
        x_normal /= length;
        y_normal /= length;

        float dot = to.velocity.x * x_normal + y_normal * to.velocity.y;
        to.velocity.x -= dot * 2 * x_normal;
        to.velocity.y -= dot * 2 * y_normal;

        return dot;
    }

}
