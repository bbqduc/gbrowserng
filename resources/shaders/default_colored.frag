#if __VERSION__ != 110
precision mediump float;
#endif

uniform vec4 color;

void main() {
    gl_FragColor = color;
}
