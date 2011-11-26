package com.soulaim.tech.math.polygon;

import com.soulaim.tech.gles.primitives.Mesh;
import com.soulaim.tech.math.Math2D;
import com.soulaim.tech.math.Vector2;

import java.util.ArrayList;

public class PolygonTesselator {

    private Polygon polygon;

    private ArrayList<Integer> unhandled = new ArrayList<Integer>();
    private ArrayList<Triangle> triangles = new ArrayList<Triangle>();


    public int numUnhandled() {
        return unhandled.size();
    }

    public ArrayList<Triangle> getTriangles() {
        return triangles;
    }

    public int numTriangles() {
        return triangles.size();
    }

    public static class Triangle {
        public int a, b, c;
        public Triangle(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public String toString() {
            return "Triangle: " + a + ", " + b + ", " + c;
        }
    }

    private void resetUnhandledMarkers() {
        unhandled.clear();
        for(int i=0; i<polygon.vertices.size(); ++i)
            unhandled.add(i);
    }

    private void resetForPostProcess() {
        triangles.clear();
        resetUnhandledMarkers();
    }

    public PolygonTesselator() {
    }

    public Mesh tesselate(Polygon polygon) {
        this.polygon = polygon;
        resetForPostProcess();
        triangulate();
        Mesh mesh = buildMeshData();
        resetForPostProcess(); // free memory
        return mesh;
    }


    public Vector2 getVertexUnhandled(int i) {
        return polygon.vertices.get(unhandled.get(i));
    }

    private void addTriangle(int earNode, int t1, int t2) {
        triangles.add(new Triangle(unhandled.get(t1), unhandled.get(earNode), unhandled.get(t2)));

        System.out.println("Removing node: " + unhandled.get(earNode) + " - " + unhandled.get(t1) + ", " + unhandled.get(t2));
        unhandled.remove(earNode);
    }

    private Mesh buildMeshData() {

        Mesh polyMesh = new Mesh(polygon.numVertices(), numTriangles() * 3);

        // build vertex buffer
        for(Vector2 v : polygon.getPolygonVertices()) {
            polyMesh.putVertex(v.x, v.y, 0.0f);
            polyMesh.putUVCoord(v.x * 0.2f, v.y * 0.2f);
        }

        // build index buffer
        for(Triangle t : getTriangles()) {
            polyMesh.putTriangleIndices(t.a, t.b, t.c);
        }

        polyMesh.getIndices().rewind();
        polyMesh.getTexCoords().rewind();
        polyMesh.getVertices().rewind();

        return polyMesh;
    }

    private boolean isEar(int t1, int t2, int t3) {
        for(int i=0; i<numUnhandled(); ++i) {
            if(i == t1 || i == t2 || i == t3)
                continue;
            if(Math2D.pointInTriangle(getVertexUnhandled(i), getVertexUnhandled(t1), getVertexUnhandled(t2), getVertexUnhandled(t3))) {
                return false;
            }
        }
        return true;
    }

    private void triangulate() {
        while(!triangulateOneStep());
    }

    private boolean triangulateOneStep() {

        // Note: If there is enough love in the polygon, we are done.
        if( numUnhandled() <3 ) {
            System.out.println("Done triangulating polygon.");
            return true;
        }

        for(int i=0; i<numUnhandled()-2; ++i) {
            if(Math2D.pointLeftOfLine(getVertexUnhandled(i + 1), getVertexUnhandled(i), getVertexUnhandled(i + 2))) {
            }
            else {
                if(isEar(i+1, i, i+2)) {
                    addTriangle(i+1, i, i+2);
                    return false;
                }
            }
        }

        int k = numUnhandled();
        if(Math2D.pointLeftOfLine(getVertexUnhandled(k - 1), getVertexUnhandled(k - 2), getVertexUnhandled(0))) {

        }
        else {
            if(isEar(k-1, k-2, 0)) {
                addTriangle(k-1, k-2, 0);
                return false;
            }
        }

        if(Math2D.pointLeftOfLine(getVertexUnhandled(0), getVertexUnhandled(k - 1), getVertexUnhandled(1))) {

        }
        else {
            if(isEar(0, k-1, 1)) {
                addTriangle(0, k-1, 1);
                return false;
            }
        }

        throw new RuntimeException("Could not triangulate given polygon");
    }
}