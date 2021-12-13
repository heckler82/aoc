package com.foley.aoc.year2021;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.functions.Compute;

/**
 * Solutions for day 1
 *
 * @author Evan Foley
 * @version 01 Dec 2021
 */
public class Day1 extends Daily {
    private int[] depths;

    /**
     * Creates a new daily
     *
     * @param year The year
     * @param fileName The name of the input file
     */
    public Day1(int year, String fileName) {
        super(year, fileName);
        depths = Compute.convertToIntArray(input);
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        int count = 0;
        int prev = depths[0];
        for(int i = 1; i < depths.length; i++) {
            if(prev < depths[i]) {
                count++;
            }
            prev = depths[i];
        }
        System.out.printf("There are %d measurements that are larger than the previous measurement\n", count);
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        int count = 0;
        int prev = depths[0] + depths[1] + depths[2];
        for(int i = 1; i < depths.length - 2; i++) {
            int sum = depths[i] + depths[i + 1] + depths[i + 2];
            if(prev < sum) {
                count++;
            }
            prev = sum;
        }
        System.out.printf("There are %d measurements that are larger than the previous measurement\n", count);
    }
}