package com.foley.aoc.year2020;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.functions.Regex;

import java.util.*;
import java.util.regex.Matcher;

/**
 * Solutions for day 19
 *
 * @author Evan Foley
 * @version 24 Nov 2020
 */
public class Day19 extends Daily {
    private final String BASE_PATTERN = "^(\\d+): \"(.)\"$";
    private final String BASIC_PATTERN = "^(\\d+): (\\d+)(\\s\\d+)?$";
    private final String COMPLEX_PATTERN = "^(\\d+): (\\d+)(\\s\\d+)? (\\|) (\\d+)(\\s\\d+)?$";
    private final Map<String, Set<Integer>> rules;
    private final Set<String> terminal;
    private final Set<String> nonTerminal;

    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day19(String fileName) {
        super(fileName);
        rules = new HashMap<>();
        terminal = new HashSet<>();
        nonTerminal = new HashSet<>();
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        int mode = 0;
        int sum = 0;
        // Parse the input
        for(String s : input) {
            switch (mode) {
                case 0:
                    if ("".equals(s)) {
                        mode = 1;
                        continue;
                    }
                    // Check for complex rule
                    Matcher m = Regex.getMatcher(COMPLEX_PATTERN, s);
                    String id;
                    if (m.find()) {
                        id = m.group(1);
                        rules.put(id, new HashSet<>());
                        // If there is no group 3, this is form # | # and not # # | # #
                        if (m.group(3) == null) {
                            rules.get(id).add(Objects.hash(m.group(2)));
                            rules.get(id).add(Objects.hash(m.group(5)));
                        } else {
                            rules.get(id).add(Objects.hash(m.group(2), m.group(3)));
                            rules.get(id).add(Objects.hash(m.group(5), m.group(6)));
                        }
                        nonTerminal.add(id);
                        continue;
                    }

                    // Check for basic rule
                    m = Regex.getMatcher(BASIC_PATTERN, s);
                    if (m.find()) {
                        id = m.group(1);
                        rules.put(id, new HashSet<>());
                        rules.get(id).add(Objects.hash(m.group(2), m.group(3)));
                        nonTerminal.add(id);
                        continue;
                    }

                    // This is a base pattern
                    m = Regex.getMatcher(BASE_PATTERN, s);
                    m.find();
                    id = m.group(1);
                    rules.put(id, new HashSet<>());
                    rules.get(id).add(Objects.hash(m.group(2)));
                    nonTerminal.add(id);
                    terminal.add(m.group(2));
                    break;
                case 1:
                    // Determine if the string matches the rules

            }
        }
        System.out.printf("There are %d strings that match the rules\n", sum);
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
    }

    private void cyk(String w) {
        
    }
}
