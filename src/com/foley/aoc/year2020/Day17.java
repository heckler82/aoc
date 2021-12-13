package com.foley.aoc.year2020;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.point.Point3D;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Solutions for day 17. Required assistance from the internet
 *
 * @author Evan Foley
 * @version 24 Nov 2020
 */
public class Day17 extends Daily {
    private Set<Integer> active;
    private Set<Integer> active2;

    /**
     * Creates a new daily
     *
     * @param year The year
     * @param fileName The name of the input file
     */
    public Day17(int year, String fileName) {
        super(year, fileName);
        active = new HashSet<>();
        active2 = new HashSet<>();

        // Seed the initial values
        for(int y = 0; y < input.length; y++) {
            String s = input[y];
            for(int x = 0; x < s.length(); x++) {
                if(s.charAt(x) == '#') {
                    active.add(Objects.hash(x, y, 0));
                    active2.add(Objects.hash(x, y, 0, 0));
                }
            }
        }
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        for(int i = 0; i < 6; i++) {
            Set<Integer> nextActive = new HashSet<>();
            for(int x = -15; x < 15; x++) {
                for(int y = -15; y < 15; y++) {
                    for(int z = -8; z < 8; z++) {
                        int num = 0;
                        int hash = Objects.hash(x, y, z);
                        for(int dx = -1; dx < 2; dx++) {
                            for(int dy = -1; dy < 2; dy++) {
                                for(int dz = -1; dz < 2; dz++) {
                                    if(dx != 0 || dy != 0 || dz != 0) {
                                        Point3D.Int n = new Point3D.Int(x + dx, y + dy, z + dz);
                                        if(active.contains(Objects.hash(x + dx, y + dy, z + dz))) {
                                            num += 1;
                                        }
                                    }
                                }
                            }
                        }
                        if(!active.contains(hash) && num == 3) {
                            nextActive.add(hash);
                        } else {
                            if(active.contains(hash) && (num == 2 || num == 3)) {
                                nextActive.add(hash);
                            }
                        }
                    }
                }
            }
            active = nextActive;
        }
        System.out.printf("The final active count is %d\n", active.size());
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        for(int i = 0; i < 6; i++) {
            Set<Integer> nextActive = new HashSet<>();
                for (int x = -15; x < 15; x++) {
                    for (int y = -15; y < 15; y++) {
                        for (int z = -8; z < 8; z++) {
                            for(int w = -8; w < 8; w++) {
                                int num = 0;
                                int hash = Objects.hash(x, y, z, w);
                                for (int dx = -1; dx < 2; dx++) {
                                    for (int dy = -1; dy < 2; dy++) {
                                        for (int dz = -1; dz < 2; dz++) {
                                            for (int dw = -1; dw < 2; dw++) {
                                                if (dx != 0 || dy != 0 || dz != 0 || dw != 0) {
                                                    if (active2.contains(Objects.hash(x + dx, y + dy, z + dz, w + dw))) {
                                                        num += 1;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                if (!active2.contains(hash) && num == 3) {
                                    nextActive.add(hash);
                                } else {
                                    if (active2.contains(hash) && (num == 2 || num == 3)) {
                                        nextActive.add(hash);
                                    }
                                }
                            }
                        }
                    }
                }
                active2 = nextActive;
        }
        System.out.printf("The final active count is %d\n", active2.size());
    }
}
