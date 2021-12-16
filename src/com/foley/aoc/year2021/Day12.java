package com.foley.aoc.year2021;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.graph.Graph;
import com.foley.aoc.util.graph.HashGraph;

import java.util.*;

/**
 * Solutions for day 12
 *
 * @author Evan Foley
 * @version 12 Dec 2021
 */
public class Day12 extends Daily {
    private Graph<String> g;

    /**
     * Creates a new daily
     *
     * @param year The year
     * @param fileName The name of the input file
     */
    public Day12(int year, String fileName) {
        super(year, fileName);
        g = new HashGraph<>();
        for(String s : input) {
            var nodes = s.split("-");
            g.addVertex(nodes[0]);
            g.addVertex(nodes[1]);
            g.addEdge(nodes[0], nodes[1], 1.0);
        }
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        System.out.printf("The number of distinct paths is %d\n", findPaths(g, "start", "end", false));
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        System.out.printf("The number of distinct paths is %d\n", findPaths(g, "start", "end", true));
    }

    private int findPaths(Graph<String> g, String start, String end, boolean allowSmallCaveReEntrance) {
        int pathCount = 0;

        Queue<Path> q = new ArrayDeque<>();
        Path path = new Path(start);
        q.offer(path);

        while(!q.isEmpty()) {
            path = q.poll();
            var last = path.get(path.size() - 1);

            if(last.equals(end)) {
                pathCount++;
                continue;
            }

            for(var n : g.getNeighbors(last)) {
                // Reject returning to start
                if("start".equals(n)) {
                    continue;
                }
                // If lower case and exists in the path, bad path
                if(Character.isLowerCase(n.charAt(0))) {
                    if(path.contains(n)) {
                        if(allowSmallCaveReEntrance && path.canEnterSmallCaveAgain()) {
                            Path newPath = new Path(path, n);
                            newPath.enterSmallCave(n);
                            q.offer(newPath);
                            continue;
                        }
                        continue;
                    }
                }
                Path newPath = new Path(path, n);
                q.offer(newPath);
            }
        }
        return pathCount;
    }

    private class Path {
        List<String> nodes;
        String smallCave;

        public Path(String start) {
            nodes = new ArrayList<>();
            nodes.add(start);
            smallCave = null;
        }

        public Path(Path p, String node) {
            nodes = new ArrayList<>(p.nodes);
            nodes.add(node);
            smallCave = p.smallCave;
        }

        public int size() {
            return nodes.size();
        }

        public String get(int i) {
            return nodes.get(i);
        }

        public boolean contains(String node) {
            return nodes.contains(node);
        }

        public boolean canEnterSmallCaveAgain() {
            return smallCave == null;
        }

        public void enterSmallCave(String node) {
            smallCave = node;
        }
    }
}