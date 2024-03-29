package com.foley.aoc.year2020;

import com.foley.aoc.util.Daily;

import java.awt.Point;

/**
 * Solutions for day 12
 *
 * @author Evan Foley
 * @version 24 Nov 2020
 */
public class Day12 extends Daily {
    enum Direction {
        WEST,
        NORTH,
        EAST,
        SOUTH;
    }
    private Point p2;

    /**
     * Creates a new daily
     *
     * @param year The year
     * @param fileName The name of the input file
     */
    public Day12(int year, String fileName) {
        super(year, fileName);
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        Point p = new Point(0, 0);
        p2 = new Point(0, 0);
        Point way = new Point(10, 1);
        Direction dir = Direction.EAST;
        Direction[] dirs = Direction.values();

        for(String s : input) {
            int dist = Integer.parseInt(s.substring(1));
            int diff;
            switch(s.charAt(0)) {
                case 'F':
                    // Update part 2 ship location from waypoint
                    p2.x += way.x * dist;
                    p2.y += way.y * dist;

                    // Determine which direction to move, and move the ship in that direction
                    if(dir.ordinal() % 2 == 0) { // 0 and 2 are WEST and EAST, 1 and 3 are NORTH and SOUTH
                        p.x += dir.ordinal() == 2 ? dist : -dist;
                    } else {
                        p.y += dir.ordinal() == 1 ? dist : -dist;
                    }
                    break;
                case 'N':
                    p.y += dist;
                    way.y += dist;
                    break;
                case 'S':
                    p.y -= dist;
                    way.y -= dist;
                    break;
                case 'E':
                    p.x += dist;
                    way.x += dist;
                    break;
                case 'W':
                    p.x -= dist;
                    way.x -= dist;
                    break;
                case 'L':
                    // Determine how many rotations were done, then clamp the value into the range of valid directions
                    diff = dir.ordinal() - (dist / 90);
                    if(diff < 0) {
                        diff += dirs.length;
                    }
                    dir = dirs[diff];

                    // Rotate the waypoint around the ship
                    for(int i = 0; i < (dist / 90); i++) {
                        int temp = way.x;
                        way.x = -way.y;
                        way.y = temp;
                    }
                    break;
                case 'R':
                    // Determine how many rotations were done, then clamp the value into the range of valid directions
                    diff = dir.ordinal() + (dist / 90);
                    if(diff >= dirs.length) {
                        diff -= dirs.length;
                    }
                    dir = dirs[diff];

                    // Rotate the waypoint around the ship
                    for(int i = 0; i < (dist / 90); i++) {
                        int temp = way.y;
                        way.y = -way.x;
                        way.x = temp;
                    }
            }
        }
        System.out.printf("The distance is %d\n", Math.abs(p.x) + Math.abs(p.y));
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        System.out.printf("The distance is %d\n", Math.abs(p2.x) + Math.abs(p2.y));
    }
}
