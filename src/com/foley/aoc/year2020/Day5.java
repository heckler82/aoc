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

            // Find row
            int row = findCell(s, 0, 127, 0, 7, 'F');
            int col = findCell(s, 0, 7, 7, s.length(), 'L');

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

    /**
     * Finds the final value of the row or column from a given bsp string
     *
     * @param s The BSP string
     * @param lower The lower limit
     * @param upper The upper limit
     * @param start The starting point in the bsp string
     * @param end The ending point in the bsp string
     * @param front The character that determines which half to take
     * @return The final calculated row or column
     */
    private int findCell(String s, int lower, int upper, int start, int end, char front) {
        int lo = lower;
        int hi = upper;
        int row = upper + 1;

        for(int i = start; i < end; i++) {
            row /= 2;
            if(s.charAt(i) == front) {
                hi -= row;
            } else {
                lo += row;
            }
        }
        return lo;
    }
}
