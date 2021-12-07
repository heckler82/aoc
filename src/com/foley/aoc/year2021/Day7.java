package com.foley.aoc.year2021;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.functions.Regex;

import java.util.HashMap;
import java.util.Map;

/**
 * Solutions for day 7
 *
 * @author Evan Foley
 * @version 07 Dec 2021
 */
public class Day7 extends Daily {
    private Map<Integer, Integer> pos;
    private int max;

    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day7(String fileName) {
        super(fileName);
        var list = Regex.getMatches("\\d+", input[0]);
        pos = new HashMap<>();
        for(var v : list) {
            int key = Integer.parseInt(v);
            if(key > max) {
                max = key;
            }
            pos.put(key, pos.getOrDefault(key, 0) + 1);
        }
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        System.out.printf("The least amount of fuel that is used is %d units\n", align(false));
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        System.out.printf("The least amount of fuel that is used is %d units\n", align(true));
    }

    /**
     * Align the crabs
     *
     * @param useGrowth true to use the growing crab fuel cost
     * @return
     */
    private int align(boolean useGrowth) {
        int min = Integer.MAX_VALUE;
        for(int i = 1; i <= max; i++) {
            int fuel = 0;
            for(int j : pos.keySet()) {
                if(j == i) {
                    continue;
                }
                int n = (Math.abs(j - i));
                int add = (useGrowth ? ((n * (n + 1)) / 2) : n) * pos.get(j);
                fuel += add;
            }
            if(fuel < min) {
                min = fuel;
            }
        }
        return min;
    }
}