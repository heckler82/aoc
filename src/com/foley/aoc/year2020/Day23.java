package com.foley.aoc.year2020;

import com.foley.aoc.util.Daily;

import java.util.LinkedList;
import java.util.List;

/**
 * Solutions for day 23
 *
 * @author Evan Foley
 * @version 24 Nov 2020
 */
public class Day23 extends Daily {
    private int maxCup;
    private int[] index;
    private int[] value;
    private int currentCupIndex;

    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day23(String fileName) {
        super(fileName);
        index = new int[10];
        value = new int[10];

        // Fill in the starting input
        for(int i = 0; i < input[0].length(); i++) {
            int curr = input[0].charAt(i) - '0';
            index[i + 1] = curr;
            value[curr] = i + 1;
        }
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        currentCupIndex = 0;
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        // Re-initialize the input
        index = new int[10000001];
        for(int i = 0; i < input[0].length(); i++) {
            int curr = input[0].charAt(i) - '0';
            index[i + 1] = curr;
            value[curr] = i + 1;
        }

        // Fill in the rest of the array
        for(int i = 10; i <= 10000000; i++) {
            index[i] = i;
            value[i] = i;
        }

        currentCupIndex = 0;
    }

    private void takeTurn(int turn) {
        //System.out.printf("-- move %d --\n", turn);
        //System.out.printf("cups: %s\n", cups.toString());
        //System.out.printf("current: %d\n", cups.get(currentCupIndex));
        //List<Integer> temp = List.of(index[(currentCupIndex + 1) % index.length], index[(currentCupIndex + 2) % index.length], index[(currentCupIndex + 3) % index.length]);
        //System.out.printf("pickup: %s\n", temp.toString());
        //int dest = currentCupValue - 1 == 0 ? maxCup : currentCupValue - 1;
        //while(temp.contains(dest)) {
        //    dest--;
        //    if(dest < 1) {
        //        dest += maxCup;
        //    }
        //}
        //System.out.printf("destination: %d\n\n", dest);
        //int destIndex = cups.indexOf(dest) + 1;
        //for(int i : temp) {
        //    cups.add(destIndex, i);
        //    destIndex++;
        //}
        //currentCupIndex = (cups.indexOf(currentCupValue) + 1) % cups.size();
        //currentCupValue = cups.get(currentCupIndex);
    }
}
