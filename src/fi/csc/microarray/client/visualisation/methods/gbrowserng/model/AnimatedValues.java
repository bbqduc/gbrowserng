package fi.csc.microarray.client.visualisation.methods.gbrowserng.model;

import com.soulaim.tech.math.Vector2;

import java.util.concurrent.ConcurrentHashMap;

public class AnimatedValues {

    final ConcurrentHashMap<String, Vector2> animatedValues = new ConcurrentHashMap<String, Vector2>();

    public float getAnimatedValue(String name) {
        if(animatedValues.containsKey(name))
            return animatedValues.get(name).x;
        return 0;
    }

    public void setAnimatedValue(String name, float value) {
        if(animatedValues.containsKey(name)) {
            animatedValues.get(name).y = value;
        }
        else {
            animatedValues.put(name, new Vector2(value, value));
        }
    }

    public void tick(float dt) {
        for(Vector2 v : animatedValues.values()) {
            v.x += (1.0f - Math.pow(0.1f, dt)) * (v.y - v.x);
        }
    }
}

