package com.foley.aoc.util.functions;

import com.foley.aoc.util.Triple;
import com.foley.aoc.util.Tuple;

import java.util.HashSet;

/**
 * Provides functions related to searching for values in data sets
 *
 * @author Evan Foley
 * @version 02 Dec 2020
 */
public class Search {
    /**
     * Searches an array of integers for a target value. Assumes that the array is sorted from least to greatest
     *
     * @param ints The array of integers
     * @param target The target value to search for
     * @return The index of the target value if present, or the negative index of the expected position in the array if the target value were to be inserted
     */
    public static int binarySearch(int[] ints, int target) {
        int mid = -1;
        int lo = 0;
        int hi = ints.length - 1;

        // Continue to search until the serach space is not valid
        while(lo <= hi) {
            // Calculate the test value and account for potential overflow
            mid = (lo + hi) >>> 1;
            // Update search space based on test value
            if(ints[mid] > target) {
                hi = mid - 1;
            } else {
                if(ints[mid] < target) {
                    lo = mid + 1;
                } else {
                    return mid;
                }
            }
        }
        // Target not found in the array, return negative expected insertion index
        return -lo;
    }

    /**
     * Searches an array of longs for a target value. Assumes that the array is sorted from least to greatest
     *
     * @param longs The array of longs
     * @param target The target value to search for
     * @return The index of the target value if present, or the negative index of the expected position in the array if the target value were to be inserted
     */
    public static int binarySearch(long[] longs, long target) {
        int mid = -1;
        int lo = 0;
        int hi = longs.length - 1;

        // Continue to search until the search space is not valid
        while(lo <= hi) {
            // Calculate the test value and account for potential overflow
            mid = (lo + hi) >>> 1;
            // Update search space based on test value
            if(longs[mid] > target) {
                hi = mid - 1;
            } else {
                if(longs[mid] < target) {
                    lo = mid + 1;
                } else {
                    return mid;
                }
            }
        }
        // Target not found in the array, return negative expected insertion index
        return -lo;
    }

    /**
     * Finds the two numbers in a sorted array whose sum adds up to the target value. Assumes the array is sorted
     *
     * @param ints The array of integers
     * @param target The target value
     * @return The two values that equal target, or -1, -1 if the target value can't be calculated from the array
     */
    public static Tuple<Integer, Integer> twoSum(int[] ints, int target) {
        int lo = 0;
        int hi = ints.length - 1;

        // Continue until lo is equal to hi
        while(lo < hi) {
            int sum = ints[lo] + ints[hi];
            // Return a hit
            if(sum == target) {
                return Tuple.pair(ints[lo], ints[hi]);
            }

            // Remove whichever end of the array will not produce the target value
            if(sum > target) {
                hi--;
            } else {
                lo++;
            }
        }

        // No result found
        return Tuple.pair(-1, -1);
    }
    
    /**
     * Finds the two numbers in a sorted array whose sum adds up to the target value. Assumes the array is sorted
     *
     * @param longs The array of longs
     * @param target The target value
     * @return The two values that equal target, or -1, -1 if the target value can't be calculated from the array
     */
    public static Tuple<Long, Long> twoSum(long[] longs, long target) {
        int lo = 0;
        int hi = longs.length - 1;

        // Continue until lo is equal to hi
        while(lo < hi) {
            long sum = longs[lo] + longs[hi];
            // Return a hit
            if(sum == target) {
                return Tuple.pair(longs[lo], longs[hi]);
            }

            // Remove whichever end of the array will not produce the target value
            if(sum > target) {
                hi--;
            } else {
                lo++;
            }
        }

        // No result found
        return Tuple.pair(-1L, -1L);
    }

    /**
     * Finds the three numbers in a sorted array whose sum adds up to the target value. Assumes the array is sorted
     *
     * @param ints The array of integers
     * @param target The target value
     * @return The three values that equal target, or -1, -1, -1 if the target value can't be calculated from the array
     */
    public static Triple<Integer, Integer, Integer> threeSum(int[] ints, int target) {
        // Utilize the first element as the starting point
        for(int i = 0; i < ints.length - 2; i++) {
            // Maintain unique sums
            HashSet<Integer> set = new HashSet<>();

            int currentSum = target - ints[i];
            // Inner loop for individual values
            for(int j = i + 1; j < ints.length; j++) {
                // If the current sum is in the set, the answer is found
                if(set.contains(currentSum - ints[j])) {
                    return Triple.triplet(ints[i], ints[j], currentSum - ints[j]);
                }
                set.add(ints[j]);
            }
        }
        return Triple.triplet(-1, -1, -1);
    }
}
