#if __VERSION__ != 110
precision mediump float;
#endif

uniform sampler2D texture;
uniform float hilight_value;
uniform vec4 color1;
uniform vec4 color2;

varying vec2 texCoord;
varying float dist;
varying float weird_val;
varying float power;

void main()
{
    float too_far = min((1.0 - dist) * 5.0, 1.0);
    float val = (dist - 0.92);
    float outside = clamp((1.0 - val * val * 85.0) * hilight_value, 0.0, 1.0);

    float alpha = too_far * dist * weird_val * 0.8 * (hilight_value * 3.0 + 2.7);

    vec4 image_color = texture2D(texture, texCoord);
    vec4 p1 = vec4(color1.rgb * weird_val, 1.0);
    vec4 p2 = vec4(1.0, 1.0, 1.0, hilight_value * 0.3 + 0.7);

    vec4 term1 = p1 * (1.0 - image_color.a) * alpha;
    vec4 term2 = image_color * image_color.a * p2 * color2;

    vec4 color = term1 + term2;

    float op = outside * power;
    color += op * vec4(1.0, 0.5, 0.25, 1.0);

    gl_FragColor = color;
}

