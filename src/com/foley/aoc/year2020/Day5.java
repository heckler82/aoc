package com.foley.aoc.year2020;

import com.foley.aoc.util.Daily;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Solutions for day 5
 *
 * @author Evan Foley
 * @version 24 Nov 2020
 */
public class Day5 extends Daily {
    private List<Integer> list;

    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day5(String fileName) {
        super(fileName);
        list = new ArrayList<>();
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        int maxId = Integer.MIN_VALUE;

        for(String s : input) {

            int id = 0;
            for(char c : s.toCharArray()) {
                id *= 2;
                id = id + (((c == 'B') || (c == 'R')) ? 1 : 0);
            }

            list.add(id);
            maxId = Math.max(id, maxId);
        }
        System.out.printf("Highest seat ID is %d\n", maxId);
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        Collections.sort(list);
        int expectedSeat = list.get(0);
        final int start = expectedSeat;

        for(int i = 0; i < list.size(); i++) {
            if(list.get(i) != expectedSeat) {
                System.out.printf("Your seat is number %d\n", i + start);
                break;
            }
            expectedSeat++;
        }
    }
}
