package com.foley.aoc.year2020;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.Functions;
import com.foley.aoc.util.Tuple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Solutions for day 1
 *
 * @author Evan Foley
 * @version 24 Nov 2020
 */
public class Day1 extends Daily {
    private int[] ints;
    
    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day1(String fileName) {
        super(fileName);
        ints = convertToIntArray(input);
        Arrays.sort(ints);
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        // Search for value in array that will sum to 2020 with the current value
        for(int i : ints) {
            int index = Functions.binarySearch(ints, 2020 - i);
            // If index is non-negative, the answer has been found
            if(index >= 0 && index < ints.length) {
                System.out.printf("2020 found @ <x = %d, y = %d>\n", i, ints[index]);
                System.out.printf("The product of the two is %d\n", i * ints[index]);
                return;
            }
        }
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        List<Tuple<Integer, Integer>> list = new ArrayList<>();
        
        // Naively get all possible pair combinations from the input
        for(int i = 0; i < ints.length - 1; i++) {
            for(int j = i + 1; j < ints.length; j++) {
                list.add(new Tuple<>(ints[i], ints[j]));
            }
        }
        
        // Derivative of part 1
        for(Tuple<> t : list) {
            int index = Functions.binarySearch(ints, 2020 - (t.getFirst() + t.getSecond()));
            // If index is non-negative, the answer has been found
            if(index >= 0 && index < ints.length && ints[index] != t.getFirst() && ints[index] != t.getSecond()) {
                System.out.printf("2020 found @ <x = %d, y = %d, z = %d>\n", t.getFirst(), t.getSecond(), ints[index]);
                System.out.printf("The product of the three is %d\n", t.getFirst() * t.getSecond() * ints[index]);
                return;
            }
        }
    }
}
