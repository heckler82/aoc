package com.foley.aoc.year2020;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.functions.Compute;
import com.foley.aoc.util.functions.Regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * Solutions for day 16
 *
 * @author Evan Foley
 * @version 24 Nov 2020
 */
public class Day16 extends Daily {
    private List<List<Integer>> validTickets;
    private List<Range> rules;
    private List<Range> departure;
    private int[] myTicket;

    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day16(String fileName) {
        super(fileName);
        validTickets = new ArrayList<>();
        rules = new ArrayList<>();
        departure = new ArrayList<>();
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        List<Integer> invalid = new ArrayList<>();
        long sum = 0;

        // Split up input
        for(int i = 0; i < input.length; i++) {
            String s = input[i];
            // Ignore blank lines
            if("".equals(s)) {
                continue;
            }

            // This is my ticket
            if(s.equals("your ticket:")) {
                myTicket = Compute.convertToIntArray(input[++i].split(","));
                continue;
            }

            // Try and get ranges
            Matcher m = Regex.getMatcher("(.+):\\s+(\\d+)-(\\d+)\\s+or\\s+(\\d+)-(\\d+)", s);
            if(m.find()) {
                Range r = new Range(Integer.parseInt(m.group(2)), Integer.parseInt(m.group(3)),
                        Integer.parseInt(m.group(4)), Integer.parseInt(m.group(5)));
                rules.add(r);
                if(m.group(1).startsWith("departure")) {
                    departure.add(r);
                }
            } else {
                if(!"nearby tickets:".equals(s)) {
                    List<Integer> nums = Compute.convertToIntegerList(s.split(","));
                    boolean ticketValid = true;
                    for(int num : nums) {
                        boolean valid = false;
                        for(Range r : rules) {
                            valid |= r.validate(num);
                        }
                        if(!valid) {
                            invalid.add(num);
                            sum += num;
                            ticketValid = false;
                        }
                    }
                    if(ticketValid) {
                        validTickets.add(nums);
                    }
                }
            }
        }

        System.out.printf("The error rate is %d\n", sum);
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        for(List<Integer> list : validTickets) {
            for(int i : list) {
                
            }
        }
    }

    /**
     * Helper class for determining if a number falls within a range
     */
    private class Range {
        private int low;
        private int high;
        private Range other;

        public Range(int low, int high) {
            this.low = low;
            this.high = high;
        }

        public Range(int low, int high, int low2, int high2) {
            this.low = low;
            this.high = high;
            other = new Range(low2, high2);
        }

        public boolean validate(int num) {
            boolean b = num >= low && num <= high;
            if(other != null) {
                b |= other.validate(num);
            }
            return b;
        }
    }
}
