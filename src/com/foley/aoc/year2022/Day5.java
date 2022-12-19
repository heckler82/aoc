package com.foley.aoc.year2022;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.Triple;
import com.foley.aoc.util.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Solutions for day 5
 *
 * @author Evan Foley
 * @version 5 Dec 2022
 */
public class Day5 extends Daily {
    private final List<Stack<Character>> stacks;
    private final List<Stack<Character>> stacks2;
    private final List<Triple<Integer, Integer, Integer>> instructions;

    /**
     * Creates a new daily
     *
     * @param year     The year
     * @param fileName The name of the input file
     */
    public Day5(int year, String fileName) {
        super(year, fileName);

        var t = getInputInfo(input);
        int numStacks = t.getFirst();
        stacks = new ArrayList<>(numStacks);
        stacks2 = new ArrayList<>(numStacks);
        for(int i = 0; i < numStacks; i++) {
            stacks.add(new Stack<Character>());
            stacks2.add(new Stack<Character>());
        }

        // Fill the initial stacks here
        int ptr = t.getSecond() - 3;
        while(ptr >= 0) {
            String line = input[ptr];
            for(int i = 0; i < line.length(); i += 4) {
                if(line.charAt(i) == '[') {
                    stacks.get(i / 4).push(line.charAt(i + 1));
                    stacks2.get(i / 4).push(line.charAt(i + 1));
                }
            }
            ptr--;
        }

        instructions = new ArrayList<>();
        for(int i = t.getSecond(); i < input.length; i++) {
            instructions.add(parseMovement(input[i]));
        }
    }

    @Override
    protected void task1() {
        for(var instr : instructions) {
            for(int i = 0; i < instr.getFirst(); i++) {
                var c = stacks.get(instr.getSecond() - 1).pop();
                stacks.get(instr.getThird() - 1).push(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(var s : stacks)  {
            sb.append(s.peek());
        }
        System.out.printf("The crates that are on top are %s\n", sb.toString());
    }

    @Override
    protected void task2() {
        for(var instr : instructions) {
            if(instr.getFirst() == 1) {
                var c = stacks2.get(instr.getSecond() - 1).pop();
                stacks2.get(instr.getThird() - 1).push(c);
            } else {
                var s = new Stack<Character>();
                for(int i = 0; i < instr.getFirst(); i++) {
                    s.push(stacks2.get(instr.getSecond() - 1).pop());
                }
                while(!s.isEmpty()) {
                    stacks2.get(instr.getThird() - 1).push(s.pop());
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(var s : stacks2)  {
            sb.append(s.peek());
        }
        System.out.printf("The crates that are on top are %s\n", sb.toString());
    }

    /**
     * Parses an instruction line into a triple. The first value is the number of crates to move, the second is the
     * source stack, and the third is the destination stack
     *
     * @param s the line of text to parse
     * @return a triple consisting of 3 integer values
     */
    private Triple<Integer, Integer, Integer> parseMovement(String s) {
        var split = s.split("\\s+");
        return Triple.triplet(Integer.parseInt(split[1]),
                Integer.parseInt(split[3]),
                Integer.parseInt(split[5]));
    }

    /**
     * Returns stack information based on the input. The first value is the number of stacks, and the second is the
     * index of the first movement instruction
     *
     * @param s the input Strings
     * @return a tuple consisting of the number of stacks and the index of the first movement instruction
     */
    private Tuple<Integer, Integer> getInputInfo(String[] s) {
        int i = 0;
        while(!input[i].equals("")) {
            i++;
        }
        return Tuple.pair((s[i - 1].length() / 4) + 1, i + 1);
    }
}
