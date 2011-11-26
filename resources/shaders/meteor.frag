#if __VERSION__ != 110
precision mediump float;
#endif

uniform vec3 pos;
uniform float time;

varying float dist;

void main()
{
    gl_FragColor = vec4(1.0, 1.0, 0.3 * time, (1.0 - dist));
}

