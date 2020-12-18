package com.foley.aoc.util.point;

import java.util.Objects;

/**
 * Provides a template for a 3-dimensional point with float precision
 *
 * @author Evan Foley
 * @version 17 Dec 2020
 */
public class Point3F {
    private float x;
    private float y;
    private float z;

    /**
     * Creates a new point
     *
     * @param x The x coordinate
     * @param y The y coordinate
     * @param z The z coordinate
     */
    public Point3F(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Creates a new point that is a copy of the specified point
     *
     * @param p The point to copy
     */
    public Point3F(Point3F p) {
        x = p.x;
        y = p.y;
        z = p.z;
    }

    /**
     * Gets the x coordinate for the point
     *
     * @return The x coordinate
     */
    public float getX() {
        return x;
    }

    /**
     * Gets the y coordinate for the point
     *
     * @return The y coordinate
     */
    public float getY() {
        return y;
    }

    /**
     * Gets the z coordinate for the point
     *
     * @return The z coordinate
     */
    public float getZ() {
        return z;
    }

    /**
     * Sets the x coordinate for the point
     *
     * @param x The new value
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Sets the y coordinate for the point
     *
     * @param y The new value
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Sets the z coordinate for the point
     *
     * @param z The new value
     */
    public void setZ(float z) {
        this.z = z;
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
        Point3F point3f = (Point3F) o;
        return Float.compare(point3f.x, x) == 0 &&
                Float.compare(point3f.y, y) == 0 &&
                Float.compare(point3f.z, z) == 0;
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
