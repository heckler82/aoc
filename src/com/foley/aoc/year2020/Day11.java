package com.foley.aoc.year2020;

import com.foley.aoc.util.Daily;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

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
        b = new Board(input[0].length(), input.length);

        for(int i = 0; i < input.length; i++) {
            for(int j = 0; j < input[i].length(); j++) {
                b.set(j, i, input[i].charAt(j));
            }
        }
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        boolean isRunning = true;

        // Loop until stopping condition is met
        while(isRunning) {
            b = b.integrate();
            //b.print();
            //System.out.println();
            isRunning = b.isMutated();
        }
        System.out.printf("There are %d occupied seats\n", b.count('#'));
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        b = new Board(input[0].length(), input.length);

        for(int i = 0; i < input.length; i++) {
            for(int j = 0; j < input[i].length(); j++) {
                b.set(j, i, input[i].charAt(j));
            }
        }

        b.findVisible();

        boolean isRunning = true;

        // Loop until stopping condition is met
        while(isRunning) {
            b = b.integrate2();
            //b.print();
            //System.out.println();
            isRunning = b.isMutated();
        }
        System.out.printf("There are %d occupied seats\n", b.count('#'));
    }

    private class Board {
        char[][] map;
        boolean mutate;
        int[][] d = {{-1, -1}, {0, -1}, {1, -1}, {-1, 0}, {1, 0}, {-1, 1}, {0, 1}, {1, 1}};
        private Map<Point, Map<Point, Point>> cache;

        public Board(int x, int y) {
            map = new char[y][x];
            mutate = false;
            cache = new HashMap<>();
        }

        public Board(Board b) {
            map = new char[b.map.length][b.map[0].length];
            mutate = false;
            cache = b.cache;
        }

        public Board integrate() {
            Board b = new Board(this);
            for(int i = 0; i < map.length; i++) {
                for(int j = 0; j < map[i].length; j++) {
                    char c = map[i][j];
                    // Apply change rules
                    int neighborCount = checkNeighbors(j, i, '#');
                    switch(c) {
                        case 'L':
                            if(neighborCount == 0) {
                                b.set(j, i, '#');
                                b.mutate = true;
                            } else {
                                b.set(j, i, 'L');
                            }
                            break;
                        case '#':
                            if(neighborCount >= 4) {
                                b.set(j, i, 'L');
                                b.mutate = true;
                            } else {
                                b.set(j, i, '#');
                            }
                            break;
                        case '.':
                            b.set(j, i, '.');
                            continue;
                    }
                }
            }
            return b;
        }

        public Board integrate2() {
            Board b = new Board(this);
            for(int i = 0; i < map.length; i++) {
                for(int j = 0; j < map[i].length; j++) {
                    char c = map[i][j];
                    // Apply change rules
                    int neighborCount = c == '.' ? 0 : checkVisibleNeighbors(j, i, '#');
                    switch(c) {
                        case 'L':
                            if(neighborCount == 0) {
                                b.set(j, i, '#');
                                b.mutate = true;
                            } else {
                                b.set(j, i, 'L');
                            }
                            break;
                        case '#':
                            if(neighborCount >= 5) {
                                b.set(j, i, 'L');
                                b.mutate = true;
                            } else {
                                b.set(j, i, '#');
                            }
                            break;
                        case '.':
                            b.set(j, i, '.');
                            continue;
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

        private int checkNeighbors(int x, int y, char c) {
            int count = 0;
            for(int i = 0; i < d.length; i++) {
                int[] a = d[i];
                if(safeCheck(x + a[0], y + a[1])) {
                    count += map[y + a[1]][x + a[0]] == c ? 1 : 0;
                }
            }
            return count;
        }

        private int checkVisibleNeighbors(int x, int y, char c) {
            int count = 0;
            Point key = new Point(x, y);
            // Doin' it dirty
            for(int[] i : d) {
                Point dir = new Point(i[0], i[1]);
                if(cache.get(key).containsKey(dir)) {
                    Point n = cache.get(key).get(dir);
                    count += map[n.y][n.x] == '#' ? 1 : 0;
                }
            }
            return count;
        }

        private int count(char c) {
            int count = 0;
            for(char[] arr : map) {
                for(char character : arr) {
                    count += character == c ? 1 : 0;
                }
            }
            return count;
        }

        private boolean safeCheck(int x, int y) {
            return (y > -1 && y < map.length && x > -1 && x < map[y].length);
        }

        private void print() {
            for(char[] arr : map) {
                for(char c : arr) {
                    System.out.print(c + " ");
                }
                System.out.println();
            }
        }

        public void findVisible() {
            for(int y = 0; y < map.length; y++) {
                for(int x = 0; x < map[y].length; x++) {
                    if(map[y][x] == '.') continue;
                    for(int[] i : d) {
                        Point p = new Point(i[0], i[1]);
                        findFirstVisible(x, y, p);
                    }
                }
            }
        }

        private void findFirstVisible(int x, int y, Point p) {
            Point point = new Point(x, y);
            if(!cache.containsKey(point)) {
                cache.put(point, new HashMap<>());
            }
            while(safeCheck(x += p.x, y += p.y)) {
                if(map[y][x] != '.') {
                    cache.get(point).put(p, new Point(x, y));
                    return;
                }
            }
        }
    }
}
