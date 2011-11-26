package com.soulaim.tech.math;

/**
 * This class defines methods for manipulating 4x4 matrices.
 *
 * The matrices are stored in a column-major order.
 */
public class MatrixUtils {
    /**
     * Multiplies two matrices together, and stores the result in the third
     * matrix, that is, result = lhs x rhs.
     *
     * @param result The result of the multiplication.
     * @param lhs The left-hand-side multiplicand.
     * @param rhs The right-hand-side multiplicand.
     */
    public static void multiplyMM(float[] result, float[] lhs, float[] rhs) {

        for(int i = 0; i < 4; ++i) {
            for(int j = 0; j < 4; ++j) {
                float sum = 0.0f;
                for(int k = 0; k < 4; ++k) {
                    sum += lhs[4 * k + i] * rhs[4 * j + k];
                }
                temp3[j * 4 + i] = sum;
            }
        }

        System.arraycopy(temp3, 0, result, 0, 16);
    }

    /**
     * Multiply a 4 element vector by a 4x4 matrix and store the result in a 4
     * element column vector, that is, result = lhs x rhs.
     *
     * @param resultVec The float array that holds the result vector.
     * @param lhsMat The float array that holds the left-hand-side matrix.
     * @param rhsVec The float array that holds the right-hand-side vector.
     */
    public static void multiplyMV(float[] resultVec, float[] lhsMat, float[] rhsVec) {
        for(int i = 0; i < 4; ++i) {
            float sum = 0.0f;
            for(int j = 0; j < 4; ++j) {
                sum += lhsMat[4 * j + i] * rhsVec[j];
            }
            resultVec[i] = sum;
        }
    }

    /**
     * Computes an orthographic projection matrix.
     *
     * @param result returns the result
     * @param left The left plane
     * @param right The right plane
     * @param bottom The bottom plane
     * @param top The top plane
     * @param near The near plane
     * @param far The far plane
     */
    public static void orthoM(float[] result, float left,
                              float right, float bottom, float top, float near, float far) {
        if (left == right) {
            throw new IllegalArgumentException("left == right");
        }
        if (bottom == top) {
            throw new IllegalArgumentException("bottom == top");
        }
        if (near == far) {
            throw new IllegalArgumentException("near == far");
        }

        final float r_width = 1.0f / (right - left);
        final float r_height = 1.0f / (top - bottom);
        final float r_depth = 1.0f / (far - near);
        final float x = 2.0f * (r_width);
        final float y = 2.0f * (r_height);
        final float z = -2.0f * (r_depth);
        final float tx = -(right + left) * r_width;
        final float ty = -(top + bottom) * r_height;
        final float tz = -(far + near) * r_depth;
        result[0] = x;
        result[5] = y;
        result[10] = z;
        result[12] = tx;
        result[13] = ty;
        result[14] = tz;
        result[15] = 1.0f;
        result[1] = 0.0f;
        result[2] = 0.0f;
        result[3] = 0.0f;
        result[4] = 0.0f;
        result[6] = 0.0f;
        result[7] = 0.0f;
        result[8] = 0.0f;
        result[9] = 0.0f;
        result[11] = 0.0f;
    }

    /**
     * Define a projection matrix in terms of six clip planes
     * @param result the float array that holds the perspective matrix
     * @param left The left plane
     * @param right The right plane
     * @param bottom The bottom plane
     * @param top The top plane
     * @param near The near plane
     * @param far The far plane
     */
    public static void frustumM(float[] result, float left, float right, float bottom, float top, float near, float far) {
        if (left == right) {
            throw new IllegalArgumentException("left == right");
        }
        if (top == bottom) {
            throw new IllegalArgumentException("top == bottom");
        }
        if (near == far) {
            throw new IllegalArgumentException("near == far");
        }
        if (near <= 0.0f) {
            throw new IllegalArgumentException("near <= 0.0f");
        }
        if (far <= 0.0f) {
            throw new IllegalArgumentException("far <= 0.0f");
        }
        final float r_width = 1.0f / (right - left);
        final float r_height = 1.0f / (top - bottom);
        final float r_depth = 1.0f / (near - far);
        final float x = 2.0f * (near * r_width);
        final float y = 2.0f * (near * r_height);
//        final float A = 2.0f * ((right + left) * r_width);
        final float A = (right + left) * r_width;
        final float B = (top + bottom) * r_height;
        final float C = (far + near) * r_depth;
        final float D = 2.0f * (far * near * r_depth);
        result[0] = x;
        result[5] = y;
        result[8] = A;
        result[9] = B;
        result[10] = C;
        result[14] = D;
        result[11] = -1.0f;
        result[1] = 0.0f;
        result[2] = 0.0f;
        result[3] = 0.0f;
        result[4] = 0.0f;
        result[6] = 0.0f;
        result[7] = 0.0f;
        result[12] = 0.0f;
        result[13] = 0.0f;
        result[15] = 0.0f;
    }

