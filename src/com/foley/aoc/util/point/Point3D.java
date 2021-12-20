package com.foley.aoc.util.point;

import java.util.Objects;

/**
 * Provides a template for a 3-dimensional point with double precision
 *
 * @author Evan Foley
 * @version 17 Dec 2020
 */
public abstract class Point3D {

    /**
     * The Int class creates points in int precision
     */
    public static class Int extends Point3D {
        public int x;
        public int y;
        public int z;

        /**
         * Creates a new point with all coordinates set to zero
         */
        public Int() {}

        /**
         * Creates a new point with the specified coordinates
         *
         * @param x the x coordinate
         * @param y the y coordinate
         * @param z the z coordinate
         */
        public Int(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        /**
         * Creates a new point with the same coordinates as the specified point
         *
         * @param p the specified point
         */
        public Int(Point3D p) {
            x = (int)p.getX();
            y = (int)p.getY();
            z = (int)p.getZ();
        }

        @Override
        /**
         * Gets the x coordinate
         *
         * @return the x coordinate
         */
        public double getX() {
            return x;
        }

        @Override
        /**
         * Gets the y coordinate
         *
         * @return the y coordinate
         */
        public double getY() {
            return y;
        }

        @Override
        /**
         * Gets the z coordinate
         *
         * @return the z coordinate
         */
        public double getZ() {
            return z;
        }

        @Override
        /**
         * Sets the location of the point
         *
         * @param x the x coordinate
         * @param y the y coordinate
         * @param z the z coordinate
         */
        public void setLocation(double x, double y, double z) {
            this.x = (int)x;
            this.y = (int)y;
            this.z = (int)z;
        }

        @Override
        /**
         * Returns the sum of this point and a specified point
         *
         * @param p the specified point
         * @return the sum of this point and a specified point
         */
        public Point3D add(Point3D p) {
            return new Int((int)(x + p.getX()), (int)(y + p.getY()), (int)(z + p.getZ()));
        }

        @Override
        /**
         * Returns the difference between this point and a specified point
         *
         * @param p the specified point
         * @return the difference between this point and a specified point
         */
        public Point3D subtract(Point3D p) {
            return new Int((int)(x - p.getX()), (int)(y - p.getY()), (int)(z - p.getZ()));
        }

        @Override
        /**
         * Returns a new point scaled by the magnitudes of a specified point
         *
         * @param p the point to scale this point by
         * @return a new point scaled by the specified point
         */
        public Point3D scale(Point3D p) {
            return new Int((int)(x * p.getX()), (int)(y * p.getY()), (int)(z * p.getZ()));
        }

        @Override
        /**
         * Returns a new point with the same coordinates as this point
         *
         * @return a new point at the same location as this point
         */
        public Point3D copy() {
            return new Int(x, y, z);
        }

        @Override
        /**
         * Makes a new point with the specified coordinates
         *
         * @param x the x coordinate
         * @param y the y coordinate
         * @param z the z coordinate
         * @return a new point with the specified coordinates
         */
        public Point3D make(double x, double y, double z) {
            return new Int((int)x, (int)y, (int)z);
        }

        /**
         * Returns a string representation of the point
         *
         * @return a string representation of the point
         */
        public String toString() {
            return "(" + x + ", " + y + ", " + z + ")";
        }

        /**
         * Creates a new point in int precision initialized to zero
         *
         * @return a new point
         */
        public static Int zero() {
            return new Int();
        }

        /**
         * Creates a new point in integer precision initialized to one
         *
         * @return a new point with coordinates initializes to one
         */
        public static Int one() {
            return new Int(1, 1, 1);
        }
    }

    /**
     * The Long class creates points in long precision
     */
    public static class Long extends Point3D {
        public long x;
        public long y;
        public long z;

        /**
         * Creates a new point with all coordinates set to zero
         */
        public Long() {}

