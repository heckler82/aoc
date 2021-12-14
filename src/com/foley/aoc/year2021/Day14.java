package com.foley.aoc.year2021;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.Tuple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Solutions for day 14
 *
 * @author Evan Foley
 * @version 14 Dec 2021
 */
public class Day14 extends Daily {
    private Map<String, Tuple<String, String>> map;
    private Map<String, Long> pairs;
    private Map<Character, Long> freq;

    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day14(int year, String fileName) {
        super(year, fileName);
        map = new HashMap<>();
        pairs = new HashMap<>();
        freq = new HashMap<>();

        String polymer = input[0];
        freq.put(polymer.charAt(0), 1L);
        for(int i = 1; i < polymer.length(); i++) {
            var key = polymer.substring(i - 1, i + 1);
            pairs.put(key, 1L + pairs.getOrDefault(key, 0L));
            freq.put(polymer.charAt(i), 1L + freq.getOrDefault(polymer.charAt(i), 0L));
        }

        for(int i = 2; i < input.length; i++) {
            var kvp = input[i].split("\\s+->\\s+");
            map.put(kvp[0], Tuple.pair(kvp[0].charAt(0) + kvp[1], kvp[1] + kvp[0].charAt(1)));
        }
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        polymerize(10);
        long max = freq.values().stream().max((Long a, Long b) -> Long.compare(a, b)).get();
        long min = freq.values().stream().min((Long a, Long b) -> Long.compare(a, b)).get();
        System.out.printf("The difference between the most and least common element is %d\n", max - min);
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        polymerize(30);
        long max = freq.values().stream().max((Long a, Long b) -> Long.compare(a, b)).get();
        long min = freq.values().stream().min((Long a, Long b) -> Long.compare(a, b)).get();
        System.out.printf("The difference between the most and least common element is %d\n", max - min);
    }

    /**
     * Performs the polymerization process 'step' number of times
     *
     * @param steps the numbers of times to polymerize
     */
    private void polymerize(int steps) {
        for(int t = 0; t < steps; t++) {
            var newPairs = new HashMap<String, Long>();
            for(var key : pairs.keySet()) {
                long numPairs = pairs.get(key);
                String newKey = map.get(key).getFirst();
                newPairs.put(newKey, numPairs + newPairs.getOrDefault(newKey, 0L));
                newKey = map.get(key).getSecond();
                newPairs.put(newKey, numPairs + newPairs.getOrDefault(newKey, 0L));
                freq.put(newKey.charAt(0), numPairs + freq.getOrDefault(newKey.charAt(0), 0L));
            }
            pairs = newPairs;
        }
    }
}