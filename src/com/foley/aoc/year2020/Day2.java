package com.foley.aoc.year2020;

import com.foley.aoc.util.Daily;

/**
 * Solutions for day 2
 *
 * @author Evan Foley
 * @version 24 Nov 2020
 */
public class Day2 extends Daily {
    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day2(String fileName) {
        super(fileName);
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        int count = 0;
        for(String s : input) {
            String[] tokens = s.split("\\s+");
            String[] range = tokens[0].split("-");
            count += isValidPassword(Integer.parseInt(range[0]), Integer.parseInt(range[1]), tokens[1].charAt(0), tokens[2]) ? 1 : 0;
        }
        System.out.printf("There are %d valid passwords in the list\n", count);
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        int count = 0;
        for(String s : input) {
            String[] tokens = s.split("\\s+");
            String[] range = tokens[0].split("-");
            count += isValidPassword2(Integer.parseInt(range[0]), Integer.parseInt(range[1]), tokens[1].charAt(0), tokens[2]) ? 1 : 0;
        }
        System.out.printf("There are %d valid passwords in the list\n", count);
    }
    
    /**
    * Determines if a password is valid if a target character appears in the string a certain number of times
    * 
    * @param min The minimum times the character must appear
    * @param max The maximum times the character must appear
    * @param target The target character
    * @param password The password string
    * @return True if the password string has the target character appear between min and max times inclusive
    */
    private boolean isValidPassword(int min, int max, char target, String password) {
        int freq = Functions.getFrequency(password, target);
        return freq >= min && freq <= max);
    }
    
    /**
    * Determines if a password is valid if only one of two positions is equal to the target character
    * 
    * @param pos The first position
    * @param pos2 The second position
    * @param target The target character
    * @param password The password string
    * @return True if the password string has one of two positions equal to the target character
    */
    private boolean isValidPassword2(int pos, int pos2, char target, String password) {
        // Fast fail
        if(pos < 1 || pos > password.length() || pos2 < 1 || pos2 > password.length()) {
            return false;
        }
        // Get characters
        char a = password.charAt(pos - 1);
        char b = password.charAt(pos - 2);
        return (a == target) ^ (b == target);
    }
}
