package com.foley.aoc.year2021;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.functions.Regex;

import java.awt.Point;
import java.util.List;

/**
 * Solutions for day 5
 *
 * @author Evan Foley
 * @version 05 Dec 2021
 */
public class Day5 extends Daily {
    private int straightCount;
    private int diagonalCount;
    private int[][] map;
    private int[][] diagMap;

    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day5(String fileName) {
        super(fileName);
        map = new int[1000][1000];
        diagMap = new int[1000][1000];
        straightCount = 0;
        diagonalCount = 0;
        parsePoints(input);
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        System.out.printf("There are %d points where at least 2 lines overlap\n", straightCount);
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        System.out.printf("There are %d points where at least 2 lines overlap\n", diagonalCount);
    }

    /**
     * Parses points from the input and puts them into the relevant lists
     *
     * @param in The input strings
     */
    private void parsePoints(String[] in) {
        for(String s : in) {
            var match = Regex.getMatches("\\d+", s);
            Point p1 = new Point(Integer.parseInt(match.get(0)), Integer.parseInt(match.get(1)));
            Point p2 = new Point(Integer.parseInt(match.get(2)), Integer.parseInt(match.get(3)));
            int minX = Math.min(p1.x, p2.x);
            int maxX = Math.max(p1.x, p2.x);
            int minY = Math.min(p1.y, p2.y);
            int maxY = Math.max(p1.y, p2.y);
            if(minX == maxX || minY == maxY) {
                for(int x = minX; x <= maxX; x++) {
                    for(int y = minY; y <= maxY; y++) {
                        map[y][x]++;
                        diagMap[y][x]++;
                        if(map[y][x] == 2) {
                            straightCount++;
                        }
                        if(diagMap[y][x] == 2) {
                            diagonalCount++;
                        }
                    }
                }
            } else {
                int m = (p2.y - p1.y) / (p2.x - p1.x);
                int y = minX == p1.x ? p1.y : p2.y;
                for(int x = minX; x <= maxX; x++) {
                    diagMap[y][x]++;
                    if(diagMap[y][x] == 2) {
                        diagonalCount++;
                    }
                    y += m;
                }
            }
        }
    }
}