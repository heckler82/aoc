package com.foley.aoc.util;

import com.foley.aoc.util.point.Point3D;

/**
 * Representation of a 3x3 matrix
 *
 * @author Evan Foley
 * @version 20 Dec 2021
 */
public class Matrix3 {
    private Point3D[] mat;

    /**
     * Creates a new 3x3 matrix
     *
     * @param p the first row
     * @param p2 the second row
     * @param p3 the third row
     */
    public Matrix3(Point3D p, Point3D p2, Point3D p3) {
        mat = new Point3D[3];
        mat[0] = p;
        mat[1] = p2;
        mat[2] = p3;
    }

    /**
     * Creates a new 3x3 matrix
     *
     * @param m11 the first item of the first row
     * @param m12 the second item of the first row
     * @param m13 the third item of the third row
     * @param m21 the first item of the second row
     * @param m22 the second item of the second row
     * @param m23 the third item of the second row
     * @param m31 the first item of the third row
     * @param m32 the second item of the third row
     * @param m33 the third item of the third row
     */
    public Matrix3(int m11, int m12, int m13, int m21, int m22, int m23, int m31, int m32, int m33) {
        mat = new Point3D[3];
        mat[0] = new Point3D.Int(m11, m12, m13);
        mat[1] = new Point3D.Int(m21, m22, m23);
        mat[2] = new Point3D.Int(m31, m32, m33);
    }

    /**
     * Multiplies a point by this matrix and returns the result
     *
     * @param p the point to multiply
     * @return the resulting point
     */
    public Point3D multiply(Point3D p) {
        return p.make(p.getX() * mat[0].getX() + p.getY() * mat[1].getX() + p.getZ() * mat[2].getX(),
                p.getX() * mat[0].getY() + p.getY() * mat[1].getY() + p.getZ() * mat[2].getY(),
                p.getX() * mat[0].getZ() + p.getY() * mat[1].getZ() + p.getZ() * mat[2].getZ());
    }

    /**
     * Gets the determinant for the matrix
     *
     * @return the determinant
     */
    public double determinant() {
        double detA = mat[0].getX() * ((mat[1].getY() * mat[2].getZ()) - (mat[1].getZ() * mat[2].getY()));
        double detB = mat[0].getY() * ((mat[1].getX() * mat[2].getZ()) - (mat[1].getZ() * mat[2].getX()));
        double detC = mat[0].getZ() * ((mat[1].getX() * mat[2].getY()) - (mat[1].getY() * mat[2].getX()));
        return detA - detB + detC;
    }
}
