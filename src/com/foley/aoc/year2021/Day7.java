package com.foley.aoc.year2021;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.functions.Compute;
import com.foley.aoc.util.functions.Regex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Solutions for day 7
 *
 * @author Evan Foley
 * @version 07 Dec 2021
 */
public class Day7 extends Daily {
    private List<Integer> hPos;
    private int max;

    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day7(String fileName) {
        super(fileName);
        var list = Regex.getMatches("\\d+", input[0]);
        hPos = new ArrayList<>();
        for(var v : list) {
            int key = Integer.parseInt(v);
            hPos.add(key);
            if(key > max) {
                max = key;
            }
        }
        Collections.sort(hPos);
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        int median = hPos.get(hPos.size() / 2);
        int sum = 0;
        for(int x : hPos) {
            sum += Math.abs(x - median);
        }
        System.out.printf("The least amount of fuel that is used is %d units\n", sum);
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        System.out.printf("The least amount of fuel that is used is %d units\n", align());
    }

    /**
     * Align the crabs
     *
     * @return The least amount of fuel used to align the crabs
     */
    private int align() {
        int min = Integer.MAX_VALUE;
        for(int i = 1; i <= max; i++) {
            int fuel = 0;
            for(int j : hPos) {
                if(j == i) {
                    continue;
                }
                int n = Math.abs(j - i);
                int add = Compute.binomialCoefficient(n);
                fuel += add;
            }
            if(fuel < min) {
                min = fuel;
            }
        }
        return min;
    }
}