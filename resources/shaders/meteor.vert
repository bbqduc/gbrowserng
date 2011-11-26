#if __VERSION__ != 110
precision mediump float;
#endif

uniform mat4 modelMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform vec3 pos;

uniform float time;

attribute vec2 vertexPosition;

varying float dist;

void main()
{
    float d = 0.2;
    float val = (1.0 - d) + d * (sin(vertexPosition.x * 15.0 + time * 3.0) + cos(vertexPosition.y * 15.0 + time * 3.0) + 2.0);
    vec4 vertexPos = modelMatrix * vec4(vertexPosition * val, 0.0, 1.0);
    dist = length(vertexPos.xy - pos.xy);

    if(dist > 0.01)
        dist = 1.0;

    gl_Position = projectionMatrix * viewMatrix * vertexPos;
}
