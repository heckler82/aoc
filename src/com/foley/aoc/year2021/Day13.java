package com.foley.aoc.year2021;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.functions.Regex;
import com.foley.aoc.util.gfx.ImageUtil;
import com.foley.aoc.util.gfx.SimpleImageDisplay;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

/**
 * Solutions for day 13
 *
 * @author Evan Foley
 * @version 13 Dec 2021
 */
public class Day13 extends Daily {
    private Set<Point> map;
    private List<Point> folds;

    /**
     * Creates a new daily
     *
     * @param year The year
     * @param fileName The name of the input file
     */
    public Day13(int year, String fileName) {
        super(year, fileName);
        map = new HashSet<>();
        folds = new ArrayList<>();
        // Get coords
        for(int i = 0; i < input.length; i++) {
            if(Regex.canMatchPattern("\\d+,\\d+", input[i])) {
                var coords = input[i].split(",");
                map.add(new Point(Integer.parseInt(coords[0]), Integer.parseInt(coords[1])));
            } else {
                if("".equals(input[i])) {
                    continue;
                } else {
                    var split = input[i].split("=");
                    if(split[0].charAt(split[0].length() - 1) == 'y') {
                        folds.add(new Point(-1, Integer.parseInt(split[1])));
                    } else {
                        folds.add(new Point(Integer.parseInt(split[1]), -1));
                    }
                }
            }
        }
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        System.out.printf("The number of visible points after the first fold is %d\n", fold(List.of(folds.get(0))));
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        folds.remove(0);
        fold(folds);
        new Thread(() -> {
            Image img = ImageUtil.createImage(map, Color.BLACK, Color.WHITE, 20, 20, 1);
            ImageUtil.saveImage(img, year + "/day13.png");
            SimpleImageDisplay.show(img,  "Activation Code");
        }).start();
    }

    private int fold(List<Point> folds) {
        for(Point p : folds) {
            var toAdd = new HashSet<Point>();
            for(var itr = map.iterator(); itr.hasNext();) {
                Point ex = itr.next();
                int diff;
                if(p.x > -1) {
                    diff = p.x - Math.abs(ex.x - p.x);
                    Point newPoint = new Point(diff, ex.y);
                    toAdd.add(newPoint);
                } else {
                    diff = p.y - Math.abs(ex.y - p.y);
                    Point newPoint = new Point(ex.x, diff);
                    toAdd.add(newPoint);
                }
                itr.remove();
            }
            map = toAdd;
        }
        return map.size();
    }
}