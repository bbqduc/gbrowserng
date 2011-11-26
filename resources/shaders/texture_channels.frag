#if __VERSION__ != 110
precision mediump float;
#endif

uniform sampler2D texture;
varying vec2 texCoord;
varying float power1;
varying float power2;

void main()
{
    vec4 image_clr = texture2D(texture, texCoord);
    vec4 term_r = vec4(1.0, 1.0, 1.0, 1.0) * image_clr.r;
    vec4 term_g = vec4(1.0, 0.5, 0.3, 1.0) * image_clr.g;
    vec4 term_b = vec4(0.2, 0.5, 1.0, 1.0) * image_clr.b;

    vec4 clr = term_r * power1 * power2 * 0.2 + term_g * power1 + term_b * power2;
    gl_FragColor = clr;
}

