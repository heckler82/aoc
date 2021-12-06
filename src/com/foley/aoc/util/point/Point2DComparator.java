package com.foley.aoc.util.point;

import java.awt.geom.Point2D;
import java.util.Comparator;

/**
 * Compares two points and orders according to read order (top/down, left/right)
 *
 * @author Evan Foley
 * @version 15 January 2019
 */
public class Point2DComparator implements Comparator<Point2D> {
    @Override
    /**
     * Compares its two arguments for order
     *
     * @param p1 the first object to be compared
     * @param p2 the second object to be compared
     * @return
     */
    public int compare(Point2D p1, Point2D p2) {
        // Get initial comparison
        int comp = Double.compare(p1.getY(), p2.getY());
        // Return if this is the top level point (lower y values are towards the top)
        if(comp < 0) {
            return comp;
        }
        // If two points are at same y position, test left/right order
        if(comp == 0) {
            comp = Double.compare(p1.getX(), p2.getX());
            // Return if this is the left point
            if(comp < 0) {
                return comp;
            }
        }
        // This point is either lower level or right level point
        return comp;
    }
}