package com.soulaim.tech.math.physics;

import com.soulaim.tech.math.Vector2;

public interface NewtonianObject {
    Vector2 acceleration(Motion state, float dt);
    float angularAcceleration(Motion state, float dt);
}
