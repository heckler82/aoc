package com.foley.aoc.util;

/**
 * Container for holding 3 associated objects
 *
 * @param <K> The type of the first object
 * @param <V> The type of the second object
 * @param <E> The type of the third object
 * @author Evan Foley
 * @version 25 Nov 2020
 */
public class Triple<K, V, E> {
    private K first;
    private V second;
    private E third;

    /**
     * Creates a new triple
     *
     * @param first The first object
     * @param second The second object
     * @param third The third object
     */
    public Triple(K first, V second, E third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    /**
     * Gets the first object of the triple
     *
     * @return The first object
     */
    public K getFirst() {
        return first;
    }

    /**
     * Gets the second object of the triple
     *
     * @return The second object
     */
    public V getSecond() {
        return second;
    }

    /**
     * Gets the third object of the triple
     *
     * @return The third object
     */
    public E getThird() {
        return third;
    }

    /**
     * Creates a new triple with the specified objects
     *
     * @param first The first object
     * @param second The second object
     * @param third The third object
     * @param <K> The type of the first object
     * @param <V> The type of the second object
     * @param <E> The type of the third object
     * @return A triple with the three object contained within
     */
    public static <K, V, E> Triple<K, V, E> triplet(K first, V second, E third) {
        return new Triple<>(first, second, third);
    }
}
