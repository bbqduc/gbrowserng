
#if __VERSION__ != 110
precision mediump float;
#endif

uniform float hilight;
varying float dist;
varying float power;
varying float dotproduct;

void main() {
    float d = dist * 10.0 - 9.0;
    if(d < 0.0) discard;

    d = (d - 0.5) * 2.0;
    d = 1.0 - d * d;

    float v = hilight;
    vec4 color1 = vec4(d, 0.0, 0.0, 1.0);
    vec4 color2 = vec4(0.0, v, 0.2 + 0.4 * d * power, 1.0);
    gl_FragColor = dotproduct * color1 + (1.0 - dotproduct) * color2;
}
