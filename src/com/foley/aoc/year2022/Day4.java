package com.foley.aoc.year2022;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.Tuple;
import com.foley.aoc.util.functions.Range;

import java.util.ArrayList;
import java.util.List;

/**
 * Solutions for day 4
 *
 * @author Evan Foley
 * @version 4 Dec 2022
 */
public class Day4 extends Daily {
    private List<Tuple<Range, Range>> ranges;

    /**
     * Creates a new daily
     *
     * @param year     The year
     * @param fileName The name of the input file
     */
    public Day4(int year, String fileName) {
        super(year, fileName);
        ranges = new ArrayList<>();
        for(var s : input) {
            var split = s.split(",");
            var left = split[0].split("-");
            var right = split[1].split("-");
            var leftRange = new Range(Integer.parseInt(left[0]), Integer.parseInt(left[1]) + 1);
            var rightRange = new Range(Integer.parseInt(right[0]), Integer.parseInt(right[1]) + 1);
            ranges.add(Tuple.pair(leftRange, rightRange));
        }
    }

    @Override
    protected void task1() {
        int numPairs = 0;
        for(var t : ranges) {
            if(t.getFirst().fullyInRange(t.getSecond()) || t.getSecond().fullyInRange(t.getFirst())) {
                numPairs++;
            }
        }
        System.out.printf("There are %d pairs that overlap\n", numPairs);
    }

    @Override
    protected void task2() {
        int numPairs = 0;
        for(var t : ranges) {
            if(t.getFirst().inRange(t.getSecond())) {
                numPairs++;
            }
        }
        System.out.printf("There are %d pairs that overlap\n", numPairs);
    }
}
