package com.soulaim.tech.math.physics;

public class Material {

    public static final Material WOOD = makeWood();
    public static final Material PLASTIC = makePlastic();
    public static final Material RUBBER = makeRubber();
    public static final Material SAND = makeSand();

    private static Material makeSand() {
        Material sand = new Material();
        sand.elasticity = 0.2f;
        sand.friction = 0.4f;
        return sand;
    }

    private static Material makeRubber() {
        Material rubber = new Material();
        rubber.elasticity = 0.9f;
        rubber.friction = 0.1f;
        return rubber;
    }

    private static Material makePlastic() {
        Material plastic = new Material();
        plastic.elasticity = 0.30f;
        plastic.friction = 0.1f;
        return plastic;
    }

    private static Material makeWood() {
        Material wood = new Material();
        wood.elasticity = 0.2f;
        wood.friction = 0.1f;
        return wood;
    }

    private float elasticity;
    private float friction;

    public void setElasticity(float elasticity) {
        this.elasticity = elasticity;
    }

    public void setFriction(float friction) {
        this.friction = friction;
    }

    public float getFriction() {
        return friction;
    }

    public float getElasticity() {
        return elasticity;
    }
}
