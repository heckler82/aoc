package com.foley.aoc.year2021;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.point.Point3L;

/**
 * Solutions for day 1
 *
 * @author Evan Foley
 * @version 02 Dec 2021
 */
public class Day2 extends Daily {
    private Point3L pos;

    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day2(String fileName) {
        super(fileName);
        pos = new Point3L(0, 0, 0);
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        for(String s : input) {
            String[] instr = s.split("\\s+");
            int val = Integer.parseInt(instr[1]);
            switch(instr[0]) {
                case "forward":
                    pos.x += val;
                    break;
                case "down":
                    pos.y += val;
                    break;
                case "up":
                    pos.y -= val;
            }
        }
        System.out.printf("The final position is %d\n", pos.x * pos.y);
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        pos = Point3L.zero();
        for(String s : input) {
            String[] instr = s.split("\\s+");
            int val = Integer.parseInt(instr[1]);
            switch(instr[0]) {
                case "forward":
                    pos.x += val;
                    pos.y += val * pos.z;
                    break;
                case "down":
                    pos.z += val;
                    break;
                case "up":
                    pos.z -= val;
            }
        }
        System.out.printf("The final position is %d\n", pos.x * pos.y);
    }
}