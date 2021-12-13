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
    List<Integer> list;
    private int deviceRating;

    /**
     * Creates a new daily
     *
     * @param year The year
     * @param fileName The name of the input file
     */
    public Day10(int year, String fileName) {
        super(year, fileName);
        list = Compute.convertToIntegerList(input);
        list.add(0);
        Collections.sort(list);
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        int jolt = list.get(0);
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 1; i < list.size(); i++) {
            int val = list.get(i);
            int diff = val - jolt;
            jolt = val;
            map.put(diff, map.getOrDefault(diff, 0) + 1);
        }
        deviceRating = list.get(list.size() - 1) + 3;
        System.out.printf("The answer is %d\n", map.get(1) * (map.get(3) + 1));
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        list.add(deviceRating);
        Map<Integer, Long> map = new HashMap<>();
        map.put(0, 1L);
        for(int i = 1; i < list.size(); i++) {
            int val = list.get(i);
            long sum = map.getOrDefault(val - 3, 0L) +
                    map.getOrDefault(val - 2, 0L) +
                    map.getOrDefault(val - 1, 0L);
            map.put(list.get(i), sum);
        }
        System.out.printf("The number of distinct valid arrangements is %d\n", map.get(deviceRating));
    }
}
