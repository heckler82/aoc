package com.foley.aoc.year2020;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.functions.Compute;
import com.foley.aoc.util.functions.Regex;

import java.util.*;
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
                            valid |= r.inRange(num);
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
        // Establish a bucket for each column
        List<Set<Integer>> buckets = new ArrayList<>();
        Map<Integer, BitSet> candidates = new HashMap<>();
        for(int i = 0; i < myTicket.length; i++) {
            buckets.add(new HashSet<>());
            candidates.put(i, new BitSet(myTicket.length));
        }

        // Fill up the buckets with column values
        for(List<Integer> list : validTickets) {
            for(int i = 0; i < list.size(); i++) {
                buckets.get(i).add(list.get(i));
            }
        }

        // Determine which rules fit each bucket
        for(int j = 0; j < rules.size(); j++) {
            Range r = rules.get(j);
            for (int i = 0; i < buckets.size(); i++) {
                var bucket = buckets.get(i);
                boolean fit = true;
                for (int num : bucket) {
                    if(!r.inRange(num)) {
                        fit = false;
                        break;
                    }
                }
                // This is a potential candidate for this column
                if(fit) {
                    candidates.get(i).set(j);
                }
            }
        }

        int[] finalRules = new int[myTicket.length];
        while(candidates.size() > 0) {
            for (int i = 0; i < myTicket.length; i++) {
                BitSet bit = candidates.get(i);
                if (bit != null && bit.cardinality() == 1) {
                    finalRules[i] = bit.nextSetBit(0);
                    candidates.remove(i);
                    for (BitSet b : candidates.values()) {
                        b.clear(finalRules[i]);
                    }
                }
            }
        }

        long prod = 1;
        for(int i = 0; i < finalRules.length; i++) {
            if(finalRules[i] < departure.size()) {
                prod *= myTicket[i];
            }
        }
        System.out.printf("The final value is %d\n", prod);
    }

    /**
     * Helper class for determining if a number falls within a range
     */
    private class Range {
        private int low;
        private int high;
        private int low2;
        private int high2;

        public Range(int low, int high, int low2, int high2) {
            this.low = low;
            this.high = high;
            this.low2 = low2;
            this.high2 = high2;
        }

        public boolean inRange(int num) {
            return (num >= low && num <= high) || (num >= low2 && num <= high2);
        }
    }
}
