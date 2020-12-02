package com.foley.aoc.util;

import javax.swing.JOptionPane;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Provides several helper methods for running actions
 *
 * @author Evan Foley
 * @version 24 Nov 2020
 */
public class Functions {
    /**
     * Converts an array of Strings to an integer array
     *
     * @param arr The input array
     * @return An integer array filled with the converted strings
     */
    public static int[] convertToIntArray(String[] arr) {
        int[] ints = new int[arr.length];
        for(int i = 0; i < arr.length; i++) {
            try {
                ints[i] = Integer.parseInt(arr[i]);
            }
            catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Could not parse '" + arr[i] + "' into an integer",
                        "Number Format Exception", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        }
        return ints;
    }

    /**
     * Converts a list of Strings to an integer array
     *
     * @param list The input list
     * @return An integer array filled with the converted strings
     */
    public static int[] convertToIntArray(List<String> list) {
        int[] ints = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            try {
                ints[i] = Integer.parseInt(list.get(i));
            }
            catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Could not parse '" + list.get(i) + "' into an integer",
                        "Number Format Exception", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        }
        return ints;
    }

    /**
     * Finds the mean of an array of integers
     *
     * @param ints The integers
     * @return The mean of the array
     */
    public static double findMean(int[] ints) {
        if(ints == null || ints.length < 1) {
            throw new IllegalArgumentException("Cannot find the mean for a null or empty int array");
        }
        int total = 0;
        for(int i : ints) {
            total += i;
        }
        return (double)total / ints.length;
    }

    /**
     * Finds the mean of an array of longs
     *
     * @param longs The longs
     * @return The mean of the array
     */
    public static double findMean(long[] longs) {
        if(longs == null || longs.length < 1) {
            throw new IllegalArgumentException("Cannot find the mean for a null or empty long array");
        }
        int total = 0;
        for(long l : longs) {
            total += l;
        }
        return (double)total / longs.length;
    }

    /**
     * Finds the median of an array of integers. This will not sort the array, but work with an internal copy of the original array
     *
     * @param ints The integers
     * @return The median of the array
     */
    public static int findMedianValue(int[] ints) {
        int[] copy = Arrays.copyOf(ints, ints.length);
        int index = sortAndFindMedianIndex(copy);
        return copy[index];
    }

    /**
     * Finds the median of an array of longs. This will not sort the array, but work with an internal copy of the original array
     *
     * @param longs The longs
     * @return The median of the array
     */
    public static long findMedianValue(long[] longs) {
        long[] copy = Arrays.copyOf(longs, longs.length);
        int index = sortAndFindMedianIndex(copy);
        return copy[index];
    }

    /**
     * Sorts an array of integers and finds the index of the median
     *
     * @param ints The array of integers
     * @return The index of the median in the sorted array
     */
    public static int sortAndFindMedianIndex(int[] ints) {
        if(ints == null || ints.length == 1) {
            throw new IllegalArgumentException("Cannot find the median for a null or empty int array");
        }
        Arrays.sort(ints);
        return ints.length / 2;
    }

    /**
     * Sorts and array of longs and finds the index of the median
     *
     * @param longs The array of longs
     * @return The index of the median in the sorted array
     */
    public static int sortAndFindMedianIndex(long[] longs) {
        if(longs == null || longs.length == 1) {
            throw new IllegalArgumentException("Cannot find the median for a null or empty long array");
        }
        Arrays.sort(longs);
        return longs.length / 2;
    }

    /**
     * Finds the mode for an array of integers. Ties are broken arbitrarily by the last value in the array to meet the max criteria
     *
     * @param ints The array of integers
     * @return The int that occurs most often in the array
     */
    public static int findMode(int[] ints) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxOccur = -1;
        int max = -1;

        // Add all occurrences of each value in the array
        for(int i : ints) {
            int val = map.getOrDefault(i, 0) + 1;
            if(maxOccur < val) {
                maxOccur = val;
                max = i;
            }
            map.put(i, val);
        }
        return max;
    }

    /**
     * Finds the mode for an array of longs. Ties are broken arbitrarily by the last value in the array to meet the max criteria
     *
     * @param longs The array of longs
     * @return The long that occurs most often in the array
     */
    public static long findMode(long[] longs) {
        Map<Long, Long> map = new HashMap<>();
        long maxOccur = -1;
        long max = -1;

        // Add all occurrences of each value in the array
        for(long l : longs) {
            long val = map.getOrDefault(l, 0L) + 1;
            if(maxOccur < val) {
                maxOccur = val;
                max = l;
            }
            map.put(l, val);
        }
        return max;
    }

    /**
     * Gets the frequency of each value in an array of integers
     *
     * @param ints The array of integers
     * @return The frequency table of each value in the array
     */
    public static Map<Integer, Integer> getFrequencies(int[] ints) {
        Map<Integer, Integer> map = new HashMap<>();

        // Add all occurrences of each value in the array
        for(int i : ints) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        return map;
    }

    /**
     * Gets the frequency of each value in an array of longs
     *
     * @param longs The array of longs
     * @return The frequency table of each value in the array
     */
    public static Map<Long, Integer> getFrequencies(long[] longs) {
        Map<Long, Integer> map = new HashMap<>();

        // Add all occurrences of each value in the array
        for(long l : longs) {
            map.put(l, map.getOrDefault(l, 0) + 1);
        }
        return map;
    }
    
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
    * @return The indexes of the two values that equal target, or -1, -1 if the target value can't be calculated from the array
    */
    public static Tuple<Integer, Integer> twoSum(int[] ints, int target) {
        int lo = 0;
        int hi = ints.length - 1;
        
        // Continue until lo is equal to hi
        while(lo < hi) {
            int sum = ints[lo] + ints[hi];
            // Return a hit
            if(sum == target) {
                return Tuple.pair(lo, hi);
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
    * Finds the three numbers in a sorted array whose sum adds up to the target value. Assumes the array is sorted
    * 
    * @param ints The array of integers
    * @param target The target value
    * @return The indexes of the three values that equal target, or -1, -1, -1 if the target value can't be calculated from the array
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
                    return Triple.triplet(i, j, binarySearch(ints, (currentSum - ints[j])));
                }
                set.add(ints[j]);
            }
        }
        return Triple.triplet(-1, -1, -1);
    }

    /**
     * Dynamically gets a new daily
     *
     * @param inputPath The path to the input file
     * @param className The name of the class to instantiate
     * @return A new daily
     */
    public static Daily getDaily(String inputPath, String className) {
        if(inputPath == null || className == null) {
            throw new IllegalArgumentException("Path to input file or class name is null");
        }
        try {
            Class cl = Class.forName(className);
            Constructor<Daily> con = cl.getConstructor(inputPath.getClass());
            return con.newInstance(inputPath);
        } catch(ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
