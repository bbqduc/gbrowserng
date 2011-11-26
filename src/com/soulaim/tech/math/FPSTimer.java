package com.soulaim.tech.math;

public class FPSTimer {
	private long lastUpdate;
	private int desiredFPS;

	public FPSTimer(int desiredFPS) {
		this.desiredFPS = desiredFPS;
        reset();
	}

	private long time() {
		return System.nanoTime();
	}

	public void update() {
		lastUpdate = time();
	}

    public void reset() {
        lastUpdate = Long.MIN_VALUE;
    }

    public float getDT() {
        long currentTime = time();
        float dt = (currentTime - lastUpdate) / 1000000000.0f;
        lastUpdate = currentTime;
        return Math.min(dt, 0.1f);
    }
}

