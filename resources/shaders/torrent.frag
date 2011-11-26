#if __VERSION__ != 110
precision mediump float;
#endif

uniform vec4 primaryColor;
uniform vec4 secondaryColor;

uniform float lifetime;
varying vec2 positionGradient;

void main()
{
    float val = (positionGradient.y - lifetime);
    float height_val = max(0.0, 1.0 - 10.0 * val * val);
    float width_val = 1.0 - positionGradient.x * positionGradient.x;
    float alpha = width_val * height_val;

    gl_FragColor = vec4(0.0, height_val, width_val, alpha * (1.0 - (lifetime * 0.5 + 0.5)));
}

