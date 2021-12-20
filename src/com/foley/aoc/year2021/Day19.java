package com.foley.aoc.year2021;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.point.Point3D;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Solutions for day 19
 *
 * @author Evan Foley
 * @version 19 Dec 2021
 */
public class Day19 extends Daily {
    private List<Scanner> scanners;
    private List<Point3D> orientations;

    /**
     * Creates a new daily
     *
     * @param year The year
     * @param fileName The name of the input file
     */
    public Day19(int year, String fileName) {
        super(year, fileName);
        scanners = new ArrayList<>();
        orientations = new ArrayList<>();

        // Add all orientation combinations to list (should be 24)


        Scanner currentScanner = null;
        int id = 0;
        for(String s : input) {
            if("".equals(s)) {
                continue;
            }
            if(s.startsWith("---")) {
                currentScanner = new Scanner(id++);
                scanners.add(currentScanner);
            } else {
                var coords = s.split(",");
                currentScanner.addBeacon(Integer.parseInt(coords[0]),
                        Integer.parseInt(coords[1]),
                        Integer.parseInt(coords[2]));
            }
        }
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        scanners.stream().forEach(s -> System.out.printf("S%d can see %d beacons\n", s.id, s.beacons.size()));
        Scanner reference = scanners.get(0);
        reference.pos = Point3D.Int.zero();

        for(var o : orientations) {
            for(Point3D p : reference.beacons) {
                for(Scanner s : scanners) {
                    if(s != reference) {
                        for(Point3D p2 : s.beacons) {
                            // re-orient p2 here
                            // get the distance between the points (p - p2) = d
                            // translate all points in this scanner
                            // if there are 12 matches then add all translated points to reference beacons, translate scanner position by d?
                        }
                    }
                }
            }
        }
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        // Find the greatest manhattan distance between point pairs
        double max = Integer.MIN_VALUE;
        for(int i = 0; i < scanners.size(); i++) {
            var s = scanners.get(i);
            for(int j = i + 1; j < scanners.size(); j++) {
                var s2 = scanners.get(j);
                if(s != s2) {
                    double md = Point3D.manhattanDistance(s.pos, s2.pos);
                    if(md > max) {
                        max = md;
                    }
                }
            }
        }
        System.out.printf("The greatest manhattan distance is %d\n", (int)max);
    }

    private Point3D roll(Point3D p) {
        return p.make(p.getX(), p.getZ(), -p.getY());
    }

    private Point3D turn(Point3D p) {
        return p.make(-p.getY(), p.getX(), p.getZ());
    }

    private class Scanner {
        int id;
        Point3D pos;
        Set<Point3D> beacons;

        public Scanner(int id) {
            this.id = id;
            beacons = new HashSet<>();
        }

        public void addBeacon(int x, int y, int z) {
            beacons.add(new Point3D.Int(x, y, z));
        }
    }
}