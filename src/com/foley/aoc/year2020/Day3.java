package com.foley.aoc.year2020;

import com.foley.aoc.util.Daily;

import java.awt.Point;

/**
 * Solutions for day 3
 *
 * @author Evan Foley
 * @version 24 Nov 2020
 */
public class Day3 extends Daily {
    private char[][] map;
    private int clamp;
    
    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day3(String fileName) {
        super(fileName);
        char[][] map = processMap(input);
        int clamp = map[0].length;
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        System.out.printf("There were %d trees encountered\n", countTrees(map, 3, 1));
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        // The answer from part one
        long init = 294L;
        // Additional slopes to check
        Tuple<Integer, Integer>[] slopes = {new Point(1, 1),
                                            new Point(5, 1),
                                            new Point(7, 1),
                                            new Point(1, 2)};
        // Get the tree count for each slop and multiply it with the last result
        for(Point p : slopes) {
            init *= countTrees(map, p.x, p.y);
        }
        System.out.printf("The product of all tree encounters is %d\n", init);
    }
    
    /**
    * Counts the number of trees in the map for a given slope
    * 
    * @param map The character map to search
    * @param slopeX The x value of the slope
    * @param slopeY The y value of the slope
    * @return The number of trees encountered in the map
    */
    private int countTrees(char[][] map, int slopeX, int slopeY) {
        Point pos = new Point(0, 0);
        int treeCount = 0;
        // Continue to parse until vertically exiting the map bounds
        while(pos.y < map.length) {
            treeCount += map[pos.y][pos.x] == '#' ? 1 : 0;
            // Clamp the x coordinate to stay within horizontal map bounds
            pos.x = (pos.x + slopeX) % clamp;
            pos.y += slopeY;
        }
        return treeCount;
    }
    
    /**
    * Processes the character map from the input
    * 
    * @param in The input array
    * @return The character map
    */
    private char[][] processMap(String[] in) {
        char[][] map = new char[in.length][in[0].length()];
        for(int y = 0; y < in.length; y++) {
            for(int x = 0; x < in[y].length(); x++) {
                map[y][x] = in[y].charAt(x);
            }
        }
    }
}
