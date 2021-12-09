package com.foley.aoc.year2021;

import com.foley.aoc.util.Daily;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Solutions for day 1
 *
 * @author Evan Foley
 * @version 09 Dec 2021
 */
public class Day9 extends Daily {
    private Map<Point, Integer> map;
    private Set<Point> lows;

    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day9(String fileName) {
        super(fileName);
        map = new HashMap<>();
        lows = new HashSet<>();
        int y = 0;
        for(String s : input) {
            int x = 0;
            for(char c : s.toCharArray()) {
                map.put(new Point(x, y), c - '0');
                x++;
            }
            y++;
        }
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        int totalRisk = 0;
        for(var ent : map.entrySet()) {
            int val = ent.getValue();
            // Check N, S, E, W neighbors
            int n = val - map.getOrDefault(new Point(ent.getKey().x, ent.getKey().y - 1), Integer.MAX_VALUE);
            int s = val - map.getOrDefault(new Point(ent.getKey().x, ent.getKey().y + 1), Integer.MAX_VALUE);
            int e = val - map.getOrDefault(new Point(ent.getKey().x + 1, ent.getKey().y), Integer.MAX_VALUE);
            int w = val - map.getOrDefault(new Point(ent.getKey().x - 1, ent.getKey().y), Integer.MAX_VALUE);
            if(n < 0 && s < 0 && e < 0 && w < 0) {
                totalRisk += 1 + val;
                lows.add(ent.getKey());
            }
        }
        System.out.printf("The sum of the risk levels of all low points is %d\n", totalRisk);
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        List<Integer> basins = new ArrayList<>();
        for(Point p : lows) {
            basins.add(performDFS(p));
        }
        basins.sort((Integer a, Integer b) -> b - a);
        System.out.printf("The product of the three largest basins is %d\n", basins.get(0) * basins.get(1) * basins.get(2));
    }

    private int performDFS(Point p) {
        Stack<Point> s = new Stack<>();
        Set<Point> visited = new HashSet<>();
        s.push(p);
        while(!s.isEmpty()) {
            var v = s.pop();
            if(!visited.contains(v) && conditionCheck(v)) {
                visited.add(v);
                s.push(new Point(v.x, v.y - 1));
                s.push(new Point(v.x, v.y + 1));
                s.push(new Point(v.x + 1, v.y));
                s.push(new Point(v.x - 1, v.y));
            }
        }
        return visited.size();
    }

    private boolean conditionCheck(Point p) {
        return map.getOrDefault(p, 9) != 9;
    }
}