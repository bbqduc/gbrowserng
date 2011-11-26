package com.soulaim.tech.math.polygon;

import com.soulaim.tech.math.Vector2;
import com.soulaim.tech.math.polygon.Polygon;
import com.soulaim.tech.math.polygon.PolygonEditor;

public class Shape {

    public static Polygon makeCircle(float radius, int vertices) {
        Polygon shape = new Polygon();
        PolygonEditor editor = new PolygonEditor(shape);
        for(int i=0; i<vertices; ++i) {
            double angle = -i * 2.0 * Math.PI / vertices;
            editor.insertVertex(new Vector2(0.5f * radius * (float)Math.sin(angle), 0.5f * radius * (float)Math.cos(angle)));
        }
        return shape;
    }

    public static Polygon makeBox(float edgeLength) {
        Polygon shape = new Polygon();
        PolygonEditor editor = new PolygonEditor(shape);
        editor.insertVertex(new Vector2(-edgeLength*0.5f, +edgeLength*0.5f));
        editor.insertVertex(new Vector2(-edgeLength*0.5f, -edgeLength*0.5f));
        editor.insertVertex(new Vector2(+edgeLength*0.5f, -edgeLength*0.5f));
        editor.insertVertex(new Vector2(+edgeLength*0.5f, +edgeLength*0.5f));
        return shape;
    }

}
