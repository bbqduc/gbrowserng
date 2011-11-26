uniform mat4 viewMatrix;
uniform mat4 modelMatrix;
uniform mat4 projectionMatrix;

attribute vec2 vertexPosition;

varying vec4 vertexPos;

void main() {
    vertexPos = modelMatrix * vec4(vertexPosition,0,1);
    gl_Position = projectionMatrix * viewMatrix * vertexPos;
}
