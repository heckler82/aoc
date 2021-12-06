package com.foley.aoc.year2021;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.functions.Regex;

import java.util.regex.Matcher;

/**
 * Solutions for day 1
 *
 * @author Evan Foley
 * @version 06 Dec 2021
 */
public class Day6 extends Daily {
    private long[] fishTimers = new long[9];

    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day6(String fileName) {
        super(fileName);
        Matcher m = Regex.getMatcher("\\d+", input[0]);
        while(m.find()) {
            fishTimers[Integer.parseInt(m.group())]++;
        }
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        System.out.printf("There are %d fish after 80 days\n", simulate(80));
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        System.out.printf("There are %d fish after 256 days\n", simulate(256 - 80));
    }

    /**
     * Simulates the fish world
     *
     * @param numDays the number of days to simluate
     * @return the number of fish at the end of the simmulation
     */
    private long simulate(int numDays) {
        for(int i = 0; i < numDays; i++) {
            long[] nextGen = new long[9];
            for(int t = 8; t > -1; t--) {
                if(t == 0) {
                    nextGen[8] = fishTimers[0];
                    nextGen[6] += fishTimers[0];
                } else {
                    nextGen[t - 1] = fishTimers[t];
                }
            }
            fishTimers = nextGen;
        }
        // Get grand total
        long sum = 0L;
        for(long l : fishTimers) {
            sum += l;
        }
        return sum;
    }
}