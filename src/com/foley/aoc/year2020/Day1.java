package com.foley.aoc.year2020;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.Functions;

import java.util.Arrays;

/**
 * Solutions for day 1
 *
 * @author Evan Foley
 * @version 24 Nov 2020
 */
public class Day1 extends Daily {
    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day1(String fileName) {
        super(fileName);
        //day = 1;
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        int[] ints = Functions.convertToIntArray(input);
        Arrays.sort(ints);
        
        // Search for value in array that will sum to 2020 with the current value
        for(int i : ints) {
            int index = Functions.binarySearch(ints, 2020 - i);
            // If index is non-negative, the answer has been found
            if(index >= 0) {
                System.out.printf("2020 found @ <x = %d, y = %d>\n", i, ints[index]);
                System.out.printf("The product of the two is %d\n", i, ints[index]);
                return;
            }
        }
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {

    }
}
