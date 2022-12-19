package com.foley.aoc.year2022;

import com.foley.aoc.util.Daily;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Solutions for day 3
 *
 * @author Evan Foley
 * @version 3 Dec 2022
 */
public class Day3 extends Daily {
    private final Map<Character, Integer> priorities;

    /**
     * Creates a new daily
     *
     * @param year     The year
     * @param fileName The name of the input file
     */
    public Day3(int year, String fileName) {
        super(year, fileName);
        priorities = new HashMap<>();
        for(int i = 1; i <= 26; i++) {
            priorities.put((char)(96 + i), i);
        }
        for(int i = 27; i <= 52; i++) {
            priorities.put((char)(38 + i), i);
        }
    }

    @Override
    protected void task1() {
        long priority = 0L;
        for(var s : input) {
            var leftHalf = s.substring(0, s.length() / 2);
            var rightHalf = s.substring(s.length() / 2);
            Set<Character> first = leftHalf.chars().mapToObj(e -> (char) e).collect(Collectors.toSet());
            Set<Character> second = rightHalf.chars().mapToObj(e -> (char) e).collect(Collectors.toSet());
            first.retainAll(second);
            var val = first.stream().findFirst();
            priority += priorities.get(val.orElse('a'));
        }
        System.out.printf("The total priority is %d\t", priority);
    }

    @Override
    protected void task2() {
        long priority = 0L;
        for(int i = 0; i < input.length - 2; i += 3) {
            var str1 = input[i];
            var str2 = input[i + 1];
            var str3 = input[i + 2];
            Set<Character> first = str1.chars().mapToObj(e -> (char) e).collect(Collectors.toSet());
            Set<Character> second = str2.chars().mapToObj(e -> (char) e).collect(Collectors.toSet());
            Set<Character> third = str3.chars().mapToObj(e -> (char) e).collect(Collectors.toSet());
            first.retainAll(second);
            first.retainAll(third);
            var val = first.stream().findFirst();
            priority += priorities.get(val.orElse('a'));
        }
        System.out.printf("The total priority is %d\t", priority);
    }
}
