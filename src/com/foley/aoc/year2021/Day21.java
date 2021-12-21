package com.foley.aoc.year2021;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.Tuple;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Solutions for day 21. Credit for part 2 goes to Jonathan Paulson. Thank you for teaching me all that you do on your videos
 *
 * @author Evan Foley
 * @version 21 Dec 2021
 */
public class Day21 extends Daily {
    private Map<Integer, Tuple<Long, Long>> cache;
    private int p1;
    private int p2;
    private int die;

    /**
     * Creates a new daily
     *
     * @param year The year
     * @param fileName The name of the input file
     */
    public Day21(int year, String fileName) {
        super(year, fileName);
        cache = new HashMap<>();
        p1 = Integer.parseInt(input[0].substring(input[0].length() - 1)) - 1;
        p2 = Integer.parseInt(input[1].substring(input[1].length() - 1)) - 1;
        die = 0;
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        int score1 = 0;
        int score2 = 0;

        while(true) {
            int move = roll() + roll() + roll();
            p1 = (p1 + move) % 10;
            score1 += p1 + 1;
            if(score1 >= 1000) {
                System.out.printf("Player 1 wins. The answer to part 1 is %d\n", (long)(score2 * die));
                return;
            }
            move = roll() + roll() + roll();
            p2 = (p2 + move) % 10;
            score2 += p2 + 1;
            if(score2 >= 1000) {
                System.out.printf("Player 2 wins. The answer to part 1 is %d\n", (long)(score1 * die));
                return;
            }
        }
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        p1 = Integer.parseInt(input[0].substring(input[0].length() - 1)) - 1;
        p2 = Integer.parseInt(input[1].substring(input[1].length() - 1)) - 1;

        var t = countWin(p1, p2, 0, 0);
        System.out.printf("The most wins was %d\n", Math.max(t.getFirst(), t.getSecond()));
    }

    private Tuple<Long, Long> countWin(int p1, int p2, int s1, int s2) {
        if(s1 >= 21) {
            return Tuple.pair(1L, 0L);
        }
        if(s2 >= 21) {
            return Tuple.pair(0L, 1L);
        }
        // Cache check here
        int key = Objects.hash(p1, p2, s1, s2);
        if(cache.containsKey(key)) {
            return cache.get(key);
        }
        var t = Tuple.pair(0L, 0L);
        for(int d1 = 1; d1 <= 3; d1++) {
            for(int d2 = 1; d2 <= 3; d2++) {
                for(int d3 = 1; d3 <= 3; d3++) {
                    int newP1 = (p1 + d1 + d2 + d3) % 10;
                    int newS1 = s1 + newP1 + 1;

                    var t2 = countWin(p2, newP1, s2, newS1);
                    t = Tuple.pair(t.getFirst() + t2.getSecond(), t.getSecond() + t2.getFirst());
                }
            }
        }
        cache.put(key, t);
        return t;
    }

    private int roll() {
        die++;
        return die;
    }
}