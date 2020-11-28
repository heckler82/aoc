package com.foley.aoc.util;

import javax.swing.JOptionPane;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
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
