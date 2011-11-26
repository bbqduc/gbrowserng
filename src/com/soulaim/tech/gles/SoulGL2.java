package com.soulaim.tech.gles;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public interface SoulGL2 {
    public static final int GL_ACTIVE_TEXTURE = 34016;
    public static final int GL_DEPTH_BUFFER_BIT = 256;
    public static final int GL_STENCIL_BUFFER_BIT = 1024;
    public static final int GL_COLOR_BUFFER_BIT = 16384;
    public static final int GL_FALSE = 0;
    public static final int GL_TRUE = 1;
    public static final int GL_POINTS = 0;
    public static final int GL_LINES = 1;
    public static final int GL_LINE_LOOP = 2;
    public static final int GL_LINE_STRIP = 3;
    public static final int GL_TRIANGLES = 4;
    public static final int GL_TRIANGLE_STRIP = 5;
    public static final int GL_TRIANGLE_FAN = 6;
    public static final int GL_ZERO = 0;
    public static final int GL_ONE = 1;
    public static final int GL_SRC_COLOR = 768;
    public static final int GL_ONE_MINUS_SRC_COLOR = 769;
    public static final int GL_SRC_ALPHA = 770;
    public static final int GL_ONE_MINUS_SRC_ALPHA = 771;
    public static final int GL_DST_ALPHA = 772;
    public static final int GL_ONE_MINUS_DST_ALPHA = 773;
    public static final int GL_DST_COLOR = 774;
    public static final int GL_ONE_MINUS_DST_COLOR = 775;
    public static final int GL_SRC_ALPHA_SATURATE = 776;
    public static final int GL_FUNC_ADD = 32774;
    public static final int GL_BLEND_EQUATION = 32777;
    public static final int GL_BLEND_EQUATION_RGB = 32777;
    public static final int GL_BLEND_EQUATION_ALPHA = 34877;
    public static final int GL_FUNC_SUBTRACT = 32778;
    public static final int GL_FUNC_REVERSE_SUBTRACT = 32779;
    public static final int GL_BLEND_DST_RGB = 32968;
    public static final int GL_BLEND_SRC_RGB = 32969;
    public static final int GL_BLEND_DST_ALPHA = 32970;
    public static final int GL_BLEND_SRC_ALPHA = 32971;
    public static final int GL_CONSTANT_COLOR = 32769;
    public static final int GL_ONE_MINUS_CONSTANT_COLOR = 32770;
    public static final int GL_CONSTANT_ALPHA = 32771;
    public static final int GL_ONE_MINUS_CONSTANT_ALPHA = 32772;
    public static final int GL_BLEND_COLOR = 32773;
    public static final int GL_ARRAY_BUFFER = 34962;
    public static final int GL_ELEMENT_ARRAY_BUFFER = 34963;
    public static final int GL_ARRAY_BUFFER_BINDING = 34964;
    public static final int GL_ELEMENT_ARRAY_BUFFER_BINDING = 34965;
    public static final int GL_STREAM_DRAW = 35040;
    public static final int GL_STATIC_DRAW = 35044;
    public static final int GL_DYNAMIC_DRAW = 35048;
    public static final int GL_BUFFER_SIZE = 34660;
    public static final int GL_BUFFER_USAGE = 34661;
    public static final int GL_CURRENT_VERTEX_ATTRIB = 34342;
    public static final int GL_FRONT = 1028;
    public static final int GL_BACK = 1029;
    public static final int GL_FRONT_AND_BACK = 1032;
    public static final int GL_TEXTURE_2D = 3553;
    public static final int GL_CULL_FACE = 2884;
    public static final int GL_BLEND = 3042;
    public static final int GL_DITHER = 3024;
    public static final int GL_STENCIL_TEST = 2960;
    public static final int GL_DEPTH_TEST = 2929;
    public static final int GL_SCISSOR_TEST = 3089;
    public static final int GL_POLYGON_OFFSET_FILL = 32823;
    public static final int GL_SAMPLE_ALPHA_TO_COVERAGE = 32926;
    public static final int GL_SAMPLE_COVERAGE = 32928;
    public static final int GL_NO_ERROR = 0;
    public static final int GL_INVALID_ENUM = 1280;
    public static final int GL_INVALID_VALUE = 1281;
    public static final int GL_INVALID_OPERATION = 1282;
    public static final int GL_OUT_OF_MEMORY = 1285;
    public static final int GL_CW = 2304;
    public static final int GL_CCW = 2305;
    public static final int GL_LINE_WIDTH = 2849;
    public static final int GL_ALIASED_POINT_SIZE_RANGE = 33901;
    public static final int GL_ALIASED_LINE_WIDTH_RANGE = 33902;
    public static final int GL_CULL_FACE_MODE = 2885;
    public static final int GL_FRONT_FACE = 2886;
    public static final int GL_DEPTH_RANGE = 2928;
    public static final int GL_DEPTH_WRITEMASK = 2930;
    public static final int GL_DEPTH_CLEAR_VALUE = 2931;
    public static final int GL_DEPTH_FUNC = 2932;
    public static final int GL_STENCIL_CLEAR_VALUE = 2961;
    public static final int GL_STENCIL_FUNC = 2962;
    public static final int GL_STENCIL_FAIL = 2964;
    public static final int GL_STENCIL_PASS_DEPTH_FAIL = 2965;
    public static final int GL_STENCIL_PASS_DEPTH_PASS = 2966;
    public static final int GL_STENCIL_REF = 2967;
    public static final int GL_STENCIL_VALUE_MASK = 2963;
    public static final int GL_STENCIL_WRITEMASK = 2968;
    public static final int GL_STENCIL_BACK_FUNC = 34816;
    public static final int GL_STENCIL_BACK_FAIL = 34817;
    public static final int GL_STENCIL_BACK_PASS_DEPTH_FAIL = 34818;
    public static final int GL_STENCIL_BACK_PASS_DEPTH_PASS = 34819;
    public static final int GL_STENCIL_BACK_REF = 36003;
    public static final int GL_STENCIL_BACK_VALUE_MASK = 36004;
    public static final int GL_STENCIL_BACK_WRITEMASK = 36005;
    public static final int GL_VIEWPORT = 2978;
    public static final int GL_SCISSOR_BOX = 3088;
    public static final int GL_COLOR_CLEAR_VALUE = 3106;
    public static final int GL_COLOR_WRITEMASK = 3107;
    public static final int GL_UNPACK_ALIGNMENT = 3317;
    public static final int GL_PACK_ALIGNMENT = 3333;
    public static final int GL_MAX_TEXTURE_SIZE = 3379;
    public static final int GL_MAX_VIEWPORT_DIMS = 3386;
    public static final int GL_SUBPIXEL_BITS = 3408;
    public static final int GL_RED_BITS = 3410;
    public static final int GL_GREEN_BITS = 3411;
    public static final int GL_BLUE_BITS = 3412;
    public static final int GL_ALPHA_BITS = 3413;
    public static final int GL_DEPTH_BITS = 3414;
    public static final int GL_STENCIL_BITS = 3415;
    public static final int GL_POLYGON_OFFSET_UNITS = 10752;
    public static final int GL_POLYGON_OFFSET_FACTOR = 32824;
    public static final int GL_TEXTURE_BINDING_2D = 32873;
    public static final int GL_SAMPLE_BUFFERS = 32936;
    public static final int GL_SAMPLES = 32937;
    public static final int GL_SAMPLE_COVERAGE_VALUE = 32938;
    public static final int GL_SAMPLE_COVERAGE_INVERT = 32939;
    public static final int GL_NUM_COMPRESSED_TEXTURE_FORMATS = 34466;
    public static final int GL_COMPRESSED_TEXTURE_FORMATS = 34467;
    public static final int GL_DONT_CARE = 4352;
    public static final int GL_FASTEST = 4353;
    public static final int GL_NICEST = 4354;
    public static final int GL_GENERATE_MIPMAP_HINT = 33170;
    public static final int GL_BYTE = 5120;
    public static final int GL_UNSIGNED_BYTE = 5121;
    public static final int GL_SHORT = 5122;
    public static final int GL_UNSIGNED_SHORT = 5123;
    public static final int GL_INT = 5124;
    public static final int GL_UNSIGNED_INT = 5125;
    public static final int GL_FLOAT = 5126;
    public static final int GL_FIXED = 5132;
    public static final int GL_DEPTH_COMPONENT = 6402;
    public static final int GL_ALPHA = 6406;
    public static final int GL_RGB = 6407;
    public static final int GL_RGBA = 6408;
    public static final int GL_LUMINANCE = 6409;
    public static final int GL_LUMINANCE_ALPHA = 6410;
    public static final int GL_UNSIGNED_SHORT_4_4_4_4 = 32819;
    public static final int GL_UNSIGNED_SHORT_5_5_5_1 = 32820;
    public static final int GL_UNSIGNED_SHORT_5_6_5 = 33635;
    public static final int GL_FRAGMENT_SHADER = 35632;
    public static final int GL_VERTEX_SHADER = 35633;
    public static final int GL_MAX_VERTEX_ATTRIBS = 34921;
    public static final int GL_MAX_VERTEX_UNIFORM_VECTORS = 36347;
    public static final int GL_MAX_VARYING_VECTORS = 36348;
    public static final int GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS = 35661;
    public static final int GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS = 35660;
    public static final int GL_MAX_TEXTURE_IMAGE_UNITS = 34930;
    public static final int GL_MAX_FRAGMENT_UNIFORM_VECTORS = 36349;
    public static final int GL_SHADER_TYPE = 35663;
    public static final int GL_DELETE_STATUS = 35712;
    public static final int GL_LINK_STATUS = 35714;
    public static final int GL_VALIDATE_STATUS = 35715;
    public static final int GL_ATTACHED_SHADERS = 35717;
    public static final int GL_ACTIVE_UNIFORMS = 35718;
    public static final int GL_ACTIVE_UNIFORM_MAX_LENGTH = 35719;
    public static final int GL_ACTIVE_ATTRIBUTES = 35721;
    public static final int GL_ACTIVE_ATTRIBUTE_MAX_LENGTH = 35722;
    public static final int GL_SHADING_LANGUAGE_VERSION = 35724;
    public static final int GL_CURRENT_PROGRAM = 35725;
    public static final int GL_NEVER = 512;
    public static final int GL_LESS = 513;
    public static final int GL_EQUAL = 514;
    public static final int GL_LEQUAL = 515;
    public static final int GL_GREATER = 516;
    public static final int GL_NOTEQUAL = 517;
    public static final int GL_GEQUAL = 518;
    public static final int GL_ALWAYS = 519;
    public static final int GL_KEEP = 7680;
    public static final int GL_REPLACE = 7681;
    public static final int GL_INCR = 7682;
    public static final int GL_DECR = 7683;
    public static final int GL_INVERT = 5386;
    public static final int GL_INCR_WRAP = 34055;
    public static final int GL_DECR_WRAP = 34056;
    public static final int GL_VENDOR = 7936;
    public static final int GL_RENDERER = 7937;
    public static final int GL_VERSION = 7938;
    public static final int GL_EXTENSIONS = 7939;
    public static final int GL_NEAREST = 9728;
    public static final int GL_LINEAR = 9729;
    public static final int GL_NEAREST_MIPMAP_NEAREST = 9984;
    public static final int GL_LINEAR_MIPMAP_NEAREST = 9985;
    public static final int GL_NEAREST_MIPMAP_LINEAR = 9986;
    public static final int GL_LINEAR_MIPMAP_LINEAR = 9987;
    public static final int GL_TEXTURE_MAG_FILTER = 10240;
    public static final int GL_TEXTURE_MIN_FILTER = 10241;
    public static final int GL_TEXTURE_WRAP_S = 10242;
    public static final int GL_TEXTURE_WRAP_T = 10243;
    public static final int GL_TEXTURE = 5890;
    public static final int GL_TEXTURE_CUBE_MAP = 34067;
    public static final int GL_TEXTURE_BINDING_CUBE_MAP = 34068;
    public static final int GL_TEXTURE_CUBE_MAP_POSITIVE_X = 34069;
    public static final int GL_TEXTURE_CUBE_MAP_NEGATIVE_X = 34070;
    public static final int GL_TEXTURE_CUBE_MAP_POSITIVE_Y = 34071;
    public static final int GL_TEXTURE_CUBE_MAP_NEGATIVE_Y = 34072;
    public static final int GL_TEXTURE_CUBE_MAP_POSITIVE_Z = 34073;
    public static final int GL_TEXTURE_CUBE_MAP_NEGATIVE_Z = 34074;
    public static final int GL_MAX_CUBE_MAP_TEXTURE_SIZE = 34076;
    public static final int GL_TEXTURE0 = 33984;
    public static final int GL_TEXTURE1 = 33985;
    public static final int GL_TEXTURE2 = 33986;
    public static final int GL_TEXTURE3 = 33987;
    public static final int GL_TEXTURE4 = 33988;
    public static final int GL_TEXTURE5 = 33989;
    public static final int GL_TEXTURE6 = 33990;
    public static final int GL_TEXTURE7 = 33991;
    public static final int GL_TEXTURE8 = 33992;
    public static final int GL_TEXTURE9 = 33993;
    public static final int GL_TEXTURE10 = 33994;
    public static final int GL_TEXTURE11 = 33995;
    public static final int GL_TEXTURE12 = 33996;
    public static final int GL_TEXTURE13 = 33997;
    public static final int GL_TEXTURE14 = 33998;
    public static final int GL_TEXTURE15 = 33999;
    public static final int GL_TEXTURE16 = 34000;
    public static final int GL_TEXTURE17 = 34001;
    public static final int GL_TEXTURE18 = 34002;
    public static final int GL_TEXTURE19 = 34003;
    public static final int GL_TEXTURE20 = 34004;
    public static final int GL_TEXTURE21 = 34005;
    public static final int GL_TEXTURE22 = 34006;
    public static final int GL_TEXTURE23 = 34007;
    public static final int GL_TEXTURE24 = 34008;
    public static final int GL_TEXTURE25 = 34009;
    public static final int GL_TEXTURE26 = 34010;
    public static final int GL_TEXTURE27 = 34011;
    public static final int GL_TEXTURE28 = 34012;
    public static final int GL_TEXTURE29 = 34013;
    public static final int GL_TEXTURE30 = 34014;
    public static final int GL_TEXTURE31 = 34015;
    public static final int GL_REPEAT = 10497;
    public static final int GL_CLAMP_TO_EDGE = 33071;
    public static final int GL_MIRRORED_REPEAT = 33648;
    public static final int GL_FLOAT_VEC2 = 35664;
    public static final int GL_FLOAT_VEC3 = 35665;
    public static final int GL_FLOAT_VEC4 = 35666;
    public static final int GL_INT_VEC2 = 35667;
    public static final int GL_INT_VEC3 = 35668;
    public static final int GL_INT_VEC4 = 35669;
    public static final int GL_BOOL = 35670;
    public static final int GL_BOOL_VEC2 = 35671;
    public static final int GL_BOOL_VEC3 = 35672;
    public static final int GL_BOOL_VEC4 = 35673;
    public static final int GL_FLOAT_MAT2 = 35674;
    public static final int GL_FLOAT_MAT3 = 35675;
    public static final int GL_FLOAT_MAT4 = 35676;
    public static final int GL_SAMPLER_2D = 35678;
    public static final int GL_SAMPLER_CUBE = 35680;
    public static final int GL_VERTEX_ATTRIB_ARRAY_ENABLED = 34338;
    public static final int GL_VERTEX_ATTRIB_ARRAY_SIZE = 34339;
    public static final int GL_VERTEX_ATTRIB_ARRAY_STRIDE = 34340;
    public static final int GL_VERTEX_ATTRIB_ARRAY_TYPE = 34341;
    public static final int GL_VERTEX_ATTRIB_ARRAY_NORMALIZED = 34922;
    public static final int GL_VERTEX_ATTRIB_ARRAY_POINTER = 34373;
    public static final int GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING = 34975;
    public static final int GL_IMPLEMENTATION_COLOR_READ_TYPE = 35738;
    public static final int GL_IMPLEMENTATION_COLOR_READ_FORMAT = 35739;
    public static final int GL_COMPILE_STATUS = 35713;
    public static final int GL_INFO_LOG_LENGTH = 35716;
    public static final int GL_SHADER_SOURCE_LENGTH = 35720;
    public static final int GL_SHADER_COMPILER = 36346;
    public static final int GL_SHADER_BINARY_FORMATS = 36344;
    public static final int GL_NUM_SHADER_BINARY_FORMATS = 36345;
    public static final int GL_LOW_FLOAT = 36336;
    public static final int GL_MEDIUM_FLOAT = 36337;
    public static final int GL_HIGH_FLOAT = 36338;
    public static final int GL_LOW_INT = 36339;
    public static final int GL_MEDIUM_INT = 36340;
    public static final int GL_HIGH_INT = 36341;
    public static final int GL_FRAMEBUFFER = 36160;
    public static final int GL_RENDERBUFFER = 36161;
    public static final int GL_RGBA4 = 32854;
    public static final int GL_RGB5_A1 = 32855;
    public static final int GL_RGB565 = 36194;
    public static final int GL_DEPTH_COMPONENT16 = 33189;
    public static final int GL_STENCIL_INDEX = 6401;
    public static final int GL_STENCIL_INDEX8 = 36168;
    public static final int GL_RENDERBUFFER_WIDTH = 36162;
    public static final int GL_RENDERBUFFER_HEIGHT = 36163;
    public static final int GL_RENDERBUFFER_INTERNAL_FORMAT = 36164;
    public static final int GL_RENDERBUFFER_RED_SIZE = 36176;
    public static final int GL_RENDERBUFFER_GREEN_SIZE = 36177;
    public static final int GL_RENDERBUFFER_BLUE_SIZE = 36178;
    public static final int GL_RENDERBUFFER_ALPHA_SIZE = 36179;
    public static final int GL_RENDERBUFFER_DEPTH_SIZE = 36180;
    public static final int GL_RENDERBUFFER_STENCIL_SIZE = 36181;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE = 36048;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME = 36049;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL = 36050;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE = 36051;
    public static final int GL_COLOR_ATTACHMENT0 = 36064;
    public static final int GL_DEPTH_ATTACHMENT = 36096;
    public static final int GL_STENCIL_ATTACHMENT = 36128;
    public static final int GL_NONE = 0;
    public static final int GL_FRAMEBUFFER_COMPLETE = 36053;
    public static final int GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT = 36054;
    public static final int GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT = 36055;
    public static final int GL_FRAMEBUFFER_INCOMPLETE_DIMENSIONS = 36057;
    public static final int GL_FRAMEBUFFER_UNSUPPORTED = 36061;
    public static final int GL_FRAMEBUFFER_BINDING = 36006;
    public static final int GL_RENDERBUFFER_BINDING = 36007;
    public static final int GL_MAX_RENDERBUFFER_SIZE = 34024;
    public static final int GL_INVALID_FRAMEBUFFER_OPERATION = 1286;

    public void glActiveTexture(int i);
    
    public void glAttachShader(int i, int i1);
    
    public void glBindAttribLocation(int program, int index, String attribName);
    
    public void glBindBuffer(int i, int i1);
    
    public void glBindFramebuffer(int i, int i1);
    
    public void glBindRenderbuffer(int i, int i1);
    
    public void glBindTexture(int i, int i1);
    
    public void glBlendColor(float v, float v1, float v2, float v3);
    
    public void glBlendEquation(int i);
    
    public void glBlendEquationSeparate(int i, int i1);
    
    public void glBlendFunc(int i, int i1);
    
    public void glBlendFuncSeparate(int i, int i1, int i2, int i3);
    
    public void glBufferData(int i, int i1, Buffer buffer, int i2);
    
    public void glBufferSubData(int i, int i1, int i2, Buffer buffer);
    
    public int glCheckFramebufferStatus(int i);
    
    public void glClear(int i);
    
    public void glClearColor(float v, float v1, float v2, float v3);
    
    public void glClearDepthf(float v);
    
    public void glClearStencil(int i);
    
    public void glColorMask(boolean b, boolean b1, boolean b2, boolean b3);
    
    public void glCompileShader(int i);
    
    public void glCompressedTexImage2D(int i, int i1, int i2, int i3, int i4, int i5, int i6, Buffer buffer);
    
    public void glCompressedTexSubImage2D(int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7, Buffer buffer);
    
    public void glCopyTexImage2D(int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7);
    
    public void glCopyTexSubImage2D(int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7);
    
    public int glCreateProgram();
    
    public int glCreateShader(int i);
    
    public void glCullFace(int i);
    
    public void glDeleteBuffers(int i, int[] ints, int i1);
    
    public void glDeleteBuffers(int i, IntBuffer intBuffer);
    
    public void glDeleteFramebuffers(int i, int[] ints, int i1);
    
    public void glDeleteFramebuffers(int i, IntBuffer intBuffer);
    
    public void glDeleteProgram(int i);
    
    public void glDeleteRenderbuffers(int i, int[] ints, int i1);
    
    public void glDeleteRenderbuffers(int i, IntBuffer intBuffer);
    
    public void glDeleteShader(int i);
    
    public void glDeleteTextures(int i, int[] ints, int i1);
    
    public void glDeleteTextures(int i, IntBuffer intBuffer);
    
    public void glDepthFunc(int i);
    
    public void glDepthMask(boolean b);
    
    public void glDepthRangef(float v, float v1);
    
    public void glDetachShader(int i, int i1);
    
    public void glDisable(int i);
    
    public void glDisableVertexAttribArray(int i);
    
    public void glDrawArrays(int mode, int first, int count);
    
    public void glDrawElements(int mode, int count, int type, Buffer indices);
    
    public void glEnable(int i);
    
    public void glEnableVertexAttribArray(int i);
    
    public void glFinish();
    
    public void glFlush();
    
    public void glFramebufferRenderbuffer(int i, int i1, int i2, int i3);
    
    public void glFramebufferTexture2D(int i, int i1, int i2, int i3, int i4);
    
    public void glFrontFace(int i);
    
    public void glGenBuffers(int i, int[] ints, int i1);
    
    public void glGenBuffers(int i, IntBuffer intBuffer);
    
    public void glGenerateMipmap(int i);
    
    public void glGenFramebuffers(int i, int[] ints, int i1);
    
    public void glGenFramebuffers(int i, IntBuffer intBuffer);
    
    public void glGenRenderbuffers(int i, int[] ints, int i1);
    
    public void glGenRenderbuffers(int i, IntBuffer intBuffer);
    
    public void glGenTextures(int i, int[] ints, int i1);
    
    public void glGenTextures(int i, IntBuffer intBuffer);
    
    public void glGetActiveAttrib(int i, int i1, int i2, int[] ints, int i3, int[] ints1, int i4, int[] ints2, int i5, byte[] bytes, int i6);
    
    public void glGetActiveAttrib(int i, int i1, int i2, IntBuffer intBuffer, IntBuffer intBuffer1, IntBuffer intBuffer2, byte b);
    
    public void glGetActiveUniform(int i, int i1, int i2, int[] ints, int i3, int[] ints1, int i4, int[] ints2, int i5, byte[] bytes, int i6);
    
    public void glGetActiveUniform(int i, int i1, int i2, IntBuffer intBuffer, IntBuffer intBuffer1, IntBuffer intBuffer2, byte b);
    
    public void glGetAttachedShaders(int i, int i1, int[] ints, int i2, int[] ints1, int i3);
    
    public void glGetAttachedShaders(int i, int i1, IntBuffer intBuffer, IntBuffer intBuffer1);
    
    public int glGetAttribLocation(int i, String s);
    
    public void glGetBooleanv(int i, boolean[] booleans, int i1);
    
    public void glGetBooleanv(int i, IntBuffer intBuffer);
    
    public void glGetBufferParameteriv(int i, int i1, int[] ints, int i2);
    
    public void glGetBufferParameteriv(int i, int i1, IntBuffer intBuffer);
    
    public int glGetError();
    
    public void glGetFloatv(int i, float[] floats, int i1);
    
    public void glGetFloatv(int i, FloatBuffer floatBuffer);
    
    public void glGetFramebufferAttachmentParameteriv(int i, int i1, int i2, int[] ints, int i3);
    
    public void glGetFramebufferAttachmentParameteriv(int i, int i1, int i2, IntBuffer intBuffer);
    
    public void glGetIntegerv(int i, int[] ints, int i1);
    
    public void glGetIntegerv(int i, IntBuffer intBuffer);
    
    public void glGetProgramiv(int i, int i1, int[] ints, int i2);
    
    public void glGetProgramiv(int i, int i1, IntBuffer intBuffer);
    
    public String glGetProgramInfoLog(int i);
    
    public void glGetRenderbufferParameteriv(int i, int i1, int[] ints, int i2);
    
    public void glGetRenderbufferParameteriv(int i, int i1, IntBuffer intBuffer);
    
    public void glGetShaderiv(int i, int i1, int[] ints, int i2);
    
    public void glGetShaderiv(int i, int i1, IntBuffer intBuffer);
    
    public String glGetShaderInfoLog(int i);
    
    public void glGetShaderPrecisionFormat(int i, int i1, int[] ints, int i2, int[] ints1, int i3);
    
    public void glGetShaderPrecisionFormat(int i, int i1, IntBuffer intBuffer, IntBuffer intBuffer1);
    
    public void glGetShaderSource(int i, int i1, int[] ints, int i2, byte[] bytes, int i3);
    
    public void glGetShaderSource(int i, int i1, IntBuffer intBuffer, byte b);
    
    public String glGetString(int i);
    
    public void glGetTexParameterfv(int i, int i1, float[] floats, int i2);
    
    public void glGetTexParameterfv(int i, int i1, FloatBuffer floatBuffer);
    
    public void glGetTexParameteriv(int i, int i1, int[] ints, int i2);
    
    public void glGetTexParameteriv(int i, int i1, IntBuffer intBuffer);
    
    public void glGetUniformfv(int i, int i1, float[] floats, int i2);
    
    public void glGetUniformfv(int i, int i1, FloatBuffer floatBuffer);
    
    public void glGetUniformiv(int i, int i1, int[] ints, int i2);
    
    public void glGetUniformiv(int i, int i1, IntBuffer intBuffer);
    
    public int glGetUniformLocation(int i, String s);
    
    public void glGetVertexAttribfv(int i, int i1, float[] floats, int i2);
    
    public void glGetVertexAttribfv(int i, int i1, FloatBuffer floatBuffer);
    
    public void glGetVertexAttribiv(int i, int i1, int[] ints, int i2);
    
    public void glGetVertexAttribiv(int i, int i1, IntBuffer intBuffer);
    
    public void glHint(int i, int i1);
    
    public boolean glIsBuffer(int i);
    
    public boolean glIsEnabled(int i);
    
    public boolean glIsFramebuffer(int i);
    
    public boolean glIsProgram(int i);
    
    public boolean glIsRenderbuffer(int i);
    
    public boolean glIsShader(int i);
    
    public boolean glIsTexture(int i);
    
    public void glLineWidth(float v);
    
    public void glLinkProgram(int i);
    
    public void glPixelStorei(int i, int i1);
    
    public void glPolygonOffset(float v, float v1);
    
    public void glReadPixels(int i, int i1, int i2, int i3, int i4, int i5, Buffer buffer);
    
    public void glReleaseShaderCompiler();
    
    public void glRenderbufferStorage(int i, int i1, int i2, int i3);
    
    public void glSampleCoverage(float v, boolean b);
    
    public void glScissor(int i, int i1, int i2, int i3);
    
    public void glShaderBinary(int i, int[] ints, int i1, int i2, Buffer buffer, int i3);
    
    public void glShaderBinary(int i, IntBuffer intBuffer, int i1, Buffer buffer, int i2);
    
    public void glShaderSource(int i, String s);
    
    public void glStencilFunc(int i, int i1, int i2);
    
    public void glStencilFuncSeparate(int i, int i1, int i2, int i3);
    
    public void glStencilMask(int i);
    
    public void glStencilMaskSeparate(int i, int i1);
    
    public void glStencilOp(int i, int i1, int i2);
    
    public void glStencilOpSeparate(int i, int i1, int i2, int i3);
    
    public void glTexImage2D(int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7, Buffer buffer);
    
    public void glTexParameterf(int i, int i1, float v);
    
    public void glTexParameterfv(int i, int i1, float[] floats, int i2);
    
    public void glTexParameterfv(int i, int i1, FloatBuffer floatBuffer);
    
    public void glTexParameteri(int i, int i1, int i2);
    
    public void glTexParameteriv(int i, int i1, int[] ints, int i2);
    
    public void glTexParameteriv(int i, int i1, IntBuffer intBuffer);
    
    public void glTexSubImage2D(int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7, Buffer buffer);
    
    public void glUniform1f(int i, float v);
    
    public void glUniform1fv(int i, int i1, float[] floats, int i2);
    
    public void glUniform1fv(int i, int i1, FloatBuffer floatBuffer);
    
    public void glUniform1i(int i, int i1);
    
    public void glUniform1iv(int i, int i1, int[] ints, int i2);
    
    public void glUniform1iv(int i, int i1, IntBuffer intBuffer);
    
    public void glUniform2f(int i, float v, float v1);
    
    public void glUniform2fv(int i, int i1, float[] floats, int i2);
    
    public void glUniform2fv(int i, int i1, FloatBuffer floatBuffer);
    
    public void glUniform2i(int i, int i1, int i2);
    
    public void glUniform2iv(int i, int i1, int[] ints, int i2);
    
    public void glUniform2iv(int i, int i1, IntBuffer intBuffer);
    
    public void glUniform3f(int i, float v, float v1, float v2);
    
    public void glUniform3fv(int i, int i1, float[] floats, int i2);
    
    public void glUniform3fv(int i, int i1, FloatBuffer floatBuffer);
    
    public void glUniform3i(int i, int i1, int i2, int i3);
    
    public void glUniform3iv(int i, int i1, int[] ints, int i2);
    
    public void glUniform3iv(int i, int i1, IntBuffer intBuffer);
    
    public void glUniform4f(int i, float v, float v1, float v2, float v3);
    
    public void glUniform4fv(int i, int i1, float[] floats, int i2);
    
    public void glUniform4fv(int i, int i1, FloatBuffer floatBuffer);
    
    public void glUniform4i(int i, int i1, int i2, int i3, int i4);
    
    public void glUniform4iv(int i, int i1, int[] ints, int i2);
    
    public void glUniform4iv(int i, int i1, IntBuffer intBuffer);
    
    public void glUniformMatrix2fv(int i, int i1, boolean b, float[] floats, int i2);
    
    public void glUniformMatrix2fv(int i, int i1, boolean b, FloatBuffer floatBuffer);
    
    public void glUniformMatrix3fv(int i, int i1, boolean b, float[] floats, int i2);
    
    public void glUniformMatrix3fv(int i, int i1, boolean b, FloatBuffer floatBuffer);
    
    public void glUniformMatrix4fv(int i, int i1, boolean b, float[] floats, int i2);
    
    public void glUniformMatrix4fv(int i, int i1, boolean b, FloatBuffer floatBuffer);
    
    public void glUseProgram(int i);
    
    public void glValidateProgram(int i);
    
    public void glVertexAttrib1f(int i, float v);
    
    public void glVertexAttrib1fv(int i, float[] floats, int i1);
    
    public void glVertexAttrib1fv(int i, FloatBuffer floatBuffer);
    
    public void glVertexAttrib2f(int i, float v, float v1);
    
    public void glVertexAttrib2fv(int i, float[] floats, int i1);
    
    public void glVertexAttrib2fv(int i, FloatBuffer floatBuffer);
    
    public void glVertexAttrib3f(int i, float v, float v1, float v2);
    
    public void glVertexAttrib3fv(int i, float[] floats, int i1);
    
    public void glVertexAttrib3fv(int i, FloatBuffer floatBuffer);
    
    public void glVertexAttrib4f(int i, float v, float v1, float v2, float v3);
    
    public void glVertexAttrib4fv(int i, float[] floats, int i1);
    
    public void glVertexAttrib4fv(int i, FloatBuffer floatBuffer);
    
    public void glVertexAttribPointer(int indx, int size, int type, boolean normalized, int stride, Buffer ptr);
    
    public void glViewport(int i, int i1, int i2, int i3);
}
