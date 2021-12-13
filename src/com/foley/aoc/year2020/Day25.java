package com.foley.aoc.year2020;

import com.foley.aoc.util.Daily;

/**
 * Solutions for day 25. Merry XMAS
 *
 * @author Evan Foley
 * @version 24 Nov 2020
 */
public class Day25 extends Daily {
    private int doorLoop;
    private int cardLoop;
    private int doorKey;
    private int cardKey;
    private final int SUBJ = 7;
    private final int HASH = 20201227;

    /**
     * Creates a new daily
     *
     * @param year The year
     * @param fileName The name of the input file
     */
    public Day25(int year, String fileName) {
        super(year, fileName);
        cardKey = Integer.parseInt(input[0]);
        doorKey = Integer.parseInt(input[1]);
        //cardKey = 5764801;
        //doorKey = 17807724;
        cardLoop = findLoopSize(cardKey);
        doorLoop = findLoopSize(doorKey);
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        long res = transform(cardKey, doorLoop);
        if(res != transform(doorKey, cardLoop)) {
            System.out.printf("The encryption key is not correct\n");
        }
        System.out.printf("Encryption key: %d\n", res);
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        System.out.println("01001101 01100101 01110010 01110010 01111001 00100000 01000011 01101000 01110010 01101001 01110011 01110100 01101101 01100001 01110011");
    }

    private int findLoopSize(int in) {
        int count = 0;
        int val = 1;
        // Transform until final value reached
        while(val != in) {
            val *= SUBJ;
            val %= HASH;
            count++;
        }
        return count;
    }

    private long transform(int key, int loop) {
        long val = 1;
        for(int i = 0; i < loop; i++) {
            val *= key;
            val %= HASH;
        }
        return val;
    }
}
