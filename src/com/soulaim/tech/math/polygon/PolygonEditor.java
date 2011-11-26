package com.soulaim.tech.math.polygon;

import com.soulaim.tech.math.Vector2;

public class PolygonEditor {

    private Polygon polygon;

    public PolygonEditor(Polygon polygon) {
        this.polygon = polygon;
    }

    public void deleteVertex(int i) {
        polygon.vertices.remove(i);
    }

    public void insertVertexAfter(int i, Vector2 v) {
        polygon.vertices.add(i, v);
    }

    public void insertVertex(Vector2 v) {
        polygon.vertices.add(v);
    }
}
