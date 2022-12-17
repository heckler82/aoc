package com.foley.aoc.util.functions;

import java.util.Iterator;

/**
 * Abstract for representing a linear range of numbers.
 *
 * @author Evan Foley
 * @version 25 Nov 2022
 */
public class Range implements Iterable<Integer>{
    private int min;
    private int max;

    /**
     * Creates a new range
     *
     * @param min The minimum allowable value of the range (inclusive)
     * @param max The maximum allowable value of the range (exclusive)
     */
    public Range(int min, int max) {
        if(min > max) {
            throw new IllegalArgumentException("The minimum value of a range should not exceed the maximum allowable value");
        }
        this.min = min;
        this.max = max;
    }

    /**
     * Returns true if this range fully contains the specified range
     *
     * @param r the range to test
     * @return true if this range fully contains the specified range
     */
    public boolean fullyInRange(Range r) {
        return min <= r.min && max >= r.max;
    }

    /**
     * Determines if a value is in the range
     *
     * @param val The value to check
     * @return True if the value is an allowable value within the range
     */
    public boolean inRange(int val) {
        return val >= min && val < max;
    }

    /**
     * Returns true if any part of the specified range is in this range
     *
     * @param r the specified range
     * @return true if any part of the specified range is in this range
     */
    public boolean inRange(Range r) {
        for(int i : r) {
            if(inRange(i)) {
                return true;
            }
        }
        return false;
    }

    @Override
    /**
     * Returns an iterator over a range
     *
     * @return an iterator
     */
    public Iterator iterator() {
        return new RangeIterator(this);
    }

    /**
     * An iterator that iterates over the allowable values of a range
     */
    private class RangeIterator implements Iterator {
        private Range r;
        private int index;

        /**
         * Creates a new range iterator
         *
         * @param r The range to iterate over
         */
        public RangeIterator(Range r) {
            this.r = r;
            this.index = r.min - 1;
        }

        @Override
        /**
         * Determines if there are more values to iterate over
         *
         * @return True if there is another element to iterate over
         */
        public boolean hasNext() {
            index++;
            return r.inRange(index);
        }

        @Override
        /**
         * Gets the next object
         *
         * @return The next object
         */
        public Object next() {
            return index;
        }
    }
}
