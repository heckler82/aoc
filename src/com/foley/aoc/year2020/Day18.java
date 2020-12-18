package com.foley.aoc.year2020;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.functions.Regex;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.BiPredicate;
import java.util.regex.Matcher;

/**
 * Solutions for day 18
 *
 * @author Evan Foley
 * @version 24 Nov 2020
 */
public class Day18 extends Daily {
    private long sum2;

    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day18(String fileName) {
        super(fileName);
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        long sum = 0;
        sum2 = 0;
        for(String s : input) {
            s = s.replaceAll("\\*", "-").replaceAll(" ", "");
            sum += solve(s);
            sum2 += solve(s, (s1, s2) -> !s1.equals("-") || !s2.equals("+"));
        }
        System.out.printf("The sum of all values is %d\n", sum);
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        System.out.printf("The sum of the advanced math problems is %d\n", sum2);
    }

    /**
     * Solves an input equation
     *
     * @param eq The equation
     * @return The final result
     */
    private long solve(String eq) {
        return solve(eq, (s, s2) -> true);
    }

    /**
     * Solves an input equation
     *
     * @param eq The equation
     * @param bp Operator precedence rules
     * @return The final result
     */
    private long solve(String eq, BiPredicate<String, String> bp) {
        var fullEquation = inFixToPostFix(eq, bp);
        Stack<Long> s = new Stack<>();
        for(String str : fullEquation) {
            switch(str) {
                case "+":
                    long r = s.pop();
                    long l = s.pop();
                    s.push(l + r);
                    break;
                case "-":
                    r = s.pop();
                    l = s.pop();
                    s.push(l * r);
                    break;
                default:
                    s.push(Long.parseLong(str));
            }
        }
        return s.isEmpty() ? -1 : s.pop();
    }

    /**
     * Uses the shunting-yard algorithm to convert an in-fix expression to a post-fix expression
     *
     * @param eq The input equation
     * @param bp The operator precedence
     * @return The equation in post-fix form
     */
    private List<String> inFixToPostFix(String eq, BiPredicate bp) {
        Stack<String> s = new Stack<>();
        List<String> list = new ArrayList<>();
        Matcher m = Regex.getMatcher("(\\d+|[+\\-()])", eq);
        while(m.find()) {
            String t = m.group(1);
            if(t.matches("[0-9]+")) {
                list.add(t);
            } else {
                if(t.matches("[\\+|-]")) {
                    while(!s.isEmpty() && bp.test(s.peek(), t) && !s.peek().equals("(")) {
                        list.add(s.pop());
                    }
                    s.push(t);
                } else {
                    if (t.equals("(")) {
                        s.push(t);
                    } else {
                        if (t.equals(")")) {
                            while (!s.peek().equals("(")) {
                                list.add(s.pop());
                            }
                            if (s.peek().equals("(")) {
                                s.pop();
                            }
                        }
                    }
                }
            }
        }
        while(!s.isEmpty()) {
            list.add(s.pop());
        }
        return list;
    }
}
