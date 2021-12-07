package com.foley.aoc.util.functions;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

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
        try {
            return Arrays.stream(arr)
                    .mapToInt(s -> Integer.parseInt(s))
                    .toArray();
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Could not parse String into an integer",
                                          "Number Format Exception", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        return null;
    }
    
    /**
     * Converts an array of Strings to an array of longs
     *
     * @param arr The input array
     * @return An array of longs filled with the converted strings
     */
    public static long[] convertToLongArray(String[] arr) {
        try {
            return Arrays.stream(arr)
                .mapToLong(s -> Long.parseLong(s))
                .toArray();
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Could not parse String into a long",
                                          "Number Format Exception", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        return null;
    }

    /**
     * Converts a list of Strings to an integer array
     *
     * @param list The input list
     * @return An integer array filled with the converted strings
     */
    public static int[] convertToIntArray(List<String> list) {
        try {
            return list.stream()
                .mapToInt(s -> Integer.parseInt(s))
                .toArray();
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Could not parse String into an integer",
                                          "Number Format Exception", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        return null;
    }

    /**
     * Converts an array of Strings to a list of integers
     *
     * @param arr The input array
     * @return A list filled with the converted strings
     */
    public static List<Integer> convertToIntegerList(String[] arr) {
        try {
            return Arrays.stream(arr)
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Could not parse String into an integer",
                                          "Number Format Exception", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        return null;
    }

    /**
     * Converts a list of Strings to a list of longs
     *
     * @param list The input list
     * @return A list filled with the converted strings
     */
    public static List<Long> convertToLongList(List<String> list) {
        try {
            return list.stream()
                .map(s -> Long.parseLong(s))
                .collect(Collectors.toList());
        } catch(NumberFormatException e) {
            System.out.println("Parsing error");
            System.exit(1);
        }
        return null;
    }

    /**
     * Converts an array of Strings to a list of longs
     *
     * @param arr The input array
     * @return A list filled with the converted strings
     */
    public static List<Long> convertToLongList(String[] arr) {
        try {
            return Arrays.stream(arr)
                .map(s -> Long.parseLong(s))
                .collect(Collectors.toList());
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Could not parse String into a long",
                                          "Number Format Exception", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        return null;
    }
    /**
     * Converts a list of Strings to a list of integers
     *
     * @param list The input list
     * @return A list filled with the converted strings
     */
    public static List<Integer> convertToIntegerList(List<String> list) {
        try {
            return list.stream()
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());
        } catch(NumberFormatException e) {
            System.out.println("Parsing error");
            System.exit(1);
        }
        return null;
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
        OptionalDouble od = Arrays.stream(ints).average();
        return od.orElse(-1.0);
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
        OptionalDouble od = Arrays.stream(longs).average();
        return od.orElse(-1.0);
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

    /**
     * Computes the greatest common divisor of two non-negative integers
     *
     * @param a The first integer
     * @param b The second integer
     * @return The greatest common divisor
     */
    public static int gcd(int a, int b) {
        if(a < 0 || b < 0) {
            throw new IllegalArgumentException("Cannot call gcd with negative numbers");
        }
        while(b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    /**
     * Computes the greatest common divisor of two non-negative longs
     *
     * @param a The first long
     * @param b The second long
     * @return The greatest common divisor
     */
    public static long gcd(long a, long b) {
        if(a < 0 || b < 0) {
            throw new IllegalArgumentException("Cannot call gcd with negative numbers");
        }
        while(b != 0) {
            long r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    /**
     * Computes the least common multiple of two integers
     *
     * @param a The first integer
     * @param b The second integer
     * @return The least common multiple
     */
    public static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }

    /**
     * Computes the least common multiple of two longs
     *
     * @param a The first long
     * @param b The second long
     * @return The least common multiple
     */
    public static long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }

    /**
     * Computers the binomial coefficient of a number
     *
     * @param i the number
     * @return the binomial coefficient of the number
     */
    public static int binomialCoefficient(int i) {
        return i * (i + 1) / 2;
    }

    /**
     * Computes the binomial coefficient of a number
     *
     * @param l the number
     * @return the binomial coefficient of the number
     */
    public static long binomialCoefficient(long l) {
        return l * (l + 1) / 2;
    }

    /**
     * Returns the smallest number x such that x % num[0] = rem[0]...x % num[value] = rem[value]
     * Assumes that values contained in longs are pairwise coprime (gcd for every pair is 1)
     *
     * @param num The array of longs
     * @param rem The expected remainders
     * @param k The length of longs and remainder
     * @return The smallest value that satisfies the chinese remainder theorem
     */
    public static long chineseRemainder(long[] num, long[] rem, long k) {
        int x = 1;

        // CRT guarantees this loop will eventually be broken (if num is pairwise coprime)
        while(true) {
            int j;
            for(j = 0; j < k; j++) {
                if(x % num[j] != rem[j]) {
                    break;
                }
            }
            if(j == k) {
                return x;
            }
            x++;
        }
    }
}
