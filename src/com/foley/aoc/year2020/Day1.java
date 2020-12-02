package com.foley.aoc.year2020;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.Functions;
import com.foley.aoc.util.Triple;
import com.foley.aoc.util.Tuple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Solutions for day 1
 *
 * @author Evan Foley
 * @version 24 Nov 2020
 */
public class Day1 extends Daily {
    private int[] ints;
    
    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day1(String fileName) {
        super(fileName);
        ints = Functions.convertToIntArray(input);
        Arrays.sort(ints);
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        Tuple<Integer, Integer> t = Functions.twoSum(ints, 2020);
        if(t.getFirst() > -1) {
            System.out.printf("2020 found at indexes %d and %d with a product of %d\n", t.getFirst(), t.getSecond(), ints[t.getFirst()] * ints[t.getSecond()]);
        }
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        Triple<Integer, Integer, Integer> t = Functions.threeSum(ints, 2020);
        if(t.getFirst() > -1) {
            System.out.printf("2020 found at indexes %d, %d, and %d with a product of %d\n", t.getFirst(), t.getSecond(), t.getThird(), ints[t.getFirst()] * ints[t.getSecond()] * ints[t.getThird()]);
        }
    }
}
