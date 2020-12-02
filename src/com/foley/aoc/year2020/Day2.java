package com.foley.aoc.year2020;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.Functions;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Solutions for day 2
 *
 * @author Evan Foley
 * @version 24 Nov 2020
 */
public class Day2 extends Daily {
    private List<Entry> list;
    
    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day2(String fileName) {
        super(fileName);
        list = new ArrayList<>();
        // Parse all input into entries
        for(String s : input) {
            list.add(new Entry(s));
        }
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        int count = 0;
        for(Entry e : list) {
            count += isValidPassword(e.val, e.val2, e.letter, e.word) ? 1 : 0;
        }
        System.out.printf("There are %d valid passwords in the list\n", count);
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        int count = 0;
        for(Entry e : list) {
            count += isValidPassword2(e.val, e.val2, e.letter, e.word) ? 1 : 0;
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
    
    /**
    * Utility class for holding line data from input
    */
    private class Entry {
        public int val;
        public int val2;
        public char letter;
        public String word;
        static Pattern p = Pattern.compile("(\\d+)-(\\d+)\\s(.):.(\\S+)");
        
        /**
        * Creates a new entry
        * 
        * @param in The string to parse
        */
        public Entry(String in) {
            Matcher m = p.matcher(in);
            if(m.find()) {
                val = Integer.parseInt(m.group(1));
                val2 = Integer.parseInt(m.grou(2));
                letter = m.group(3).charAt(0);
                word = m.group(4);
            }
        }
    }
}
