package com.soulaim.tech.math.polygon;

import com.soulaim.tech.math.Vector2;
import com.soulaim.tech.math.lines.LineSegment;

import javax.sound.sampled.Line;
import java.util.ArrayList;

public class Polygon {

    protected ArrayList<Vector2> vertices = new ArrayList<Vector2>();

    public int numVertices() {
        return vertices.size();
    }

    public ArrayList<Vector2> getPolygonVertices() {
        return vertices;
    }

    public Vector2 getVertexDirect(int i) {
        return vertices.get(i);
    }

    public ArrayList<LineSegment> getBoundary() {

        if(vertices.size() < 3)
            return null;

        ArrayList<LineSegment> result = new ArrayList<LineSegment>();
        for(int i=0; i<vertices.size()-1; ++i) {
            result.add(new LineSegment(vertices.get(i), vertices.get(i+1)));
        }
        result.add(new LineSegment(vertices.get(vertices.size()-1), vertices.get(0)));

        return result;
    }
}