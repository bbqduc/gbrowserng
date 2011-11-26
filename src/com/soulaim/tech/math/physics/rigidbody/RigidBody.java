package com.soulaim.tech.math.physics.rigidbody;

import com.soulaim.tech.math.Vector2;
import com.soulaim.tech.math.lines.LineSegment;
import com.soulaim.tech.math.physics.Material;
import com.soulaim.tech.math.physics.Motion;
import com.soulaim.tech.math.polygon.Polygon;

/**
 * Assumes a uniform mass distribution.
 */
public class RigidBody {

    private final Material material;
    private final Motion motion;
    private Vector2 rotationalVelocityTmp = new Vector2();

    private final LineSegment tmp = new LineSegment();

    public RigidBody(Motion motion, Material material) {
        this.motion = motion;
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }

    public LineSegment segmentToWorldCoordinates(LineSegment segment) {
        tmp.p1.copyFrom(segment.p1);
        tmp.p2.copyFrom(segment.p2);

        tmp.p1.rotate(motion.angle); tmp.p1.increase(motion.position);
        tmp.p2.rotate(motion.angle); tmp.p2.increase(motion.position);
        return tmp;
    }

    public void applyForce(Vector2 force) {
        motion.velocity.increase(force.scale(1.0f / mass));
    }

    /**
     * @param r Vector from origin of the object to the point of interest.
     * @return rotational velocity at given point.
     */
    public Vector2 rotationalVelocityAtPoint(Vector2 r) {
        rotationalVelocityTmp.x = -r.y * motion.angularVelocity;
        rotationalVelocityTmp.y = +r.x * motion.angularVelocity;
        return rotationalVelocityTmp;
    }

    public void applyForce(Vector2 point, Vector2 force) {
        point.decrease(centerOfMass);
        motion.velocity.increase(force.scale(1.0f / mass));
        motion.angularVelocity -= force.crossProduct(point) / momentOfInertia;
    }

    public Polygon shape;
    Vector2 centerOfMass = new Vector2();
    float mass = 1.0f;
    private float momentOfInertia = 10 * (2 * mass / 12);

    public float getMomentOfInertia() {
        return momentOfInertia;
    }

    public Motion getMotion() {
        return motion;
    }

    public float getMass() {
        return mass;
    }
}
