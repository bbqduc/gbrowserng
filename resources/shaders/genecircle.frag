
#if __VERSION__ != 110
precision mediump float;
#endif

uniform float hilight;
varying float dist;
varying float power;

void main() {
    float d = dist * 10.0 - 9.0;
    if(d < 0.0) discard;

    d = (d - 0.5) * 2.0;
    d = 1.0 - d * d;

    float v = hilight;
    gl_FragColor = vec4(d * v * power, d * v * power, 0.2 + 0.8 * d * power, 1.0);
}