        /**
         * Creates a new point with the specified coordinates
         *
         * @param x the x coordinate
         * @param y the y coordinate
         * @param z the z coordinate
         */
        public Long(long x, long y, long z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        /**
         * Creates a new point with the same coordinates as the specified point
         *
         * @param p the specified point
         */
        public Long(Point3D p) {
            x = (long)p.getX();
            y = (long)p.getY();
            z = (long)p.getZ();
        }

        @Override
        /**
         * Gets the x coordinate
         *
         * @return the x coordinate
         */
        public double getX() {
            return x;
        }

        @Override
        /**
         * Gets the y coordinate
         *
         * @return the y coordinate
         */
        public double getY() {
            return y;
        }

        @Override
        /**
         * Gets the z coordinate
         *
         * @return the z coordinate
         */
        public double getZ() {
            return z;
        }

        @Override
        /**
         * Sets the location of the point
         *
         * @param x the x coordinate
         * @param y the y coordinate
         * @param z the z coordinate
         */
        public void setLocation(double x, double y, double z) {
            this.x = (long)x;
            this.y = (long)y;
            this.z = (long)z;
        }

        @Override
        /**
         * Returns the sum of this point and a specified point
         *
         * @param p the specified point
         * @return the sum of this point and a specified point
         */
        public Point3D add(Point3D p) {
            return new Float((long)(x + p.getX()), (long)(y + p.getY()), (long)(z + p.getZ()));
        }

        @Override
        /**
         * Returns the difference between this point and a specified point
         *
         * @param p the specified point
         * @return the difference between this point and a specified point
         */
        public Point3D subtract(Point3D p) {
            return new Float((long)(x - p.getX()), (long)(y - p.getY()), (long)(z - p.getZ()));
        }

        @Override
        /**
         * Returns a new point scaled by the magnitudes of a specified point
         *
         * @param p the point to scale this point by
         * @return a new point scaled by the specified point
         */
        public Point3D scale(Point3D p) {
            return new Long((long)(x * p.getX()), (long)(y * p.getY()), (long)(z * p.getZ()));
        }

        @Override
        /**
         * Returns a new point with the same coordinates as this point
         *
         * @return a new point at the same location as this point
         */
        public Point3D copy() {
            return new Long(x, y, z);
        }

        @Override
        /**
         * Makes a new point with the specified coordinates
         *
         * @param x the x coordinate
         * @param y the y coordinate
         * @param z the z coordinate
         * @return a new point with the specified coordinates
         */
        public Point3D make(double x, double y, double z) {
            return new Long((long)x, (long)y, (long)z);
        }

        /**
         * Returns a string representation of the point
         *
         * @return a string representation of the point
         */
        public String toString() {
            return "(" + x + ", " + y + ", " + z + ")";
        }

        /**
         * Creates a new point in long precision initialized to zero
         *
         * @return a new point
         */
        public static Long zero() {
            return new Long();
        }

        /**
         * Creates a new point in long precision initialized to one
         *
         * @return a new point with coordinates initializes to one
         */
        public static Long one() {
            return new Long(1L, 1L, 1L);
        }
    }

    /**
     * The Float class creates points in float precision
     */
    public static class Float extends Point3D {
        public float x;
        public float y;
        public float z;

        /**
         * Creates a new point with all coordinates set to zero
         */
        public Float() {}

        /**
         * Creates a new point with the specified coordinates
         *
         * @param x the x coordinate
         * @param y the y coordinate
         * @param z the z coordinate
         */
        public Float(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        /**
         * Creates a new point with the same coordinates as the specified point
         *
         * @param p the specified point
         */
        public Float(Point3D p) {
            x = (float)p.getX();
            y = (float)p.getY();
            z = (float)p.getZ();
        }

        @Override
        /**
         * Gets the x coordinate
         *
         * @return the x coordinate
         */
        public double getX() {
            return x;
        }

        @Override
        /**
         * Gets the y coordinate
         *
         * @return the y coordinate
         */
        public double getY() {
            return y;
        }

        @Override
        /**
         * Gets the z coordinate
         *
         * @return the z coordinate
         */
        public double getZ() {
            return z;
        }

        @Override
        /**
         * Sets the location of the point
         *
         * @param x the x coordinate
         * @param y the y coordinate
         * @param z the z coordinate
         */
        public void setLocation(double x, double y, double z) {
            this.x = (float)x;
            this.y = (float)y;
            this.z = (float)z;
        }

        @Override
        /**
         * Returns the sum of this point and a specified point
         *
         * @param p the specified point
         * @return the sum of this point and a specified point
         */
        public Point3D add(Point3D p) {
            return new Float((float)(x + p.getX()), (float)(y + p.getY()), (float)(z + p.getZ()));
        }

        @Override
        /**
         * Returns the difference between this point and a specified point
         *
         * @param p the specified point
         * @return the difference between this point and a specified point
         */
        public Point3D subtract(Point3D p) {
            return new Float((float)(x - p.getX()), (float)(y - p.getY()), (float)(z - p.getZ()));
        }

        @Override
        /**
         * Returns a new point scaled by the magnitudes of a specified point
         *
         * @param p the point to scale this point by
         * @return a new point scaled by the specified point
         */
        public Point3D scale(Point3D p) {
            return new Float((float)(x * p.getX()), (float)(y * p.getY()), (float)(z * p.getZ()));
        }

        @Override
        /**
         * Returns a new point with the same coordinates as this point
         *
         * @return a new point at the same location as this point
         */
        public Point3D copy() {
            return new Float(x, y, z);
        }

        @Override
        /**
         * Makes a new point with the specified coordinates
         *
         * @param x the x coordinate
         * @param y the y coordinate
         * @param z the z coordinate
         * @return a new point with the specified coordinates
         */
        public Point3D make(double x, double y, double z) {
            return new Float((float)x, (float)y, (float)z);
        }

        /**
         * Returns a string representation of the point
         *
         * @return a string representation of the point
         */
        public String toString() {
            return "(" + x + ", " + y + ", " + z + ")";
        }

        /**
         * Creates a new point in float precision initialized to zero
         *
         * @return a new point
         */
        public static Float zero() {
            return new Float();
        }

        /**
         * Creates a new point in float precision initialized to one
         *
         * @return a new point with coordinates initializes to one
         */
        public static Float one() {
            return new Float(1.0f, 1.0f, 1.0f);
        }
    }

