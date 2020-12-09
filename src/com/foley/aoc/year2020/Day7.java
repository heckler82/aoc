package com.foley.aoc.year2020;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.Tuple;
import com.foley.aoc.util.functions.Regex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;

/**
 * Solutions for day 7
 *
 * @author Evan Foley
 * @version 24 Nov 2020
 */
public class Day7 extends Daily {
    private Map<String, List<Tuple<Integer, String>>> map;
    
    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day7(String fileName) {
        super(fileName);
        map = new HashMap<>();
        // Parse all data into the map
        // Data entries are in the format "color" : List of <quantity : "color">
        for(String s : input) {
            List<Tuple<Integer, String>> list = new ArrayList<>();
            String[] split = s.split(" bags contain ");
            // There are no additional bags inside this one
            if(split.length == 1) {
                map.put(split[0], list);
            } else {
                // Match pattern for "int color"
                Matcher m = Regex.getMatcher("(\\d+)\\s+(\\w+\\s+\\w+)", split[1]);
                // Poll all the results out of the string and add them to the map
                while(m.find()) {
                    Tuple<Integer, String> t = new Tuple<>(Integer.parseInt(m.group(1)), m.group(2));
                    list.add(t);
                }
                map.put(split[0], list);
            }
        }
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        System.out.printf("There are %d valud bag colors\n", getValidBagColors("shiny gold").size());
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        System.out.printf("1 shiny gold bag contains %d other bags\n", getTotalBagsInside("shiny gold"));
    }
    
    /**
    * Gets all the bag colors that can hold at least 1 of the specified color
    * 
    * @param s The bag color
    * @return All of the bag colors that can hold the specified color
    */
    private Set<String> getValidBagColors(String s) {
        List<String> list = new ArrayList<String>();
        // Find all colors that hold s in its color listing and add them to list
        for(String s2 : map.keySet()) {
            List<Tuple<Integer, String>> l = map.get(s2);
            for(Tuple<Integer, String> t : l) {
                if(t.getSecond().equals(s)) {
                    list.add(s2);
                }
            }
        }
        // Add all found colors to a set
        Set<String> set = new HashSet<>(list);
        // Find all the colors that hold the previously found colors, and union the resulting set to the created set
        for(String s2 : list) {
            set.addAll(getValidBagColors(s2));
        }
        return set;
    }
    
    /*
    * Gets the total number of bags that are contained within 1 of the specified bag color
    * 
    * @param s The bag color
    * @return The total number of bags that are contained within 1 of the specified bag color
    */
    private long getTotalBagsInside(String s) {
        // Get the colors that are held inside this color
        List<Tuple<Integer, String>> list = map.get(s);
        long sum = 0L;
        // Add up the total number of each color that is contained inside
        for(Tuple<Integer, String> t : list) {
            sum += t.getFirst() + t.getFirst() * getTotalBagsInside(t.getSecond());
        }
        return sum;
    }
}
