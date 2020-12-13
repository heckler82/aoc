package com.foley.aoc.year2020;

import com.foley.aoc.util.Daily;

import java.util.ArrayList;
import java.util.List;

/**
 * Solutions for day 13
 *
 * @author Evan Foley
 * @version 24 Nov 2020
 */
public class Day13 extends Daily {
    private List<Integer> id;
    private List<Integer> rem;
    private int arrival;
    private long prod;

    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day13(String fileName) {
        super(fileName);
        arrival = Integer.parseInt(input[0]);
        id = new ArrayList<>();
        rem = new ArrayList<>();
        String[] split = input[1].split(",");
        prod = 1L;
        for(int i = 0; i < split.length; i++) {
            String s = split[i];
            if(!"x".equals(s)) {
                int val = Integer.parseInt(s);
                id.add(val);
                rem.add(val - i);
                prod *= val;
            }
        }
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        int bestDepart = Integer.MAX_VALUE;
        int busId = -1;
        for(int i : id) {
            int beforeArrive = arrival / i;
            beforeArrive = (beforeArrive * i) + i;
            if(beforeArrive < bestDepart) {
                bestDepart = beforeArrive;
                busId = i;
            }
        }
        System.out.printf("The answer is %d\n", (bestDepart - arrival) * busId);
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        long sum = 0;
        for(int i = 0; i < id.size(); i++) {
            long p = prod / id.get(i);
            sum += rem.get(i) * mulInv(p, id.get(i)) * p;
        }
        System.out.printf("The time that matches the conditions is %d\n", sum % prod);
    }

    private long mulInv(long a, long b) {
        long b1 = b;
        long x0 = 0;
        long x1 = 1;

        if(b == 1) {
            return 1;
        }

        while(a > 1) {
            long q = a / b;
            long amb = a % b;
            a = b;
            b = amb;
            long xqx = x1 - q * x0;
            x1 = x0;
            x0 = xqx;
        }

        if(x1 < 0) {
            x1 += b1;
        }
        return x1;
    }
}
