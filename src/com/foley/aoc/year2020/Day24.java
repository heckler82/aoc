package com.foley.aoc.year2020;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.functions.Regex;
import com.foley.aoc.util.gfx.SimpleImageDisplay;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;

/**
 * Solutions for day 24
 *
 * @author Evan Foley
 * @version 24 Nov 2020
 */
public class Day24 extends Daily {
    private final String DIRECTION_PATTERN = "([n|s]?[e|w])";
    private final double SIZE = 1.0;
    private Set<Hex> active;

    int minx = Integer.MAX_VALUE;
    int maxx = Integer.MIN_VALUE;
    int miny = Integer.MAX_VALUE;
    int maxy = Integer.MIN_VALUE;

    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day24(String fileName) {
        super(fileName);
        active = new HashSet<>();
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        for(String s : input) {
            var h = new Hex(0, 0, 0);
            Matcher m = Regex.getMatcher(DIRECTION_PATTERN, s);
            while(m.find()) {
                switch(m.group(1)) {
                    case "e":
                        h.walk(1, -1, 0);
                        break;
                    case "w":
                        h.walk(-1, 1, 0);
                        break;
                    case "ne":
                        h.walk(1, 0, -1);
                        break;
                    case "nw":
                        h.walk(0, 1, -1);
                        break;
                    case "se":
                        h.walk(0, -1, 1);
                        break;
                    case "sw":
                        h.walk(-1, 0, 1);
                }
            }

            // Add or remove from active status
            if(active.contains(h)) {
                active.remove(h);
            } else {
                active.add(h);
            }
        }
        System.out.printf("There are %d active hexagons\n", active.size());
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        for(int i = 0; i < 100; i++) {
            var consider = new HashMap<Hex, Integer>();
            var next = new HashSet<Hex>();

            for(var h : active) {
                // Get the neighbor count
                int count = 0;
                for(var n : h.neighbors()) {
                    if(active.contains(n)) {
                        count += 1;
                    } else {
                        consider.put(n, consider.getOrDefault(n, 0) + 1);
                    }
                }

                // If there are only 1 or two neighbors, add it to the next generation
                if(count == 1 || count == 2) {
                    next.add(h);
                }
            }

            // Go through potentials
            for(var entry : consider.entrySet()) {
                if(entry.getValue() == 2) {
                    next.add(entry.getKey());
                }
            }

            active = next;
        }
        System.out.printf("There are %d active tiles after 100 days\n", active.size());

        // Image stuff
        //displayImage();
    }

    private void displayImage() {
        for(var h : active) {
            if(h.x > maxx) {
                maxx = h.x;
            }
            if(h.x < minx) {
                minx = h.x;
            }
            if(h.y > maxy) {
                maxy = h.y;
            }
            if(h.y < miny) {
                miny = h.y;
            }
        }
        BufferedImage img = new BufferedImage((maxx - minx) * 5, (maxy - miny) * 5, BufferedImage.TYPE_INT_RGB);
        Graphics g = img.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, img.getWidth(), img.getHeight());
        g.setColor(Color.BLACK);
        for(var h : active) {
            g.fillRect((h.x - minx) * 5, (h.y - miny) * 5, 5, 5);
        }
        SimpleImageDisplay.show(img);
    }

    private class Hex {
        public int x;
        public int y;
        public int z;

        public Hex(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public void walk(int x, int y, int z) {
            this.x += x;
            this.y += y;
            this.z += z;
        }

        public List<Hex> neighbors() {
            var n = new ArrayList<Hex>();
            n.add(new Hex(x + 1, y - 1, z));
            n.add(new Hex(x + 1, y, z - 1));
            n.add(new Hex(x - 1, y + 1, z));
            n.add(new Hex(x - 1, y, z + 1));
            n.add(new Hex(x, y + 1, z - 1));
            n.add(new Hex(x, y - 1, z + 1));
            return n;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Hex hex = (Hex) o;
            return x == hex.x && y == hex.y && z == hex.z;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, z);
        }
    }
}
