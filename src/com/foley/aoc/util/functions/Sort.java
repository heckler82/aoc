package com.foley.aoc.util.functions;

import java.util.Arrays;

/**
 * Provides functions for sorting data sets
 *
 * @author Evan Foley
 * @version 02 Dec 2020
 */
public class Sort {
    /**
     * Sorts an array of integers using the merge sort algorithm
     *
     * @param a The array of integers
     */
    public static void mergeSort(int[] a) {
        // Only sort if there is more than one element
        if(a.length > 1) {
            int[] b = Arrays.copyOfRange(a, 0, a.length / 2);
            int[] c = Arrays.copyOfRange(a, a.length / 2, a.length);
            mergeSort(b);
            mergeSort(c);
            merge(a, b, c);
        }
    }

    /**
     * Merges two arrays into a single large array. Assumes that the larger array has enough space to hold all elements
     * of the two smaller arrays.
     *
     * @param a The large array
     * @param b The first small array
     * @param c The second small array
     */
    public static void merge(int[] a, int[] b, int[] c) {
        int p = b.length;
        int q = c.length;

        int i = 0;
        int j = 0;
        int k = 0;

        // Continue until the end of one of the arrays is reached
        while(i < p && j < q) {
            // Take the smaller of the two available values
            if (b[i] < c[j]) {
                a[k] = b[i];
                i++;
            } else {
                a[k] = c[j];
                j++;
            }
            // Increase the main array index
            k++;
        }

        // Determine which array was exhausted
        if(i == p) {
            // copy c[j -> q - 1] to a[k -> p + q - 1]
            while(j < q) {
                a[k] = c[j];
                j++;
                k++;
            }
        } else {
            // copy b[i -> p - 1] to a[k -> p + q - 1]
            while (i < p) {
                a[k] = b[i];
                i++;
                k++;
            }
        }
    }
}
