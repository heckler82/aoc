package com.foley.aoc.year2021;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.functions.Compute;

import java.awt.Point;
import java.awt.Rectangle;

/**
 * Solutions for day 17
 *
 * @author Evan Foley
 * @version 17 Dec 2021
 */
public class Day17 extends Daily {
    private Point upperLeft;
    private Point lowerRight;

    /**
     * Creates a new daily
     *
     * @param year The year
     * @param fileName The name of the input file
     */
    public Day17(int year, String fileName) {
        super(year, fileName);
        var targetParam = input[0].split(":\\s+")[1].split(",\\s+");
        var targetX = targetParam[0].split("\\.\\.");
        var targetY = targetParam[1].split("\\.\\.");
        int x1 = Integer.parseInt(targetX[0].substring(2));
        int x2 = Integer.parseInt(targetX[1]);
        int minX = Math.min(x1, x2);
        int maxX = Math.max(x1, x2);
        int y1 = Integer.parseInt(targetY[0].substring(2));
        int y2 = Integer.parseInt(targetY[1]);
        int minY = Math.min(y1, y2);
        int maxY = Math.max(y1, y2);
        upperLeft = new Point(minX, maxY);
        lowerRight = new Point(maxX, minY);
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        int upperBound = Math.abs(lowerRight.y);
        int lowerBound = upperBound - Math.abs(upperLeft.y);
        int maxHeight = 0;
        for(int i = 0; i < Integer.MAX_VALUE; i++) {
            if(i + 1 <= upperBound) {
                if(i + 1 >= lowerBound) {
                    maxHeight = Compute.triangleNumber(i);
                }
            } else {
                break;
            }
        }
        System.out.printf("The highest the projectile can go is %d\n", maxHeight);
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        int minYVel = lowerRight.y;
        int maxYVel = Math.abs(lowerRight.y) - 1;
        int maxXVel = lowerRight.x;
        int minXVel = getLowestPossibleXVel(upperLeft.x);

        Rectangle targetArea = new Rectangle(upperLeft.x, upperLeft.y, (lowerRight.x - upperLeft.x), Math.abs(lowerRight.y - upperLeft.y));

        int count = 0;
        int lineLimit = 9;
        for(int x = minXVel; x <= maxXVel; x++) {
            for(int y = minYVel; y <= maxYVel; y++) {
                Point p = new Point(x, y);
                if(canHitTargetArea(targetArea, new Point(0, 0), p)) {
                    count++;
                }
            }
        }
        System.out.printf("The number of velocities that will hit the target is %d\n", count);
    }

    private int getLowestPossibleXVel(int limit) {
        for(int i = 0; i < limit; i++) {
            int tri = Compute.triangleNumber(i);
            if(tri >= limit) {
                return i;
            }
        }
        return -1;
    }

    private boolean canHitTargetArea(Rectangle r, Point p, Point v) {
        while(true) {
            p.x += v.x;
            p.y += v.y;
            if(customContains(r, p)) {
                return true;
            }
            if(p.x > r.x + r.width || p.y < r.y - r.height) {
                break;
            }
            if(v.x > 0) {
                v.x--;
            }
            v.y--;
        }
        return false;
    }

    private boolean customContains(Rectangle r, Point p) {
        return (p.x >= r.x) && (p.x <= r.x + r.width) && (p.y <= r.y) && (p.y >= r.y - r.height);
    }
}