package com.foley.aoc.util.point;

import java.util.Objects;

/**
 * Provides a template for a 3-dimensional point with 32-bit integer precision
 *
 * @author Evan Foley
 * @version 17 Dec 2020
 */
public class Point3I {
    private int x;
    private int y;
    private int z;

    /**
     * Creates a new point
     *
     * @param x The x coordinate
     * @param y The y coordinate
     * @param z The z coordinate
     */
    public Point3I(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Creates a new point that is a copy of the specified point
     *
     * @param p The point to copy
     */
    public Point3I(Point3I p) {
        x = p.x;
        y = p.y;
        z = p.z;
    }

    /**
     * Gets the x coordinate for the point
     *
     * @return The x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y coordinate for the point
     *
     * @return The y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the z coordinate for the point
     *
     * @return The z coordinate
     */
    public int getZ() {
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
        Point3I point3i = (Point3I) o;
        return Integer.compare(point3i.x, x) == 0 &&
                Integer.compare(point3i.y, y) == 0 &&
                Integer.compare(point3i.z, z) == 0;
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
