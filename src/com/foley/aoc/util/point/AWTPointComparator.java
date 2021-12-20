package com.foley.aoc.util.point;

import java.awt.Point;
import java.util.Comparator;

/**
 * Compares two points and orders according to read order (top/down, left/right)
 *
 * @author Evan Foley
 * @version 15 January 2019
 */
public class AWTPointComparator implements Comparator<Point> {
    @Override
    /**
     * Compares its two arguments for order
     *
     * @param p1 the first object to be compared
     * @param p2 the second object to be compared
     * @return
     */
    public int compare(Point p1, Point p2) {
        int comp = Integer.compare(p1.y, p2.y);
        // A non-zero answer will sort the points (lower y values towards top, greater towards bottom)
        if(comp != 0) {
            return comp;
        } else {
            // If two points are at same y position, sort on left to right order (left < x < right)
            return Integer.compare(p1.x, p2.x);
        }
    }
}