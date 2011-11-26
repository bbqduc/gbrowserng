package com.soulaim.tech.gles.view;

import com.soulaim.tech.math.physics.Motion;
import com.soulaim.tech.math.Frustum;

public class CameraFrustum extends Camera {

    private float halfWidth;
    private float halfHeight;
    private Frustum frustum;
    private float aspectRatio;

    private Motion followTarget = null;

    public void tick(float dt) {
        float x = followTarget.position.x + followTarget.velocity.x * 1.2f;
        float y = followTarget.position.y + followTarget.velocity.y * 1.2f;
        float my_x = this.getX();
        float my_y = this.getY();

        float dx = (x - my_x) * dt * 2;
        float dy = (y - my_y) * dt * 2;

        float speed = (float) Math.sqrt(dx*dx + dy*dy);
        float targetDepth = 15 * (1 + speed);
        float my_z = this.getZ();
        my_z += (targetDepth - my_z) * dt * 2;

        this.setZ(Math.min(my_z, 23));
        this.setPosition(my_x + dx, my_y + dy);
    }

    public void setFollowTarget(Motion target) {
        followTarget = target;
    }

    public float getHalfWidth() {
        return halfWidth;
    }

    public float getHalfHeight() {
        return halfHeight;
    }

    public CameraFrustum() {
        super();

        frustum = new Frustum();
        frustum.near = 1f;
        frustum.far = 25.0f;

        setAspectRatio(1.0f);
    }

    public CameraFrustum(Motion target) {
        this();
        this.followTarget = target;
    }

    public void setAspectRatio(float aspectRatio) {
        this.aspectRatio = aspectRatio;

        setHalves();

        frustum.left = -frustum.near; // 90 degree fov horizontally.
        frustum.right = frustum.near; // 90 degree fov horizontally.

        frustum.bottom = frustum.left / aspectRatio;
        frustum.top = frustum.right / aspectRatio;
    }

    private void setHalves() {
        this.halfWidth = z; // 90 degree fov horizontally.
        this.halfHeight = z / aspectRatio;
    }

    public float getAspectRatio() {
        return aspectRatio;
    }

    @Override
    public void setZ(float z) {
        super.setZ(z);
        setHalves();
    }

    public Frustum getFrustum() {
        return frustum;
    }
}
