package com.foley.aoc.year2021;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.functions.Regex;

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
            var match = Regex.getMatches("\\d+", s);
            Point p1 = new Point(Integer.parseInt(match.get(0)), Integer.parseInt(match.get(1)));
            Point p2 = new Point(Integer.parseInt(match.get(2)), Integer.parseInt(match.get(3)));
            int minX = Math.min(p1.x, p2.x);
            int maxX = Math.max(p1.x, p2.x);
            int minY = Math.min(p1.y, p2.y);
            int maxY = Math.max(p1.y, p2.y);
            if (p1.y == p2.y) { // Horizontal line if both y's are equal
                for (int x = minX; x <= maxX; x++) {
                    str.add(new Point(x, p1.y));
                }
            } else if (p1.x == p2.x) { // Vertical line if both x's are equal
                for(int y = minY; y <= maxY; y++) {
                    str.add(new Point(p1.x, y));
                }
            } else { // Diagonal line
                int m = (p2.y - p1.y) / (p2.x - p1.x);
                int y = minX == p1.x ? p1.y : p2.y;
                for(int x = minX; x <= maxX; x++) {
                    diag.add(new Point(x, y));
                    y += m;
                }
            }
        }
    }
}