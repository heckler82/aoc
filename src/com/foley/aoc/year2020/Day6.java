package com.foley.aoc.year2020;

import com.foley.aoc.util.Daily;

import java.util.Arrays;
import java.util.BitSet;

/**
 * Solutions for day 6
 *
 * @author Evan Foley
 * @version 24 Nov 2020
 */
public class Day6 extends Daily {
    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day6(String fileName) {
        super(fileName);
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        int sum = 0;
        StringBuilder grp = new StringBuilder();

        for(int i = 0; i < input.length; i++) {
            grp.append(input[i]);
            if("".equals(input[i]) || i == input.length - 1) {
                sum += grp.chars().distinct().count();
                grp.setLength(0);
                continue;
            }
        }

        System.out.printf("The sum of the question counts is %d\n", sum);
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        int sum = 0;
        int grp = Integer.MAX_VALUE;

        for(int i = 0; i < input.length; i++) {
            String s = input[i];
            int buckets = 0;

            // Activate all bits from the string and keep only repeats, exclude empty strings
            for(char c : s.toCharArray()) {
                buckets |= 1 << (c - 'a');
            }
            grp &= (s.length() == 0) ? grp : buckets;

            if("".equals(s) || i == input.length - 1) {
                sum += Integer.bitCount(grp);
                grp = Integer.MAX_VALUE;
                continue;
            }
        }

        System.out.printf("The sum of the questions everyone answered yes to is %d\n", sum);
    }
}
