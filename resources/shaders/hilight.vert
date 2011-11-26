#if __VERSION__ != 110
precision mediump float;
#endif

uniform mat4 modelMatrix;

uniform vec2 texAdd;
uniform vec2 center;
uniform float time;

attribute vec2 vertexPosition;

varying vec2 texCoord;
varying float dist;
varying float weird_val;
varying float power;

void main()
{
    weird_val = sin( (vertexPosition.x * 3.0 + vertexPosition.y * 3.0) * 5.0 * time ) * 0.5 + 0.5;
    vec4 vertexPos = modelMatrix * vec4(vertexPosition, 0.0, 1.0);

    vec2 textureCoordinate = vertexPosition * vec2(0.5, -0.5) + vec2(0.5, 0.5);
    texCoord = textureCoordinate / 8.0;
    texCoord += texAdd;

    power = cos( (vertexPosition.x * 4.0 + vertexPosition.y * 4.0) + time * 9.0) * 0.3 + 0.7;

    dist = length(vertexPos.xy - center) * 100.0;
    if(dist > 0.01)
    {
        dist = 1.0;
    }

    gl_Position = vertexPos;
}
