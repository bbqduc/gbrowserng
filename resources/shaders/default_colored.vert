uniform mat4 MVPMatrix;
attribute vec4 vertexPosition;

void main() {
    gl_Position = MVPMatrix * vertexPosition;
}
