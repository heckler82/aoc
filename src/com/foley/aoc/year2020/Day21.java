package com.foley.aoc.year2020;

import com.foley.aoc.util.Daily;

import java.util.HashMap;
import java.util.Map;

/**
 * Solutions for day 21
 *
 * @author Evan Foley
 * @version 24 Nov 2020
 */
public class Day21 extends Daily {
    private Map<String, Integer> ingredients;
    private Map<String, Integer> allergens;

    /**
     * Creates a new daily
     *
     * @param year The year
     * @param fileName The name of the input file
     */
    public Day21(int year, String fileName) {
        super(year, fileName);
        ingredients = new HashMap<>();
        allergens = new HashMap<>();
        for(String s : input) {
            s = s.replace(")", "");
            String[] sep = s.split("\\(contains ");
            String[] in = sep[0].split("\\s+");
            for(String ingredient : in) {
                ingredients.put(ingredient, ingredients.getOrDefault(ingredient, 0) + 1);
            }
            String[] all = sep[1].split(", ");
            for(String allergen : all) {
                allergens.put(allergen, allergens.getOrDefault(allergens, 0) + 1);
            }
        }
        int test = 0;
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
}
