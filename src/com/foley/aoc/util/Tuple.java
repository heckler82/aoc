package com.foley.aoc.util;

/**
 * Container for holding 2 associated objects
 *
 * @param <K> The type of the first object
 * @param <V> The type of the second object
 * @author Evan Foley
 * @version 25 Nov 2020
 */
public class Tuple<K, V> {
    private K first;
    private V second;

    /**
     * Creates a new tuple
     *
     * @param first The first object
     * @param second The second object
     */
    public Tuple(K first, V second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Gets the first object of the tuple
     *
     * @return The first object
     */
    public K getFirst() {
        return first;
    }

    /**
     * Gets the second object of the tuple
     *
     * @return The second object
     */
    public V getSecond() {
        return second;
    }

    /**
     * Creates a new tuple with the specified objects
     *
     * @param first The first object
     * @param second The second object
     * @param <K> The type of the first object
     * @param <V> The type of the second object
     * @return A tuple with the two objects contained within
     */
    public static <K, V> Tuple<K, V> pair(K first, V second) {
        return new Tuple<>(first, second);
    }
}
