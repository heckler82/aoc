package com.foley.aoc.year2020;

import com.foley.aoc.util.Daily;

import java.util.List;

/**
 * Solutions for day 23
 *
 * @author Evan Foley
 * @version 24 Nov 2020
 */
public class Day23 extends Daily {
    private int[] next;
    private int[] nextEx;
    private int first;

    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day23(String fileName) {
        super(fileName);
        next = new int[10];
        nextEx = new int[1000001];

        // Fill the array
        int prev = 0;
        first = -1;
        for(int i = 0; i < 9; i++) {
            int index = input[0].charAt(i) - '0';
            if(i == 0) {
                first = index;
            }
            next[prev] = index;
            nextEx[prev] = index;
            prev = index;
        }
        next[prev] = first;
        // Fill in the remaining stuff for part 2
        for(int i = 10; i < 1000001; i++) {
            nextEx[prev] = i;
            prev = i;
        }
        nextEx[prev] = first;
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        simulate(next, 100, first);
        printFrom(1);
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        simulate(nextEx, 10000000, first);
        long v1 = nextEx[1];
        long v2 = nextEx[nextEx[1]];
        System.out.printf("The final result is %d\n", v1 * v2);
    }

    /**
     * Plays that fucking crab game
     *
     * @param state The state of the crab game
     * @param iterations The number of times to play the crab game
     * @param start The starting cup
     */
    private void simulate(int[] state, int iterations, int start) {
        for(int i = 0; i < iterations; i++) {
            // Pick up the 3 cups immediately clockwise of the current cup
            List<Integer> temp = List.of(state[start], state[state[start]], state[state[state[start]]]);
            // Select new destination cup (1 less than current cup); ensure it is not in the temp list
            int dest = start - 1;
            state[start] = state[state[state[state[start]]]];
            while(temp.contains(dest) || dest == 0) {
                dest--;
                if(dest <= 0) {
                    dest = state.length - 1;
                }
            }
            // Place temp list immediately clockwise of destination cup
            int finalDest = state[dest];
            state[dest] = temp.get(0);
            state[temp.get(2)] = finalDest;
            // Current cup becomes the cup immediately clockwise of the current cup
            start = state[start];
        }
    }

    /**
     * Prints out the next value starting from an initial value
     *
     * @param start The starting value
     */
    private void printFrom(int start) {
        int term = start;
        while(next[start] != term) {
            System.out.print(next[start]);
            start = next[start];
        }
        System.out.println();
    }
}
