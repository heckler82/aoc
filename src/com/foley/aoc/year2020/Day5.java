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
            int row = 127;
            int col = 7;
            int rowMin = 0;
            int colMin = 0;

            // Find row
            for(int i = 0; i < 7; i++) {
                if(s.charAt(i) == 'F') {
                    row = (row + rowMin) / 2;
                } else {
                    rowMin = (row + rowMin) / 2 + 1;
                }
            }
            for(int i = 7; i < 10; i++) {
                if(s.charAt(i) == 'L') {
                    col = (col + colMin) / 2;
                } else {
                    colMin = (col + colMin) / 2 + 1;
                }
            }
            int id = row * 8 + col;
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
        for(int i = 0; i < 824; i++) {
            if(list.get(i) != (i + 11)) {
                System.out.printf("Your seat is number %d\n", i + 11);
                break;
            }
        }
    }
}
