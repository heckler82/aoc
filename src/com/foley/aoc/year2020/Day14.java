package com.foley.aoc.year2020;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.functions.Regex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;

/**
 * Solutions for day 14
 *
 * @author Evan Foley
 * @version 24 Nov 2020
 */
public class Day14 extends Daily {
    private Map<Long, Long> mem;
    private final String wrtPat = "\\[(\\d+)\\]\\s+=\\s+(\\d+)";
    private String mask;

    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day14(String fileName) {
        super(fileName);
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        mem = new HashMap<>();
        for(String s : input) {
            Matcher m = Regex.getMatcher(wrtPat, s);
            if(m.find()) {
                long addr = Long.parseLong(m.group(1));
                long val = Long.parseLong(m.group(2));
                // Apply mask here
                val = applyMask(mask, val);
                mem.put(addr, val);
            } else {
                // Get mask here
                mask = s.substring(7);
            }
        }
        System.out.printf("The sum of all memory is %d\n", sumMem());
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        mem = new HashMap<>();
        for(String s : input) {
            Matcher m = Regex.getMatcher(wrtPat, s);
            if(m.find()) {
                long addr = Long.parseLong(m.group(1));
                long val = Long.parseLong(m.group(2));
                // Apply mask here
                Set<Long> addresses = applyAddressMask(mask, addr);
                for(long l : addresses) {
                    mem.put(l, val);
                }
            } else {
                // Get mask here
                mask = s.substring(7);
            }
        }
        System.out.printf("The sum of all memory is %d\n", sumMem());
    }

    /**
     * Applies a mask to a value and returns the new number
     *
     * @param mask The mask in string form
     * @param val The value to mask
     * @return The masked value
     */
    private long applyMask(String mask, long val) {
        long maskVal = val;
        for(int i = mask.length() - 1; i >= 0; i--) {
            char c = mask.charAt(i);
            switch(c) {
                case '1':
                    maskVal |= 1L << (mask.length() - i) - 1;
                    break;
                case '0':
                    maskVal &= ~(1L << (mask.length() - i) - 1);
            }
        }
        return maskVal;
    }

    /**
     * Applies the rules for masking an address
     *
     * @param mask The mask in string form
     * @param val The value to mask
     * @return The masked value
     */
    private Set<Long> applyAddressMask(String mask, long val) {
        List<Integer> shiftIndex = new ArrayList<>();
        Set<Long> addresses = new HashSet<>();
        long maskVal = val;
        for(int i = mask.length() - 1; i >= 0; i--) {
            char c = mask.charAt(i);
            switch(c) {
                case '1':
                    maskVal |= 1L << (mask.length() - i) - 1;
                    break;
                case 'X':
                    shiftIndex.add((mask.length() - i) - 1);
            }
        }

        // Reset all floating bits to 0
        for(int i : shiftIndex) {
            maskVal &= ~(1L << i);
        }
        long start = maskVal;

        var combo = getCombinations(shiftIndex);
        int count = 0;
        for(var sub : combo) {
            count++;
            maskVal = start;
            for(int i : sub) {
                maskVal |= (1L << i);
            }
            addresses.add(maskVal);
        }

        return addresses;
    }

    /**
     * Adds up all non-zero values in memory
     *
     * @return The sum of all non-zero numbers in memory
     */
    private long sumMem() {
        long sum = 0;
        for(long l : mem.values()) {
            sum += l == 0 ? 0 : l;
        }
        return sum;
    }

    private Set<Set<Integer>> getCombinations(List<Integer> list) {
        Set<Set<Integer>> s = new HashSet<>();
        int p = (int)Math.pow(2, list.size());
        for(int count = 0; count < p; count++) {
            Set<Integer> c = new HashSet<>();
            for(int i = 0; i < list.size(); i++) {
                if((count & (1 << i)) > 0) {
                    c.add(list.get(i));
                }
            }
            s.add(c);
        }
        return s;
    }
}
