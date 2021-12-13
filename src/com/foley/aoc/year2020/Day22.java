package com.foley.aoc.year2020;

import com.foley.aoc.util.Daily;

import java.util.*;

/**
 * Solutions for day 22
 *
 * @author Evan Foley
 * @version 24 Nov 2020
 */
public class Day22 extends Daily {
    private int deckSize;
    private List<Integer> p1;
    private List<Integer> p2;

    /**
     * Creates a new daily
     *
     * @param year The year
     * @param fileName The name of the input file
     */
    public Day22(int year, String fileName) {
        super(year, fileName);
        p1 = new ArrayList<>();
        p2 = new ArrayList<>();

        List<Integer>  curr = p1;
        for(int i = 1; i < input.length; i++) {
            String s = input[i];
            if("".equals(s)) {
                curr = p2;
                i += 1;
                continue;
            }
            curr.add(Integer.parseInt(s));
        }
        deckSize = p1.size() + p2.size();
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        // Continue until a winner is found
        while(p1.size() != deckSize && p2.size() != deckSize) {
            takeTurn();
        }

        List<Integer> winner = p1.size() == deckSize ? p1 : p2;
        System.out.printf("The winning score is %d\n", calcWinningScore(winner));
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
    }

    private void takeTurn() {
        int c1 = p1.remove(0);
        int c2 = p2.remove(0);
        if(c1 > c2) {
            p1.add(c1);
            p1.add(c2);
        } else {
            p2.add(c2);
            p2.add(c1);
        }
    }

    private long calcWinningScore(List<Integer> winner) {
        long sum = 0;
        for(int i = deckSize; i > 0; i--) {
            sum += i * winner.remove(0);
        }
        return sum;
    }
}
