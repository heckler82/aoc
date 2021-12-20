package com.foley.aoc.util.point;

import java.util.Comparator;

/**
 * Compares two points and orders according to read order (top/down, left/right, front/back)
 *
 * @author Evan Foley
 * @version 15 January 2019
 */
public class Point3DComparator implements Comparator<Point3D> {
    @Override
    /**
     * Compares its two arguments for order
     *
     * @param p1 the first object to be compared
     * @param p2 the second object to be compared
     * @return
     */
    public int compare(Point3D p1, Point3D p2) {
        int zComp = Double.compare(p1.getZ(), p2.getZ());

        // Non-zero z values determine placement (negative z on p1 is closer to front than the p2, positive is farther)
        if (zComp != 0) {
            return zComp;
        } else {
            // If two points are at the same z position, sort on top to bottom order (top < y < bottom)
            int comp = Double.compare(p1.getY(), p2.getY());
            if (comp != 0) {
                return comp;
            } else {
                // If two points are at same y position, sort on left to right order (left < x < right)
                return Double.compare(p1.getX(), p2.getX());
            }
        }
    }
}