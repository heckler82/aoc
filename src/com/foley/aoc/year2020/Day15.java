package com.foley.aoc.year2020;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.Tuple;
import com.foley.aoc.util.functions.Compute;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Solutions for day 15
 *
 * @author Evan Foley
 * @version 24 Nov 2020
 */
public class Day15 extends Daily {
    private int turn;
    private int max;
    private Map<Integer, Tuple<Integer, Integer>> spoken = new HashMap<>();


    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day15(String fileName) {
        super(fileName);
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        turn = 0;
        int lastSpoken = 0;
        int last = 0;
        int[] start = Compute.convertToIntArray(input[0].split(","));

        int[] vals = new int[30000001];
        Arrays.fill(vals, -1);

        for(int i = 0; i < start.length; i++) {
            vals[start[i]] = i;
        }

        int turns = start.length + 1;

        while(turns < 30000000) {
            int lastSpokenIndex = vals[lastSpoken];
            vals[lastSpoken] = turns - 1;
            lastSpoken = lastSpokenIndex == -1 ? 0 : turns - 1 - lastSpokenIndex;
            turns++;
            if(turns == 2020) {
                last = lastSpoken;
            }
        }
        max = lastSpoken;
        System.out.printf("Final value is %d\n", last);

        /**
        // Seed
        for(int i : start) {
            turn++;
            spoken.put(i, Tuple.pair(turn, -1));
            lastSpoken = i;
        }

        while(turn < 30000000) {
            var t = spoken.getOrDefault(lastSpoken, Tuple.pair(-1, -1));
            lastSpoken = takeTurn(lastSpoken, t.getSecond() == -1);
            if(turn == 2020) {
                last = lastSpoken;
            }
        }
        max = lastSpoken;
        System.out.printf("The final number is %d\n", last);*/
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        System.out.printf("The final number is %d\n", max);
    }

    /**
     * Takes a turn, and returns the value of the calculated spoken number
     *
     * @return
     */
    private int takeTurn(int lastSpoken, boolean firstTime) {
        turn++;
        if(firstTime) {
            var t = spoken.getOrDefault(0, Tuple.pair(-1, -1));
            spoken.put(0, Tuple.pair(turn, t.getFirst()));
            return 0;
        }
        var t = spoken.get(lastSpoken);
        int diff = t.getFirst() - t.getSecond();
        if(!spoken.containsKey(diff)) {
            spoken.put(diff, Tuple.pair(turn, -1));
        } else {
            var upd = spoken.get(diff);
            spoken.put(diff, Tuple.pair(turn, upd.getFirst()));
        }
        return diff;
    }
}
