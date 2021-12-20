package com.foley.aoc.year2021;

import com.foley.aoc.util.Daily;

/**
 * Solutions for day 18
 *
 * @author Evan Foley
 * @version 18 Dec 2021
 */
public class Day18 extends Daily {
    /**
     * Creates a new daily
     *
     * @param year The year
     * @param fileName The name of the input file
     */
    public Day18(int year, String fileName) {
        super(year, fileName);
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        String s = input[0];
        for(int i = 1; i < input.length; i++) {
            s = "[" + s + "," + input[i] + "]";
            reduce(s);
        }
        System.out.printf("The magnitude of the final sum is %d\n", magnitude(s));
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
    }

    private void reduce(String s) {

    }

    private void explode() {

    }

    private void split() {
        
    }

    private long magnitude(String s) {
        return 0L;
    }
}