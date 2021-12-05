package com.foley.aoc.year2021;

import com.foley.aoc.util.Daily;

import java.util.ArrayList;
import java.util.List;

/**
 * Solutions for day 3
 *
 * @author Evan Foley
 * @version 03 Dec 2021
 */
public class Day3 extends Daily {
    private int[] one;
    private int[] zero;
    private List<String> oConsider;
    private List<String> co2Consider;

    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day3(String fileName) {
        super(fileName);
        one = new int[12];
        zero = new int[12];
        oConsider = new ArrayList<>();
        co2Consider = new ArrayList<>();
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        for(String s : input) {
            oConsider.add(s);
            co2Consider.add(s);
            for(int i = 0; i < s.length(); i++) {
                if('1' == s.charAt(i)) {
                    one[i]++;
                } else {
                    zero[i]++;
                }
            }
        }
        StringBuilder gRateBuild = new StringBuilder();
        StringBuilder eRateBuild = new StringBuilder();
        for(int i = 0; i < one.length; i++) {
            if(one[i] > zero[i]) {
                gRateBuild.append("1");
                eRateBuild.append("0");
            } else {
                gRateBuild.append("0");
                eRateBuild.append("1");
            }
        }
        long gRate = Integer.parseInt(gRateBuild.toString(), 2);
        long eRate = Integer.parseInt(eRateBuild.toString(), 2);
        System.out.printf("The power consumption is %d\n", gRate * eRate);
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        String oxyRem = getFinalString(oConsider, true);
        String co2Rem = getFinalString(co2Consider, false);

        System.out.printf("The life support rating is %d\n", Integer.parseInt(oxyRem, 2) * Integer.parseInt(co2Rem, 2));
    }

    /**
     * Gets the remaining string value in a list according to filtering
     *
     * @param strings The initial list of strings
     * @param useMostCommon True if the most common value should be considered
     * @return The final remaining string that matches the filter
     */
    private static String getFinalString(List<String> strings, boolean useMostCommon) {
        int pos = 0;
        int ones = 0;
        int zeroes = 0;

        while(strings.size() > 1) {
            for(String s : strings) {
                if('1' == s.charAt(pos)) {
                    ones++;
                } else {
                    zeroes++;
                }
            }
            List<String> keep = new ArrayList<>();
            char use = '0';
            if(useMostCommon) {
                use = ones - zeroes > 0 ? '1' : ones == zeroes ? '1' : '0';
            } else {
                use = ones - zeroes < 0 ? '1' : ones == zeroes ? '0' : '0';
            }
            for(String s : strings) {
                if(s.charAt(pos) == use) {
                    keep.add(s);
                }
            }
            strings.retainAll(keep);
            keep.clear();
            pos++;
            ones = 0;
            zeroes = 0;
        }
        return strings.get(0);
    }
}