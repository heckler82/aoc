package com.foley.aoc.util.point;

import java.util.Objects;

/**
 * Provides a template for a 3-dimensional point with 64-bit integer precision
 *
 * @author Evan Foley
 * @version 17 Dec 2020
 */
public class Point3L {
    public long x;
    public long y;
    public long z;

    /**
     * Creates a new point
     *
     * @param x The x coordinate
     * @param y The y coordinate
     * @param z The z coordinate
     */
    public Point3L(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Creates a new point
     *
     * @param x The x coordinate
     * @param y The y coordinate
     * @param z The z coordinate
     */
    public Point3L(long x, long y, long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Creates a new point that is a copy of the specified point
     *
     * @param p The point to copy
     */
    public Point3L(Point3I p) {
        x = p.getX();
        y = p.getY();
        z = p.getZ();
    }

    /**
     * Creates a new point that is a copy of the specified point
     *
     * @param p The point to copy
     */
    public Point3L(Point3L p) {
        x = p.x;
        y = p.y;
        z = p.z;
    }

    /**
     * Gets the x coordinate for the point
     *
     * @return The x coordinate
     */
    public long getX() {
        return x;
    }

    /**
     * Gets the y coordinate for the point
     *
     * @return The y coordinate
     */
    public long getY() {
        return y;
    }

    /**
     * Gets the z coordinate for the point
     *
     * @return The z coordinate
     */
    public long getZ() {
        return z;
    }

    /**
     * Sets the x coordinate for the point
     *
     * @param x The new value
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y coordinate for the point
     *
     * @param y The new value
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Sets the z coordinate for the point
     *
     * @param z The new value
     */
    public void setZ(int z) {
        this.z = z;
    }

    /**
     * Sets the x coordinate for the point
     *
     * @param x The new value
     */
    public void setX(long x) {
        this.x = x;
    }

    /**
     * Sets the y coordinate for the point
     *
     * @param y The new value
     */
    public void setY(long y) {
        this.y = y;
    }

    /**
     * Sets the z coordinate for the point
     *
     * @param z The new value
     */
    public void setZ(long z) {
        this.z = z;
    }

    /**
     * Returns a new point with all values equal to zero
     *
     * @return A new point with all values equal to zero
     */
    public static Point3L zero() {
        Point3L p = new Point3L(0, 0, 0);
        return p;
    }

    /**
     * Returns a new point with all values equal to one
     *
     * @return A new point with all values equal to one
     */
    private static Point3L one() {
        Point3L p = new Point3L(1, 1, 1);
        return p;
    }

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
        Point3L point3l = (Point3L) o;
        return Long.compare(point3l.x, x) == 0 &&
                Long.compare(point3l.y, y) == 0 &&
                Long.compare(point3l.z, z) == 0;
    }

    @Override
    /**
     * Gets the hash for the point
     *
     * @return The hash of the point
     */
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
