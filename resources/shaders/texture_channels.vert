
uniform mat4 modelMatrix;
uniform float time;
attribute vec2 vertexPosition;

varying vec2 texCoord;
varying float power1;
varying float power2;

void main()
{
    vec4 vertexPos = modelMatrix * vec4(vertexPosition, 0.0, 1.0);
    texCoord = vertexPosition * vec2(0.5, -0.5) + vec2(0.5, 0.5);

    power1 = cos( (vertexPosition.x * 4.0 + vertexPosition.y * 4.0) + time * 9.0) * 1.2 + 1.5;
    power2 = sin( (vertexPosition.x * 3.0 + vertexPosition.y * 3.0) * 5.0 * time ) * 1.2 + 1.5;

    gl_Position = vertexPos;
}