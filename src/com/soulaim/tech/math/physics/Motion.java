package com.soulaim.tech.math.physics;

import com.soulaim.tech.math.Vector2;

public class Motion {
    public Vector2 position = new Vector2();
    public Vector2 velocity = new Vector2();
    public float angle;
    public float angularVelocity;

    public void clear() {
        position.x = position.y = 0;
        velocity.x = position.y = 0;
        angle = angularVelocity = 0;
    }

    public void copyState(Motion motion) {
        position.copyFrom(motion.position);
        velocity.copyFrom(motion.velocity);
        angle = motion.angle;
        angularVelocity = motion.angularVelocity;
    }
}
