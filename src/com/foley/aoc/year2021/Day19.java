package com.foley.aoc.year2021;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.Matrix3;
import com.foley.aoc.util.point.Point3D;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Solutions for day 19
 *
 * @author Evan Foley
 * @version 19 Dec 2021
 */
public class Day19 extends Daily {
    private List<Scanner> scanners;
    private List<Scanner> known;
    private List<Matrix3> orientations;

    /**
     * Creates a new daily
     *
     * @param year The year
     * @param fileName The name of the input file
     */
    public Day19(int year, String fileName) {
        super(year, fileName);
        scanners = new ArrayList<>();
        known = new ArrayList<>();
        orientations = createOrientations();

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

        Scanner ref = scanners.get(0);
        scanners.remove(0);
        ref.pos = Point3D.Int.zero();
        known.add(ref);
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        for(Scanner s : scanners) {
            for(Scanner ks : known) {
                if(search(ks, s)) {
                    known.add(s);
                    break;
                }
            }
        }
        System.out.printf("There are %d beacons in the entire map\n", known.get(0).beacons.size());
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        // Find the greatest manhattan distance between point pairs
        double max = Integer.MIN_VALUE;
        /**for(int i = 0; i < scanners.size(); i++) {
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
        }*/
        System.out.printf("The greatest manhattan distance is %d\n", (int)max);
    }

    private boolean search(Scanner ref, Scanner s) {
        for(var rPnt : ref.beacons) {
            for(var sPnt : s.beacons) {
                for(var o : orientations) {

                }
            }
        }
        return false;
    }

    private List<Matrix3> createOrientations() {
        List<Matrix3> list = new ArrayList<>();
        list.add(new Matrix3(new Point3D.Int(1, 0, 0),
                new Point3D.Int(0, 1, 0),
                new Point3D.Int(0, 0, 1)));
        list.add(new Matrix3(new Point3D.Int(1, 0, 0),
                new Point3D.Int(0, 0, 1),
                new Point3D.Int(0, -1, 0)));
        list.add(new Matrix3(new Point3D.Int(1, 0, 0),
                new Point3D.Int(0, -1, 0),
                new Point3D.Int(0, 0, -1)));
        list.add(new Matrix3(new Point3D.Int(1, 0, 0),
                new Point3D.Int(0, 0, -1),
                new Point3D.Int(0, 1, 0)));
        list.add(new Matrix3(new Point3D.Int(0, 1, 0),
                new Point3D.Int(0, 0, 1),
                new Point3D.Int(1, 0, 0)));
        list.add(new Matrix3(new Point3D.Int(0, 1, 0),
                new Point3D.Int(1, 0, 0),
                new Point3D.Int(0, 0, -1)));
        list.add(new Matrix3(new Point3D.Int(0, 1, 0),
                new Point3D.Int(0, 0, -1),
                new Point3D.Int(-1, 0, 0)));
        list.add(new Matrix3(new Point3D.Int(0, 1, 0),
                new Point3D.Int(-1, 0, 0),
                new Point3D.Int(0, 0, 1)));
        list.add(new Matrix3(new Point3D.Int(0, 0, 1),
                new Point3D.Int(1, 0, 0),
                new Point3D.Int(0, 1, 0)));
        list.add(new Matrix3(new Point3D.Int(0, 0, 1),
                new Point3D.Int(0, 1, 0),
                new Point3D.Int(-1, 0, 0)));
        list.add(new Matrix3(new Point3D.Int(0, 0, 1),
                new Point3D.Int(-1, 0, 0),
                new Point3D.Int(0, -1, 0)));
        list.add(new Matrix3(new Point3D.Int(0, 0, 1),
                new Point3D.Int(0, -1, 0),
                new Point3D.Int(1, 0, 0)));
        list.add(new Matrix3(new Point3D.Int(-1, 0, 0),
                new Point3D.Int(0, -1, 0),
                new Point3D.Int(0, 0, 1)));
        list.add(new Matrix3(new Point3D.Int(-1, 0, 0),
                new Point3D.Int(0, 0, 1),
                new Point3D.Int(0, 1, 0)));
        list.add(new Matrix3(new Point3D.Int(-1, 0, 0),
                new Point3D.Int(0, 1, 0),
                new Point3D.Int(0, 0, -1)));
        list.add(new Matrix3(new Point3D.Int(-1, 0, 0),
                new Point3D.Int(0, 0, -1),
                new Point3D.Int(0, -1, 0)));
        list.add(new Matrix3(new Point3D.Int(0, -1, 0),
                new Point3D.Int(0, 0, -1),
                new Point3D.Int(1, 0, 0)));
        list.add(new Matrix3(new Point3D.Int(0, -1, 0),
                new Point3D.Int(1, 0, 0),
                new Point3D.Int(0, 0, 1)));
        list.add(new Matrix3(new Point3D.Int(0, -1, 0),
                new Point3D.Int(0, 0, 1),
                new Point3D.Int(-1, 0, 0)));
        list.add(new Matrix3(new Point3D.Int(0, -1, 0),
                new Point3D.Int(-1, 0, 0),
                new Point3D.Int(0, 0, -1)));
        list.add(new Matrix3(new Point3D.Int(0, 0, -1),
                new Point3D.Int(-1, 0, 0),
                new Point3D.Int(0, 1, 0)));
        list.add(new Matrix3(new Point3D.Int(0, 0, -1),
                new Point3D.Int(0, 1, 0),
                new Point3D.Int(1, 0, 0)));
        list.add(new Matrix3(new Point3D.Int(0, 0, -1),
                new Point3D.Int(1, 0, 0),
                new Point3D.Int(0, -1, 0)));
        list.add(new Matrix3(new Point3D.Int(0, 0, -1),
                new Point3D.Int(0, -1, 0),
                new Point3D.Int(-1, 0, 0)));
        return list;
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

        public String toString() {
            return "scanner " + id;
        }
    }
}