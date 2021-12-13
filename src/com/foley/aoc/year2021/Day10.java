package com.foley.aoc.year2021;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.Tuple;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Solutions for day 10
 *
 * @author Evan Foley
 * @version 10 Dec 2021
 */
public class Day10 extends Daily {
    private Map<Character, Tuple<Character, Integer>> illegal;
    private Map<Character, Integer> close;
    List<String> consider;

    /**
     * Creates a new daily
     *
     * @param year The year
     * @param fileName The name of the input file
     */
    public Day10(int year, String fileName) {
        super(year, fileName);
        illegal = new HashMap<>();
        illegal.put(')', Tuple.pair('(', 3));
        illegal.put(']', Tuple.pair('[', 57));
        illegal.put('}', Tuple.pair('{', 1197));
        illegal.put('>', Tuple.pair('<', 25137));

        close = new HashMap<>();
        close.put('(', 1);
        close.put('[', 2);
        close.put('{', 3);
        close.put('<', 4);

        consider = new ArrayList<>();
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        int errorSum = 0;
        for(String s : input) {
            consider.add(s);
            Stack<Character> open = new Stack<>();
            for(char c : s.toCharArray()) {
                if(illegal.containsKey(c)) {
                    char t = open.pop();
                    if(illegal.get(c).getFirst() != t) {
                        errorSum += illegal.get(c).getSecond();
                        consider.remove(s);
                        break;
                    }
                } else {
                    open.push(c);
                }
            }
        }
        System.out.printf("The total syntax error score is %d\n", errorSum);
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        List<Long> scores = new ArrayList<>();
        for(String s : consider) {
            Stack<Character> open = new Stack<>();
            for(char c : s.toCharArray()) {
                if(illegal.containsKey(c)) {
                    open.pop();
                    continue;
                }
                open.push(c);
            }

            long total = 0L;
            while(!open.isEmpty()) {
                char c = open.pop();
                total = 5 * total + close.get(c);
            }
            scores.add(total);
        }
        scores.sort(Comparator.naturalOrder());
        System.out.printf("The middle score is %d\n", scores.get(scores.size() / 2));
    }
}