package com.soulaim.tech.math.physics;

import com.soulaim.tech.math.Vector2;

public class MotionIntegrator {

    private MotionDerivative motionDerivate = new MotionDerivative();

    private Vector2 tmp = new Vector2();
    private Vector2 tmp2 = new Vector2();

    NewtonianObject object;
    Motion tmp_state = new Motion();

    public MotionIntegrator(NewtonianObject object) {
        this.object = object;
    }

    private MotionDerivative evaluate(Motion initial, float t, float dt, MotionDerivative derivative) {
        tmp_state.clear();

        Vector2.scale(derivative.velocity, dt, tmp);
        Vector2.sum(initial.position, tmp, tmp_state.position);

        Vector2.scale(derivative.acceleration, dt, tmp);
        Vector2.sum(initial.velocity, tmp, tmp_state.velocity);

        tmp_state.angularVelocity = initial.angularVelocity + derivative.angularAcceleration * dt;
        tmp_state.angle = initial.angle + derivative.angularVelocity * dt;

        MotionDerivative output = new MotionDerivative();
        output.velocity = tmp_state.velocity;
        output.acceleration = object.acceleration(tmp_state, dt);
        output.angularVelocity = tmp_state.angularVelocity;
        output.angularAcceleration = object.angularAcceleration(tmp_state, dt);

        return output;
    }

    public void motionTick(Motion state, float t, float dt)
    {
        // TODO: This memory reservation per frame could be eliminated
        MotionDerivative a = evaluate(state, t, 0.0f, motionDerivate);
        MotionDerivative b = evaluate(state, t+dt*0.5f, dt*0.5f, a);
        MotionDerivative c = evaluate(state, t+dt*0.5f, dt*0.5f, b);
        MotionDerivative d = evaluate(state, t+dt, dt, c);

        Vector2.sum(b.velocity, c.velocity, tmp);
        Vector2.scale(tmp, 2.0f, tmp);
        Vector2.sum(tmp, a.velocity, tmp);
        Vector2.sum(tmp, d.velocity, tmp);
        Vector2.scale(tmp, 1.0f/6.0f * dt, tmp);

        Vector2.sum(b.acceleration, c.acceleration, tmp2);
        Vector2.scale(tmp2, 2.0f, tmp2);
        Vector2.sum(tmp2, a.acceleration, tmp2);
        Vector2.sum(tmp2, d.acceleration, tmp2);
        Vector2.scale(tmp2, 1.0f/6.0f * dt, tmp2);

        float deltaAngle = dt * (2 * (b.angularVelocity + c.angularVelocity) + a.angularVelocity + d.angularVelocity) / 6.0f;
        float deltaAngleVelocity = dt * ((2 * b.angularAcceleration + c.angularAcceleration) + a.angularAcceleration + d.angularAcceleration) / 6.0f;

        state.position.increase(tmp);
        state.velocity.increase(tmp2);
        state.angle += deltaAngle;
        state.angularVelocity += deltaAngleVelocity;
    }
}
