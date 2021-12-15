package com.foley.aoc.year2021;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.point.AWTPointComparator;

import java.awt.Point;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Solutions for day 15
 *
 * @author Evan Foley
 * @version 15 Dec 2021
 */
public class Day15 extends Daily {
    private SearchNode[][] smallMap;
    private SearchNode[][] largeMap;
    private int endX;
    private int endY;
    private Point[] dir = {new Point(-1, 0), new Point(0, -1), new Point(1, 0), new Point(0, 1)};

    /**
     * Creates a new daily
     *
     * @param year The year
     * @param fileName The name of the input file
     */
    public Day15(int year, String fileName) {
        super(year, fileName);
        smallMap = new SearchNode[input.length][input[0].length()];
        endX = input[0].length() - 1;
        endY = input.length - 1;

        for(int y = 0; y < input.length; y++) {
            for(int x = 0; x < input[y].length(); x++) {
                smallMap[y][x] = new SearchNode(input[y].charAt(x) - '0', new Point(x, y));
            }
        }

        largeMap = expand(smallMap);
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        System.out.printf("The path with the lowest risk has value %d\n", getShortestPath(smallMap, smallMap.length - 1, smallMap.length - 1));
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        System.out.printf("The path with the lowest risk has value %d\n", getShortestPath(largeMap, largeMap.length - 1, largeMap.length - 1));
    }

    private long getShortestPath(SearchNode[][] map, int endX, int endY) {
        Queue<SearchNode> q = new PriorityQueue<>((SearchNode a, SearchNode b) -> Long.compare(a.totalCost, b.totalCost));
        var current = map[0][0];
        current.totalCost = 0;
        q.offer(current);

        while(!q.isEmpty()) {
            current = q.poll();

            for(var p : dir) {
                var newP = new Point(current.p.x + p.x, current.p.y + p.y);
                if(0 <= newP.x && newP.x < map[0].length && 0 <= newP.y && newP.y < map.length) {
                    var n = map[newP.y][newP.x];
                    long alt = map[current.p.y][current.p.x].totalCost + n.risk;
                    if (alt < n.totalCost) {
                        n.totalCost = alt;
                        n.parent = map[current.p.y][current.p.x];
                        q.offer(n);
                    }
                }
            }
        }
        return map[endY][endX].totalCost;
    }

    private SearchNode[][] expand(SearchNode[][] map) {
        SearchNode[][] newMap = new SearchNode[map.length * 5][map[0].length * 5];

        int yDiff = map.length;
        int xDiff = map.length;

        for(int y = 0; y < newMap.length; y++) {
            for(int x = 0; x < newMap.length; x++) {
                int newRisk;
                if(x >= map.length) {
                    newRisk = newMap[y][x - xDiff].risk + 1;
                } else {
                    if(y >= map.length) {
                        newRisk = newMap[y - yDiff][x].risk + 1;
                    } else {
                        newRisk = map[y][x].risk;
                    }
                }
                if(newRisk > 9) {
                    newRisk = 1;
                }
                newMap[y][x] = new SearchNode(newRisk, new Point(x, y));
            }
        }
        return newMap;
    }

    private class SearchNode {
        long totalCost;
        int risk;
        SearchNode parent;
        Point p;

        public SearchNode(int risk, Point p) {
            totalCost = Integer.MAX_VALUE;
            this.risk = risk;
            parent = null;
            this.p = p;
        }
    }
}