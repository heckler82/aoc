package com.foley.aoc.year2021;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.point.Point3D;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Solutions for day 22
 *
 * @author Evan Foley
 * @version 22 Dec 2021
 */
public class Day22 extends Daily {
    private Set<Point3D> on;
    private long onL;

    /**
     * Creates a new daily
     *
     * @param year The year
     * @param fileName The name of the input file
     */
    public Day22(int year, String fileName) {
        super(year, fileName);
        on = new HashSet<>();
        onL = 0L;
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        for(var s : input) {
            boolean state = s.startsWith("on") ? true : false;
            var axes = s.split(",");
            var xAxis = axes[0].substring(axes[0].indexOf("=") + 1).split("\\.\\.");
            var yAxis = axes[1].substring(axes[1].indexOf("=") + 1).split("\\.\\.");
            var zAxis = axes[2].substring(axes[2].indexOf("=") + 1).split("\\.\\.");
            int x1 = Integer.parseInt(xAxis[0]);
            int x2 = Integer.parseInt(xAxis[1]);
            int y1 = Integer.parseInt(yAxis[0]);
            int y2 = Integer.parseInt(yAxis[1]);
            int z1 = Integer.parseInt(zAxis[0]);
            int z2 = Integer.parseInt(zAxis[1]);
            for(int z = z1; z <= z2; z++) {
                for(int y = y1; y <= y2; y++) {
                    for(int x = x1; x <= x2; x++) {
                        if(state) {
                            on.add(new Point3D.Int(x, y, z));
                        } else {
                            on.remove(new Point3D.Int(x, y, z));
                        }
                    }
                }
            }
        }
        System.out.printf("There are %d cubes turned on\n", on.size());
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        System.out.printf("After the initialization process, there are %d cubes turned on\n", onL);
    }
}