    /**
     * The Double class creates points in double precision
     */
    public static class Double extends Point3D {
        public double x;
        public double y;
        public double z;

        /**
         * Creates a new point with all coordinates set to zero
         */
        public Double() {}

        /**
         * Creates a new point with the specified coordinates
         *
         * @param x the x coordinate
         * @param y the y coordinate
         * @param z the z coordinate
         */
        public Double(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        /**
         * Creates a new point with the same coordinates as the specified point
         *
         * @param p the specified point
         */
        public Double(Point3D p) {
            x = p.getX();
            y = p.getY();
            z = p.getZ();
        }

        @Override
        /**
         * Gets the x coordinate
         *
         * @return the x coordinate
         */
        public double getX() {
            return x;
        }

        @Override
        /**
         * Gets the y coordinate
         *
         * @return the y coordinate
         */
        public double getY() {
            return y;
        }

        @Override
        /**
         * Gets the z coordinate
         *
         * @return the z coordinate
         */
        public double getZ() {
            return z;
        }

        @Override
        /**
         * Sets the location of the point
         *
         * @param x the x coordinate
         * @param y the y coordinate
         * @param z the z coordinate
         */
        public void setLocation(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        /**
         * Returns the sum of this point and a specified point
         *
         * @param p the specified point
         * @return the sum of this point and a specified point
         */
        public Point3D add(Point3D p) {
            return new Double(x + p.getX(), y + p.getY(), z + p.getZ());
        }

        @Override
        /**
         * Returns the difference between this point and a specified point
         *
         * @param p the specified point
         * @return the difference between this point and a specified point
         */
        public Point3D subtract(Point3D p) {
            return new Double(x - p.getX(), y - p.getY(), z - p.getZ());
        }

        @Override
        /**
         * Returns a new point scaled by the magnitudes of a specified point
         *
         * @param p the point to scale this point by
         * @return a new point scaled by the specified point
         */
        public Point3D scale(Point3D p) {
            return new Double(x * p.getX(), y * p.getY(), z * p.getZ());
        }

        @Override
        /**
         * Returns a new point with the same coordinates as this point
         *
         * @return a new point at the same location as this point
         */
        public Point3D copy() {
            return new Double(x, y, z);
        }

        @Override
        /**
         * Makes a new point with the specified coordinates
         *
         * @param x the x coordinate
         * @param y the y coordinate
         * @param z the z coordinate
         * @return a new point with the specified coordinates
         */
        public Point3D make(double x, double y, double z) {
            return new Double(x, y, z);
        }

        /**
         * Returns a string representation of the point
         *
         * @return a string representation of the point
         */
        public String toString() {
            return "(" + x + ", " + y + ", " + z + ")";
        }

        /**
         * Creates a new point in double precision initialized to zero
         *
         * @return a new point
         */
        public static Double zero() {
            return new Double();
        }

        /**
         * Creates a new point in double precision initialized to one
         *
         * @return a new point with coordinates initializes to one
         */
        public static Double one() {
            return new Double(1.0, 1.0, 1.0);
        }
    }

    /**
     * Gets the x coordinate
     *
     * @return the x coordinate
     */
    public abstract double getX();

    /**
     * Gets the y coordinate
     *
     * @return the y coordinate
     */
    public abstract double getY();

    /**
     * Gets the z coordinate
     *
     * @return the z coordinate
     */
    public abstract double getZ();

    /**
     * Sets the location of the point
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     */
    public abstract void setLocation(double x, double y, double z);

    /**
     * Returns the sum of this point and a specified point
     *
     * @param p the specified point
     * @return the sum of this point and a specified point
     */
    public abstract Point3D add(Point3D p);

    /**
     * Returns the difference between this point and a specified point
     *
     * @param p the specified point
     * @return the difference between this point and a specified point
     */
    public abstract Point3D subtract(Point3D p);

    /**
     * Returns a new point scaled by the magnitudes of a specified point
     *
     * @param p the point to scale this point by
     * @return a new point scaled by the specified point
     */
    public abstract Point3D scale(Point3D p);

    /**
     * Returns a new point with the same coordinates as this point
     *
     * @return a new point at the same location as this point
     */
    public abstract Point3D copy();

    /**
     * Makes a new point with the specified coordinates
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     * @return a new point with the specified coordinates
     */
    public abstract Point3D make(double x, double y, double z);

    /**
     * Sets the location of the point to the same coordinates as the specified point
     *
     * @param p the specified point
     */
    public void setLocation(Point3D p) {
        setLocation(p.getX(), p.getY(), p.getZ());
    }

    /**
     * Gets the euclidean distance between two points
     *
     * @param x1 the x coordinate of the first point
     * @param y1 the y coordinate of the first point
     * @param z1 the z coordinate of the first point
     * @param x2 the x coordinate of the second point
     * @param y2 the y coordinate of the second point
     * @param z2 the z coordinate of the second point
     * @return the euclidean distance between the two points
     */
    public static double euclideanDistance(double x1, double y1, double z1, double x2, double y2, double z2) {
        double distSq = euclideanDistanceSq(x1, y1, z1, x2, y2, z2);
        return Math.sqrt(distSq);
    }

    /**
     * Gets the squared euclidean distance between two points
     *
     * @param x1 the x coordinate of the first point
     * @param y1 the y coordinate of the first point
     * @param z1 the z coordinate of the first point
     * @param x2 the x coordinate of the second point
     * @param y2 the y coordinate of the second point
     * @param z2 the z coordinate of the second point
     * @return the squared euclidean distance between the two points
     */
    public static double euclideanDistanceSq(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) + Math.pow(z2 - z1, 2);
    }

