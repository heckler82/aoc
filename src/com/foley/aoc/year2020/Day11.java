package com.foley.aoc.year2020;

import com.foley.aoc.util.Daily;

import java.awt.Point;
import java.util.*;

/**
 * Solutions for day 11
 *
 * @author Evan Foley
 * @version 24 Nov 2020
 */
public class Day11 extends Daily {
    private Board b;

    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day11(String fileName) {
        super(fileName);
        // Parse the map
        b = new Board(input);
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        boolean isRunning = true;
        Board b1 = b.integrate();

        // Loop until stopping condition is met
        while(isRunning) {
            b1 = b1.integrate();
            isRunning = b1.isMutated();
        }
        System.out.printf("There are %d occupied seats\n", b1.count);
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        Board b1 = b.integrate();
        b1.findVisible();

        boolean isRunning = true;

        // Loop until stopping condition is met
        while(isRunning) {
            b1 = b1.integrate2();
            isRunning = b1.isMutated();
        }
        System.out.printf("There are %d occupied seats\n", b1.count);
    }

    private class Board {
        char[][] map;
        boolean mutate;
        int[][] dir = {{-1, -1}, {0, -1}, {1, -1}, {-1, 0}, {1, 0}, {-1, 1}, {0, 1}, {1, 1}};
        Set<Point> consider;
        private Map<Point, List<Point>> cache;
        public int count;

        public Board(String[] input) {
            map = new char[input.length][];
            consider = new HashSet<>();
            cache = new HashMap<>();
            for(int y = 0; y < input.length; y++) {
                map[y] = new char[input[y].length()];
                for(int x = 0; x < input[y].length(); x++) {
                    map[y][x] = input[y].charAt(x);
                    if(input[y].charAt(x) != '.') {
                        Point p = new Point(x, y);
                        consider.add(p);
                        cache.put(p, new ArrayList<>());
                    }
                }
            }
            mutate = false;
            count = 0;
        }

        public Board(Board b) {
            map = new char[b.map.length][b.map[0].length];
            mutate = false;
            cache = b.cache;
            consider = b.consider;
            count = 0;
        }

        public Board integrate() {
            Board b = new Board(this);
            for(Point p : consider) {
                char c = map[p.y][p.x];
                int neighborCount = checkNeighbors(p.x, p.y);
                switch(c) {
                    case 'L':
                        if(neighborCount == 0) {
                            b.set(p.x, p.y, '#');
                            b.count++;
                            b.mutate = true;
                        } else {
                            b.set(p.x, p.y, 'L');
                        }
                        break;
                    case '#':
                        if(neighborCount >= 4) {
                            b.set(p.x, p.y, 'L');
                            b.mutate = true;
                        } else {
                            b.set(p.x, p.y, '#');
                            b.count++;
                        }
                }
            }
            return b;
        }

        public Board integrate2() {
            Board b = new Board(this);
            for(Point p : consider) {
                char c = map[p.y][p.x];
                int neighborCount = checkVisibleNeighbors(p);
                switch(c) {
                    case 'L':
                        if(neighborCount == 0) {
                            b.set(p.x, p.y, '#');
                            b.count++;
                            b.mutate = true;
                        } else {
                            b.set(p.x, p.y, 'L');
                        }
                        break;
                    case '#':
                        if(neighborCount >= 5) {
                            b.set(p.x, p.y, 'L');
                            b.mutate = true;
                        } else {
                            b.set(p.x, p.y, '#');
                            b.count++;
                        }
                }
            }
            return b;
        }

        public boolean isMutated() {
            return mutate;
        }

        public void set(int x, int y, char c) {
            map[y][x] = c;
        }

        private int checkNeighbors(int x, int y) {
            int count = 0;
            for(int[] i : dir) {
                if(safeCheck(x + i[0], y + i[1])) {
                    count += map[y + i[1]][x + i[0]] == '#' ? 1 : 0;
                }
            }
            return count;
        }

        private int checkVisibleNeighbors(Point key) {
            int count = 0;
            // Doin' it dirty
            for(Point p : cache.get(key)) {
                count += map[p.y][p.x] == '#' ? 1 : 0;
                if(count == 5) {
                    return count;
                }
            }
            return count;
        }

        private boolean safeCheck(int x, int y) {
            return (y > -1 && y < map.length && x > -1 && x < map[y].length);
        }

        public void findVisible() {
            for(Point p : consider) {
                for(int[] i : dir) {
                    findFirstVisible(p.x, p.y, new Point(i[0], i[1]));
                }
            }
        }

        private void findFirstVisible(int x, int y, Point p) {
            Point point = new Point(x, y);
            while(safeCheck(x += p.x, y += p.y)) {
                Point p1 = new Point(x, y);
                if(consider.contains(p1)) {
                    cache.get(point).add(p1);
                    return;
                }
            }
        }
    }
}
