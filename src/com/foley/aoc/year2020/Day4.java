package com.foley.aoc.year2020;

import com.foley.aoc.util.Daily;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Solutions for day 4
 *
 * @author Evan Foley
 * @version 24 Nov 2020
 */
public class Day4 extends Daily {
    private int isValid;
    
    /**
     * Creates a new daily
     *
     * @param year The year
     * @param fileName The name of the input file
     */
    public Day4(int year, String fileName) {
        super(year, fileName);
        isValid = 0;
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        int haveAllData = 0;
        Passport p = new Passport();
        for(String s : input) {
            if("".equals(s)) {
                haveAllData += p.hasAllData() ? 1 : 0;
                isValid += p.validate() ? 1 : 0;
                p = new Passport();
            } else {
                p.parse(s);
            }
        }
        // Account for the last line that doesn't have a newline after it
        haveAllData += p.hasAllData() ? 1 : 0;
        isValid += p.validate() ? 1 : 0;
        System.out.printf("There are %d passports that have all necessary data fields\n", haveAllData);
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        System.out.printf("There are %d passports that pass validation\n", isValid);
    }
    
    /**
    * Container for holding passport data
    */
    private class Passport {
        private int activeFlags;
        Map<Integer, String> dataMap;

        int BIRTH_YEAR = 1;
        int ISSUE_YEAR = 2;
        int EXPIRATION_YEAR = 4;
        int HEIGHT = 8;
        int HAIR_COLOR = 16;
        int EYE_COLOR = 32;
        int PASSPORT_ID = 64;
        int COUNTRY_ID = 128;

        Map<String, Integer> validFlags = new HashMap<>();
        Set<String> set = new HashSet<>();
        
        /**
        * Creates a new passport
        */
        public Passport() {
            activeFlags = 0;
            dataMap = new HashMap<>();
            validFlags.put("byr", BIRTH_YEAR);
            validFlags.put("iyr", ISSUE_YEAR);
            validFlags.put("eyr", EXPIRATION_YEAR);
            validFlags.put("hgt", HEIGHT);
            validFlags.put("hcl", HAIR_COLOR);
            validFlags.put("ecl", EYE_COLOR);
            validFlags.put("pid", PASSPORT_ID);
            validFlags.put("cid", COUNTRY_ID);
            set.add("amb");
            set.add("blu");
            set.add("brn");
            set.add("gry");
            set.add("grn");
            set.add("hzl");
            set.add("oth");
        }
        
        /**
        * Activates a flag in the passport
        * 
        * @param flag The flag to activate
        * @param data The data to map to the flag
        */
        private void activateFlag(int flag, String data) {
            activeFlags |= flag;
            dataMap.put(flag, data);
        }
        
        /**
        * Ensures that a passport has all required data
        * 
        * @return True if the passport has all required data
        */
        public boolean hasAllData() {
            return (activeFlags & 127) == 127;
        }
        
        /*
        * Validates that all passport data is within expected parameters
        * 
        * @return True if all data validates
        */
        public boolean validate() {
            // Are all flags set
            if(!hasAllData()) {
                return false;
            }
            
            // BIRTH YEAR
            int byr = Integer.parseInt(dataMap.get(BIRTH_YEAR));
            if(byr < 1920 || byr > 2002) {
                return false;
            }
            
            // ISSUE YEAR
            int iyr = Integer.parseInt(dataMap.get(ISSUE_YEAR));
            if(iyr < 2010 || iyr > 2020) {
                return false;
            }
            
            // EXPIRATION YEAR
            int eyr = Integer.parseInt(dataMap.get(EXPIRATION_YEAR));
            if(eyr < 2020 || eyr > 2030) {
                return false;
            }
            
            // HEIGHT
            String s = dataMap.get(HEIGHT);
            Matcher m = match("(\\d+)(cm|in)", s);
            if(!m.find()) {
                return false;
            }
            int hgt = Integer.parseInt(m.group(1));
            if(m.group(2).equals("cm")) {
                if(hgt < 150 || hgt > 193) {
                    return false;
                }
            } else {
                if(hgt < 59 || hgt > 76) {
                    return false;
                }
            }
            
            // HAIR COLOR
            s = dataMap.get(HAIR_COLOR);
            m = match("#([a-fA-F0-9]{6})", s);
            if(!m.find()) {
                return false;
            }
            
            // EYE COLOR
            s = dataMap.get(EYE_COLOR);
            if(!set.contains(s)) {
                return false;
            }
            
            // PASSPORT ID
            s = dataMap.get(PASSPORT_ID);
            m = match("^\\d{9}$", s);
            // All other tests have passed, return the result of matching for a passport id
            return m.find();
        }
        
        /**
        * Gets the matcher for a given pattern and string
        * 
        * @param pattern The pattern to match against
        * @param s The string to match
        * @return The matcher
        */
        private Matcher match(String pattern, String s) {
            Pattern p = Pattern.compile(pattern);
            return p.matcher(s);
        }
        
        /**
        * Parses an input string into the passport
        * 
        * @param s The input string
        */
        public void parse(String s) {
            Pattern p = Pattern.compile("(\\S+):(\\S+)");
            Matcher m = p.matcher(s);
            // While valid data pairs are found, add to the passport
            while(m.find()) {
                String strF = m.group(1);
                String dat = m.group(2);
                int flag = validFlags.getOrDefault(strF, -1);
                if(flag != -1) {
                    activateFlag(flag, dat);
                }
            }
        }
    }
}
