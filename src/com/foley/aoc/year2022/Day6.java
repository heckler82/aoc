package com.foley.aoc.year2022;

import com.foley.aoc.util.Daily;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Solutions for day 6
 *
 * @author Evan Foley
 * @version 6 Dec 2022
 */
public class Day6 extends Daily {
    /**
     * Creates a new daily
     *
     * @param year     The year
     * @param fileName The name of the input file
     */
    public Day6(int year, String fileName) {
        super(year, fileName);
    }

    @Override
    protected void task1() {
        System.out.printf("%d characters need to be processed before the first start-of-packet marker is detected\n", lookForMarker(4));
    }

    @Override
    protected void task2() {
        System.out.printf("%d characters need to be processed before the first start-of-message marker is detected\n", lookForMarker(14));
    }

    /**
     * Looks for a sequence of unique characters that is 'size' characters long
     *
     * @param size the size of the sequence to find
     * @return the number of characters that must be processed to find the correct sized sequence
     */
    private int lookForMarker(int size) {
        int numChars = 0;
        Queue<Character> q = new LinkedList<>();
        String stream = input[0];
        for(int i = 0; i < stream.length(); i++) {
            numChars++;
            var c = stream.charAt(i);
            if(!q.contains(c)) {
                q.offer(c);
                if(q.size() == size) {
                    break;
                }
            } else {
                while(!q.isEmpty()) {
                    char polled = q.poll();
                    if(polled == c) {
                        break;
                    }
                }
                q.offer(c);
            }
        }
        return numChars;
    }
}
