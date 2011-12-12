package com.soulaim.tech.math;

/**
 * A 4x4 matrix, in a column major order:
 *
 *  0  4  8 12
 *  1  5  9 13
 *  2  6 10 14
 *  3  7 11 15
 */
public class Matrix4 {

    public static final Matrix4 IDENTITY = new Matrix4();
    public float[] data = new float[16];


    public Matrix4() {
        makeIdentityMatrix();
    }

    public void makeIdentityMatrix() {
        MatrixUtils.setIdentityM(data);
    }

    public void makeLookAtMatrix(float eyeX, float eyeY, float eyeZ,
                                 float centerX, float centerY, float centerZ,
                                 float upX, float upY, float upZ) {
        MatrixUtils.setLookAtM(data, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public void makeFrustumMatrix(float left, float right, float bottom, float top, float near, float far) {
        MatrixUtils.frustumM(data, left, right, bottom, top, near, far);
    }

    public void makeOrthoMatrix(float left, float right, float bottom, float top, float near, float far) {
        MatrixUtils.orthoM(data, left, right, bottom, top, near, far);
    }



    public void makeRotationMatrix(float degrees, float x, float y, float z) {
        MatrixUtils.setRotateM(data, degrees, x, y, z);
    }

    public void storeMatrix(Matrix4 m) {
        System.arraycopy(m.data, 0, data, 0, 16);
    }

    public void storeMultiplication(Matrix4 lhs, Matrix4 rhs) {
        MatrixUtils.multiplyMM(data, lhs.data, rhs.data);
    }

    public void translate(float dx, float dy, float dz) {
        MatrixUtils.translateM(data, dx, dy, dz);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                sb.append(data[j*4 + i]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void scale(float dx, float dy, float dz) {
        MatrixUtils.scaleM(data, dx, dy, dz);
    }

    public void makeScaleMatrix(float x, float y, float z) {
        MatrixUtils.makeScaleMatrix(data, x, y, z);
    }

    public void makeTranslationMatrix(float x, float y, float z) {
        MatrixUtils.makeTranslationMatrix(data, x, y, z);
    }

    public void multiplyVector(float[] vector) {
        MatrixUtils.multiplyMV(vector, data, vector);
    }

    public void rotate(float degrees, float x, float y, float z) {
        MatrixUtils.rotateM(data, degrees, x, y, z);
    }
}