    /**
     * Gets the cehbyshev distance between two points
     *
     * @param x1 the x coordinate of the first point
     * @param y1 the y coordinate of the first point
     * @param z1 the z coordinate of the first point
     * @param x2 the x coordinate of the second point
     * @param y2 the y coordinate of the second point
     * @param z2 the z coordinate of the second point
     * @return the chebyshev distance between the two points
     */
    public static double chebyshevDistance(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Math.max(Math.abs(x2 - x1), Math.max(Math.abs(y2 - y1), Math.abs(z2 - z1)));
    }

    /**
     * Gets the manhattan distance between two points
     *
     * @param x1 the x coordinate of the first point
     * @param y1 the y coordinate of the first point
     * @param z1 the z coordinate of the first point
     * @param x2 the x coordinate of the second point
     * @param y2 the y coordinate of the second point
     * @param z2 the z coordinate of the second point
     * @return the manhattan distance between the two points
     */
    public static double manhattanDistance(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Math.abs(x2 - x1) + Math.abs(y2 - y1) + Math.abs(z2 - z1);
    }

    /**
     * Gets the euclidean distance between two points
     *
     * @param p1 the first point
     * @param p2 the second point
     * @return the distance between the two points
     */
    public static double euclideanDistance(Point3D p1, Point3D p2) {
        return euclideanDistance(p1.getX(), p1.getY(), p1.getZ(),
                p2.getX(), p2.getY(), p2.getZ());
    }

    /**
     * Gets the squared euclidean distance between two points
     *
     * @param p1 the first point
     * @param p2 the second point
     * @return the squared euclidean distance between the two points
     */
    public static double euclideanDistanceSq(Point3D p1, Point3D p2) {
        return euclideanDistanceSq(p1.getX(), p1.getY(), p1.getZ(),
                p2.getX(), p2.getY(), p2.getZ());
    }

    /**
     * Gets the cehbyshev distance between two points
     *
     * @param p1 the first point
     * @param p2 the second point
     * @return the chebyshev distance between the two points
     */
    public static double chebyshevDistance(Point3D p1, Point3D p2) {
        return chebyshevDistance(p1.getX(), p1.getY(), p1.getZ(), p2.getX(), p2.getY(), p2.getZ());
    }

    /**
     * Gets the manhattan distance between two points
     *
     * @param p1 the first point
     * @param p2 the second point
     * @return the manhattan distance between the two points
     */
    public static double manhattanDistance(Point3D p1, Point3D p2) {
        return manhattanDistance(p1.getX(), p1.getY(), p1.getZ(), p2.getX(), p2.getY(), p2.getZ());
    }

    /**
     * Default constructor
     */
    protected Point3D() {}

    @Override
    /**
     * Determines the equality between this point and the specified object
     *
     * @param o The object to test against
     * @return True if the two objects are equal
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3D point3D = (Point3D) o;
        return java.lang.Double.compare(point3D.getX(), getX()) == 0 &&
                java.lang.Double.compare(point3D.getY(), getY()) == 0 &&
                java.lang.Double.compare(point3D.getZ(), getZ()) == 0;
    }

    @Override
    /**
     * Gets the hash for the point
     *
     * @return The hash of the point
     */
    public int hashCode() {
        return Objects.hash(getX(), getY(), getZ());
    }
}
