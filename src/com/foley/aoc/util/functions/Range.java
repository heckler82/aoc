package com.foley.aoc.util.functions;

import java.util.BitSet;
import java.util.Iterator;

/**
 * Abstract for representing a range of numbers. After creation, the minimum and maximum boundaries for the range cannot
 * be adjusted; sub ranges of values within can be excluded or included as needed
 *
 * @author Evan Foley
 * @version 07 Feb 2021
 */
public class Range implements Iterable{
    private BitSet range;
    private int min;
    private int max;

    /**
     * Creates a new range
     *
     * @param min The minimum allowable value of the range (inclusive)
     * @param max The maximum allowable value of the range (inclusive)
     */
    public Range(int min, int max) {
        if(min > max) {
            throw new IllegalArgumentException("The minimum value of a range should not exceed the maximum allowable value");
        }
        range = new BitSet(max - min);
        range.set(0, range.size());
    }

    /**
     * Determines if a value is in the range
     *
     * @param val The value to check
     * @return True if the value is an allowable value within the range
     */
    public boolean inRange(int val) {
        if(val < min || val > max) {
            return false;
        }
        return range.get(val - min);
    }

    /**
     * Gets the number of values that fall within the range
     *
     * @return The number of values that fall within the range
     */
    public int size() {
        return range.cardinality();
    }

    /**
     * Excludes a range of numbers from this range
     *
     * @param min The minimum value (inclusive)
     * @param max The maximum value (inclusive)
     */
    public void exclude(int min, int max) {
        range.clear(Math.max(min, this.min) - min, Math.min(this.max, max) - min);
    }

    /**
     * Excludes a value from this range
     *
     * @param val The value to exclude
     */
    public void exclude(int val) {
        if(inRange(val)) {
            range.clear(val - min);
        }
    }

    /**
     * Includes a range of numbers in this range
     *
     * @param min The minimum value (inclusive)
     * @param max The maximum value (inclusive)
     */
    public void include(int min, int max) {
        range.set(Math.max(min, this.min) - min, Math.min(this.max, max) - min);
    }

    /**
     * Includes a value in the range
     *
     * @param val The value to include
     */
    public void include(int val) {
        if(inRange(val)) {
            range.set(val - min);
        }
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
            this.index = 0;
        }

        @Override
        /**
         * Determines if there are more values to iterate over
         *
         * @return True if there is another element to iterate over
         */
        public boolean hasNext() {
            index = r.range.nextSetBit(index);
            return index != -1;
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
