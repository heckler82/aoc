package com.foley.aoc.util.functions;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides computational functions
 *
 * @author Evan Foley
 * @version 02 Dec 2020
 */
public class Compute {
    /**
     * Converts an array of Strings to an array of integers
     *
     * @param arr The input array
     * @return An array of integers filled with the converted strings
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
     * Converts an array of Strings to an array of longs
     *
     * @param arr The input array
     * @return An array of longs filled with the converted strings
     */
    public static long[] convertToLongArray(String[] arr) {
        long[] longs = new long[arr.length];
        for(int i = 0; i < arr.length; i++) {
            try {
                longs[i] = Long.parseLong(arr[i]);
            }
            catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Could not parse '" + arr[i] + "' into a long",
                        "Number Format Exception", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        }
        return longs;
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
     * Converts an array of Strings to a list of integers
     *
     * @param arr The input array
     * @return A list filled with the converted strings
     */
    public static List<Integer> convertToIntegerList(String[] arr) {
        List<Integer> ints = new ArrayList<>();
        for(String s : arr) {
            ints.add(Integer.parseInt(s));
        }
        return ints;
    }

    /**
     * Converts a list of Strings to a list of longs
     *
     * @param list The input list
     * @return A list filled with the converted strings
     */
    public static List<Long> convertToLongList(List<String> list) {
        List<Long> longs = new ArrayList<>();
        for(String s : list) {
            longs.add(Long.parseLong(s));
        }
        return longs;
    }

    /**
     * Converts an array of Strings to a list of longs
     *
     * @param arr The input array
     * @return A list filled with the converted strings
     */
    public static List<Long> convertToLongList(String[] arr) {
        List<Long> longs = new ArrayList<>();
        for(String s : arr) {
            longs.add(Long.parseLong(s));
        }
        return longs;
    }
    /**
     * Converts a list of Strings to a list of integers
     *
     * @param list The input list
     * @return A list filled with the converted strings
     */
    public static List<Integer> convertToIntegerList(List<String> list) {
        List<Integer> ints = new ArrayList<>();
        for(String s : list) {
            ints.add(Integer.parseInt(s));
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
     * Gets the frequency of a target character in an array of characters
     *
     * @param chars The array of characters
     * @param target The target character to search for
     * @return The number of times the target character appears in the array
     */
    public static int getFrequency(char[] chars, char target) {
        int count = 0;
        for(char c : chars) {
            count += c == target ? 1 : 0;
        }
        return count;
    }

    /**
     * Gets the frequency of a target character in a string
     *
     * @param str The string
     * @param target The target character to search for
     * @return The number of times the target character appears in the String
     */
    public static int getFrequency(String str, char target) {
        int count = 0;
        for(int i = 0; i < str.length(); i++) {
            count += str.charAt(i) == target ? 1 : 0;
        }
        return count;
    }
    
    /**
    * Gets a range of integers from a parent array
    * 
    * @param ints The parent array
    * @param lo The starting index (inclusive)
    * @param hi The ending index (exclusive)
    * @return The sub range of integers
    */
    public static int[] getSortedRange(int[] ints, int lo, int hi) {
        int[] arr = new int[hi - lo];
        for(int i = lo; i < hi; i++) {
            arr[i - lo] = ints[i];
        }
        Arrays.sort(arr);
        return arr;
    }
    
    /**
    * Gets a range of longs from a parent array
    * 
    * @param longs The parent array
    * @param lo The starting index (inclusive)
    * @param hi The ending index (exclusive)
    * @return The sub range of longs
    */
    public static long[] getSortedRange(long[] longs, int lo, int hi) {
        long[] arr = new long[hi - lo];
        for(int i = lo; i < hi; i++) {
            arr[i - lo] = longs[i];
        }
        Arrays.sort(arr);
        return arr;
    }
}