    /**
     * Computes the length of a vector,
     *
     * @param x x-coordinate of the vector
     * @param y y-coordinate of the vector
     * @param z z-coordinate of the vector
     * @return the length of the vector
     */
    public static float length(float x, float y, float z) {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * Sets matrix to an identity matrix.
     * @param result returns the identity matrix,
     */
    public static void setIdentityM(float[] result) {
        System.arraycopy(identity, 0, result, 0, 16);
    }

    /**
     * Translates matrix result by x, y, and z in place.
     * @param result matrix
     * @param x translation factor x
     * @param y translation factor y
     * @param z translation factor z
     */
    public static void translateM(float[] result, float x, float y, float z) {
        for (int i = 0; i < 4; ++i) {
            result[i + 12] += result[i] * x + result[i + 4] * y + result[i + 8] * z;
        }
    }

    /**
     * Rotates a matrix in place by an angle around a vector (x, y, z),
     * @param result the rotated matrix
     * @param degrees an angle to rotate in degrees
     * @param x vector x-coordinate
     * @param y vector y-coordinate
     * @param z vector z-coordinate
     */
    public static void rotateM(float[] result, float degrees, float x, float y, float z) {
        setRotateM(temp1, degrees, x, y, z);
        multiplyMM(temp2, result, temp1);
        System.arraycopy(temp2, 0, result, 0, 16);
    }

    /**
     * Creates a rotation matrix around a vector (x, y, z),
     * @param result returns the result
     * @param angle an angle to rotate in degrees
     * @param x vector x-coordinate
     * @param y vector y-coordinate
     * @param z vector z-coordinate
     */
    public static void setRotateM(float[] result, float angle, float x, float y, float z) {
        result[3] = 0;
        result[7] = 0;
        result[11] = 0;
        result[12] = 0;
        result[13] = 0;
        result[14] = 0;
        result[15] = 1;
        angle *= (float) (Math.PI / 180.0f);
        float s = (float) Math.sin(angle);
        float c = (float) Math.cos(angle);
        if (1.0f == x && 0.0f == y && 0.0f == z) {
            result[5] = c;
            result[10] = c;
            result[6] = s;
            result[9] = -s;
            result[1] = 0;
            result[2] = 0;
            result[4] = 0;
            result[8] = 0;
            result[0] = 1;
        } else if (0.0f == x && 1.0f == y && 0.0f == z) {
            result[0] = c;
            result[10] = c;
            result[8] = s;
            result[2] = -s;
            result[1] = 0;
            result[4] = 0;
            result[6] = 0;
            result[9] = 0;
            result[5] = 1;
        } else if (0.0f == x && 0.0f == y && 1.0f == z) {
            result[0] = c;
            result[5] = c;
            result[1] = s;
            result[4] = -s;
            result[2] = 0;
            result[6] = 0;
            result[8] = 0;
            result[9] = 0;
            result[10] = 1;
        } else {
            float len = length(x, y, z);
            if (1.0f != len) {
                float recipLen = 1.0f / len;
                x *= recipLen;
                y *= recipLen;
                z *= recipLen;
            }
            float nc = 1.0f - c;
            float xy = x * y;
            float yz = y * z;
            float zx = z * x;
            float xs = x * s;
            float ys = y * s;
            float zs = z * s;
            result[0] = x * x * nc + c;
            result[4] = xy * nc - zs;
            result[8] = zx * nc + ys;
            result[1] = xy * nc + zs;
            result[5] = y * y * nc + c;
            result[9] = yz * nc - xs;
            result[2] = zx * nc - ys;
            result[6] = yz * nc + xs;
            result[10] = z * z * nc + c;
        }
    }


    /**
     * Implementation of gluLookAt(). Creates a view matrix based on the location, eye direction, and up vector
     * of a camera,
     *
     * @param result returns the result
     * @param eyeX eye point X
     * @param eyeY eye point Y
     * @param eyeZ eye point Z
     * @param centerX camera X
     * @param centerY camera Y
     * @param centerZ camera Z
     * @param upX up vector X
     * @param upY up vector Y
     * @param upZ up vector Z
     */
    public static void setLookAtM(float[] result, float eyeX, float eyeY, float eyeZ,
                                  float centerX, float centerY, float centerZ,
                                  float upX, float upY, float upZ) {

        float fx = centerX - eyeX;
        float fy = centerY - eyeY;
        float fz = centerZ - eyeZ;

        // Normalize f
        float rlf = 1.0f / MatrixUtils.length(fx, fy, fz);
        fx *= rlf;
        fy *= rlf;
        fz *= rlf;

        // compute s = f x up (x means "cross product")
        float sx = fy * upZ - fz * upY;
        float sy = fz * upX - fx * upZ;
        float sz = fx * upY - fy * upX;

        // and normalize s
        float rls = 1.0f / MatrixUtils.length(sx, sy, sz);
        sx *= rls;
        sy *= rls;
        sz *= rls;

        // compute u = s x f
        float ux = sy * fz - sz * fy;
        float uy = sz * fx - sx * fz;
        float uz = sx * fy - sy * fx;

        result[0] = sx;
        result[1] = ux;
        result[2] = -fx;
        result[3] = 0.0f;

        result[4] = sy;
        result[5] = uy;
        result[6] = -fy;
        result[7] = 0.0f;

        result[8] = sz;
        result[9] = uz;
        result[10] = -fz;
        result[11] = 0.0f;

        result[12] = 0.0f;
        result[13] = 0.0f;
        result[14] = 0.0f;
        result[15] = 1.0f;

        translateM(result, -eyeX, -eyeY, -eyeZ);
    }

    private static float[] identity = {
            1, 0, 0, 0,
            0, 1, 0, 0,
            0, 0, 1, 0,
            0 ,0, 0, 1};
    private static float[] temp1 = new float[16];
    private static float[] temp2 = new float[16];
    private static float[] temp3 = new float[16];

    public static void makeScaleMatrix(float[] result, float x, float y, float z) {
        setIdentityM(result);
        result[0] = x;
        result[5] = y;
        result[10] = z;
    }

    public static void scaleM(float[] result, float x, float y, float z) {
        makeScaleMatrix(temp1, x, y, z);
        MatrixUtils.multiplyMM(result, result, temp1);
    }

    public static void makeTranslationMatrix(float[] data, float x, float y, float z) {
        setIdentityM(data);
        data[12] = x;
        data[13] = y;
        data[14] = z;
    }
}
