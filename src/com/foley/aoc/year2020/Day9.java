package com.foley.aoc.year2020;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.functions.Compute;
import com.foley.aoc.util.functions.Search;

/**
 * Solutions for day 9
 *
 * @author Evan Foley
 * @version 24 Nov 2020
 */
public class Day9 extends Daily {
    priavte long[] stream;
    private long val = 0L;
    
    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day9(String fileName) {
        super(fileName);
        stream = Compute.convertToLongArray(input);
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        int lo = 0;
        int hi = 25;
        long[] preamble = getPreamble(stream, lo, hi);
        for(int i = lo + 25; i < stream.length; i++) {
            Tuple<Long, Long> t = Search.twoSum(preamble, stream[i]);
            if(t.getFirst() == -1) {
                val = stream[i];
                System.out.printf("The bad value is %d\n", val);
                break;
            }
            preamble = getPreamble(stream, ++lo, ++hi);
        }
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        System.out.printf("The weakness is %d\n", getWeakness(stream, val));
    }
    
    /**
    * Gets the preamble from an input array
    * 
    * @param longs The input array
    * @param lo The starting index (inclusive)
    * @param hi The ending index (exclusive)
    */
    private long[] getPreamble(long[] longs, int lo, int hi) {
        long[] pr = new long[hi - lo];
        for(int i = lo; i < hi; i++) {
            pr[i - lo] = longs[i];
        }
        Arrays.sort(pr);
        return pr;
    }
    
    /**
    * Finds the value of the weakness
    * 
    * @param longs The array of longs
    * @param target The target value
    * @return The sum of the minimum and maximum value that add up to target
    */
    private long getWeakness(long[] longs, long target) {
        int lo = 0;
        long sum = 0L;
        for(int i = 0; i < longs.length; i++) {
            sum += longs[i];
            while(sum > target) {
                sum -= longs[lo++];
            }
            if(sum == target) {
                long[] range = getSortedRange(longs, lo, i);
                long min = range[0];
                long max = range[range.length - 1];
                return min + max;
            }
        }
        return -1;
    }
    
    /**
    * Gets a range of longs from a parent array
    * 
    * @param longs The parent array
    * @param lo The starting index (inclusive)
    * @param hi The ending index (exclusive)
    * @return The sub range of longs
    */
    private long[] getSortedRange(long[] longs, int lo, int hi) {
        long[] l = new long[hi - lo];
        for(int i = lo; i < hi; i++) {
            l[i - lo] = longs[i];
        }
        Array.sort(l);
        return l;
    }
}
