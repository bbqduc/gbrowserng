#if __VERSION__ != 110
precision mediump float;
#endif

uniform mat4 modelMatrix;

uniform vec2 mouse;
uniform float time;
uniform float hilight;

attribute vec2 vertexPosition;
varying float dist;
varying float power;
varying float dotproduct;

void main()
{
    power = sin( 4.0 * time + (vertexPosition.x * 3.0 + vertexPosition.y * 3.0) ) * 0.5 + 0.5;
    float dot = mouse.x * vertexPosition.x + mouse.y * vertexPosition.y;
    dot = dot * dot * dot * dot * dot * dot * dot * dot * dot;
    dotproduct = max(0.0, dot);

    vec4 vertexPos = modelMatrix * vec4(vertexPosition, 0.0, 1.0);

    dist = length(vertexPosition.xy);
    if(dist > 0.001) {
        dist = 1.0;
    }

    gl_Position = vertexPos;
}

