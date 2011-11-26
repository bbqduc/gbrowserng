#if __VERSION__ != 110
precision mediump float;
#endif


uniform vec4 innerColor;
uniform vec4 outerColor;

uniform float radius;
uniform float lifetime;

// varying vec4 vertexPos;
varying float dist;

float fade(float t)
{
    //return t*t*t*(t*(t*6.0-15.0)+10.0);
    return t;
}


void main()
{
    float val = fade(dist);
    vec4 clr = innerColor * (1.0 - val) + outerColor * val;
    clr.a *= fade(lifetime) * (1.0 - dist);
    // clr.a *= (1.0-dist);
    gl_FragColor = clr;
}

