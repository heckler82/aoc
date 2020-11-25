package com.foley.aoc;

import com.foley.aoc.util.Daily;
import com.foley.aoc.year2020.Day1;

/**
 * Runs the program
 *
 * @author Evan Foley
 * @version 24 Nov 2020
 */
public class Driver {
    /**
     * Main entry point for the program
     *
     * @param args CLI arguments provided to the program
     */
    public static void main(String[] args) {
        Daily d = new Day1("./res/day1.txt");
        d.doTasks();
    }
}
