package com.foley.aoc.year2021;

import com.foley.aoc.util.Daily;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

/**
 * Solutions for day 11
 *
 * @author Evan Foley
 * @version 11 Dec 2021
 */
public class Day11 extends Daily {
    private int[][] map;

    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day11(String fileName) {
        super(fileName);
        map = new int[10][10];
        for(int y = 0; y < 10; y++) {
            for(int x = 0; x < 10; x++) {
                map[y][x] = input[y].charAt(x) - '0';
            }
        }
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        int count = 0;
        for(int t = 0; t < 100; t++) {
            count += sim();
        }
        System.out.printf("The number of flashes in 100 steps is %d\n", count);
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        int step = 0;
        int result = 0;
        while(result != 100) {
            result = sim();
            step++;
        }
        System.out.printf("The first time all the octopi flash simultaneously is step %d\n", step);
    }

    private int sim() {
        int count = 0;
        // Increment
        for(int y = 0; y < 10; y++) {
            for(int x = 0; x < 10; x++) {
                map[y][x]++;
            }
        }
        // Check for flashers
        Set<Point> flashed = new HashSet<>();
        boolean allFlashed = false;
        while(!allFlashed) {
            allFlashed = true;
            for (int y = 0; y < 10; y++) {
                for (int x = 0; x < 10; x++) {
                    var p = new Point(x, y);
                    if (!flashed.contains(p) && map[y][x] > 9) {
                        flashed.add(p);
                        count++;
                        allFlashed = false;
                        increment(x - 1, y - 1);
                        increment(x, y - 1);
                        increment(x + 1, y - 1);
                        increment(x - 1, y);
                        increment(x + 1, y);
                        increment(x - 1, y + 1);
                        increment(x, y + 1);
                        increment(x + 1, y + 1);
                    }
                }
            }
        }
        // Reset flashers to zero
        for(var p : flashed) {
            map[p.y][p.x] = 0;
        }
        return count;
    }

    private void increment(int x, int y) {
        if((y >= 0 && y < 10) && (x >= 0 && x < 10)) {
            map[y][x]++;
        }
    }

    private void printMap() {
        System.out.println();
        for(int[] arr : map) {
            for(int i : arr) {
                System.out.print(i);
            }
            System.out.println();
        }
    }
}