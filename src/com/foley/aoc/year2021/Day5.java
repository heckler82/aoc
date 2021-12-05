package com.foley.aoc.year2021;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.Tuple;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Solutions for day 5
 *
 * @author Evan Foley
 * @version 05 Dec 2021
 */
public class Day5 extends Daily {
    private List<Point> diagonal;
    private List<Point> straight;
    Set<Point> overlap;
    private int intersectCount;
    private int[][] map;

    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day5(String fileName) {
        super(fileName);
        straight = new ArrayList<>();
        diagonal = new ArrayList<>();
        overlap = new HashSet<>();
        parsePoints(input, straight, diagonal);
        map = new int[1000][1000];
        intersectCount = 0;
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        intersectCount = getIntersectCount(straight);
        System.out.printf("There are %d points where at least 2 lines overlap\n", intersectCount);
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        intersectCount += getIntersectCount(diagonal);
        System.out.printf("There are %d points where at least 2 lines overlap\n", intersectCount);
    }

    /**
     * Counts the number of points that have at least two lines overlapping
     *
     * @param points The list of points
     * @return The number of points at least two lines overlap
     */
    private int getIntersectCount(List<Point> points) {
        int count = 0;
        for(var p : points) {
            map[p.y][p.x]++;
            if(map[p.y][p.x] > 1 && !overlap.contains(p)) {
                overlap.add(p);
                count++;
            }
        }
        return count;
    }

    /**
     * Parses points from the input and puts them into the relevant lists
     *
     * @param in The input strings
     * @param str The list of points forming straight lines
     * @param diag The lines of points forming diagonal lines
     */
    private void parsePoints(String[] in, List<Point> str, List<Point> diag) {
        for(String s : in) {
            var pts = s.split(" -> ");
            Point p1 = parsePoint(pts[0]);
            Point p2 = parsePoint(pts[1]);
            try {
                double m = (p2.y - p1.y) / (p2.x - p1.x); // Divide by zero is a vertical line, but will throw ArithmeticException
                int minX = Math.min(p1.x, p2.x);
                int maxX = Math.max(p1.x, p2.x);
                if (p1.y == p2.y) { // Horizontal line if both y's are equal
                    for (int x = minX; x <= maxX; x++) {
                            str.add(new Point(x, p1.y));
                    }
                } else {
                    // Diagonal line
                    int y = minX == p1.x ? p1.y : p2.y;
                    for(int x = minX; x <= maxX; x++) {
                        diag.add(new Point(x, y));
                        y += (int)m;
                    }
                }
            } catch (ArithmeticException e) {
                // Vertical line
                int minY = Math.min(p1.y, p2.y);
                int maxY = Math.max(p1.y, p2.y);
                for(int y = minY; y <= maxY; y++) {
                    straight.add(new Point(p1.x, y));
                }
            }
        }
    }

    /**
     * Parses a point from a string
     *
     * @param s The string to parse
     * @return The point parsed from the string
     */
    private Point parsePoint(String s) {
        var coord = s.split(",");
        return new Point(Integer.parseInt(coord[0]), Integer.parseInt(coord[1]));
    }
}