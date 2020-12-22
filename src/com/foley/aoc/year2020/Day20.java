package com.foley.aoc.year2020;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.functions.Regex;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Solutions for day 20
 *
 * @author Evan Foley
 * @version 24 Nov 2020
 */
public class Day20 extends Daily {
    private Map<Integer, char[][]> map;

    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day20(String fileName) {
        super(fileName);
        map = parseInput(input);
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
    }

    private Map<Integer, char[][]> parseInput(String[] input) {
        Map<Integer, char[][]> map = new HashMap<>();
        int currentId = 0;
        int currentRow = 0;
        char[][] chars = new char[10][];
        for(String s : input) {
            if(s.contains("Tile ")) {
                currentId = Integer.parseInt(Regex.getSingleMatch("(\\d+)", s));
                continue;
            }
            if("".equals(s)) {
                map.put(currentId, chars);
                chars = new char[10][];
                currentRow = 0;
                continue;
            }
            chars[currentRow] = s.toCharArray();
            currentRow++;
        }
        map.put(currentId, chars);
        return map;
    }
}
