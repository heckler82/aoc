package com.foley.aoc.year2021;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.point.AWTPointComparator;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Solutions for day 20
 *
 * @author Evan Foley
 * @version 20 Dec 2021
 */
public class Day20 extends Daily {
    private Map<Point, Character> map;
    private String algorithm;
    /**
     * Creates a new daily
     *
     * @param year The year
     * @param fileName The name of the input file
     */
    public Day20(int year, String fileName) {
        super(year, fileName);
        map = new HashMap<>();
        algorithm = input[0];
        for(int i = 2; i < input.length; i++) {
            int y = i - 2;
            String s = input[i];
            for(int x = 0; x < s.length(); x++) {
                map.put(new Point(x, y), s.charAt(x));
            }
        }
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        clickyClackyEnhance(2);

        long lit = map.values().stream().filter(i -> i == '#').count();
        System.out.printf("There are %d pixels lit after 2 enhancements\n", lit);
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        clickyClackyEnhance(48);

        long lit = map.values().stream().filter(i -> i == '#').count();
        System.out.printf("There are %d pixels lit after 2 enhancements\n", lit);
    }

    private void clickyClackyEnhance(int steps) {
        for(int t = 1; t <= steps; t++) {
            var newMap = new HashMap<Point, Character>();
            Point min = map.keySet().stream().min(new AWTPointComparator()).get();
            Point max = map.keySet().stream().max(new AWTPointComparator()).get();

            for(int y = min.y - 1; y <= max.y + 1; y++) {
                for(int x = min.x - 1; x <= max.x + 1; x++) {
                    Point p = new Point(x, y);
                    StringBuilder sb = new StringBuilder();
                    for(int py = y - 1; py <= y + 1; py++) {
                        for(int px = x - 1; px <= x + 1; px++) {
                            char val = map.getOrDefault(new Point(px, py), (t % 2) == 1 ? '.' : '#');
                            sb.append(val == '.' ? 0 : 1);
                        }
                    }
                    char newVal = algorithm.charAt(Integer.parseInt(sb.toString(), 2));
                    newMap.put(p, newVal);
                }
            }
            map = newMap;
        }
    }
}