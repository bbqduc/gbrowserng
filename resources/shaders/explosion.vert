#if __VERSION__ != 110
precision mediump float;
#endif

uniform mat4 viewMatrix;
uniform mat4 modelMatrix;
uniform mat4 projectionMatrix;

uniform float lifetime;
uniform float time;
uniform float radius;
uniform float d;

uniform vec2 center;

attribute vec2 vertexPosition;
varying float dist;

void main()
{
    float val = (1.0 - d) + d * (sin(vertexPosition.x * 15.0 + time * 3.0) + cos(vertexPosition.y * 15.0 + time * 3.0) + 2.0);

    vec4 vertexPos = modelMatrix * vec4(vertexPosition * lifetime * lifetime * val * radius, 0.0, 1.0);
    dist = length(vertexPos.xy - center) * 100.0;

    if(dist > 0.01)
    {
        dist = 1.0;
    }

    gl_Position = projectionMatrix * viewMatrix * vertexPos;
}

