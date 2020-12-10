package com.foley.aoc.year2020;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.functions.Compute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Solutions for day 10
 *
 * @author Evan Foley
 * @version 24 Nov 2020
 */
public class Day10 extends Daily {
    private int[] adapters;
    private int deviceRating;

    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day10(String fileName) {
        super(fileName);
        adapters = Compute.convertToIntArray(input);
        Arrays.sort(adapters);
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        int jolt = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i : adapters) {
            int diff = i - jolt;
            jolt = i;
            map.put(diff, map.getOrDefault(diff, 0) + 1);
        }
        deviceRating = adapters[adapters.length - 1] + 3;
        System.out.printf("The answer is %d\n", map.get(1) * (map.get(3) + 1));
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        List<Integer> ints = new ArrayList<>();
        ints.add(0);
        ints.add(deviceRating);
        for(String s : input) {
            ints.add(Integer.parseInt(s));
        }
        Collections.sort(ints);
        int pow2 = 0;
        int pow7 = 0;
        for(int i = 1; i < ints.size() - 1; i++) {
            long neg3 = (i >= 3) ? ints.get(i - 3) : -9999;
            if(ints.get(i + 1) - neg3 == 4) {
                pow7 += 1;
                pow2 -= 2;
            } else {
                if(ints.get(i + 1) - ints.get(i - 1) == 2) {
                    pow2++;
                }
            }
        }
        long count = (long)(Math.pow(2, pow2) * Math.pow(7, pow7));
        System.out.printf("The number of distinct valid arrangements is %d\n", count);
    }
}
