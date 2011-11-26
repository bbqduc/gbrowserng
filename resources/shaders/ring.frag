#if __VERSION__ != 110
precision mediump float;
#endif


uniform vec4 innerColor;
uniform vec4 outerColor;

uniform float radius;
uniform float lifetime;

varying float dist;
varying float weird_val;

float fade(float t)
{
  // return t*t*t*(t*(t*6.0-15.0)+10.0); // Improved fade, yields C2-continuous noise
  return t;
}


void main()
{
    float val = fade(dist);
    vec4 clr = innerColor * (1.0 - val) + outerColor * val;
    clr.r *= weird_val;
    clr.a *= val * val * lifetime;
    gl_FragColor = clr;
}
