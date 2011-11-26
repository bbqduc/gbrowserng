#if __VERSION__ != 110
precision mediump float;
#endif


uniform vec4 color;
uniform vec2 center;
uniform float radius;

uniform float time;

varying vec4 vertexPos;

void main() {
    float distance = length(vertexPos.xy - center.xy);
    float scaledDistance = (radius - distance) / radius;

    float timeFactor = (0.6 * time + 0.4);
    float alpha = scaledDistance * timeFactor;

    gl_FragColor = vec4(color.xyz, alpha);
}
