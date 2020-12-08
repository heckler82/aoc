package com.foley.aoc.year2020;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.functions.Regex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;

/**
 * Solutions for day 8
 *
 * @author Evan Foley
 * @version 24 Nov 2020
 */
public class Day8 extends Daily {
    private List<Instruction> instructions;
    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day8(String fileName) {
        super(fileName);
        // Get all instructions from the input
        for(String s : input) {
            instructions.add(parseInstructions(s));
        }
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        // Run the unmodified program
        runProgram();
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        // Modify a single 'nop' or 'jmp' instruction until the program can run without loops
        for(Instruction i : instructions) {
            // Ignore accumulate instructions
            if(i.op.equals("acc")) {
                continue;
            }
            // Modify a single instruction each run
            String mod = "jmp";
            if(i.op.equals("jmp")) {
                mod = "nop";
            }
            // If the modified run was successful, break out of the loop
            if(attemptModifiedRun(i, mod)) {
                break;
            }
        }
    }
    
    /**
    * Modifies an instruction with a new operation and runs the program
    * 
    * @param i The instruction to modify
    * @param newOp The new operation
    * @return True if the program successfully reached termination
    */
    private boolean attemptModifiedRun(Instruction i, String newOp) {
        String oldOp = i.op;
        i.op = newOp;
        boolean b = runProgram();
        i.op = oldOp;
        return b;
    }
    
    /**
    * Runs the program defined by the list of instructions
    * 
    * @return True if the program terminated normally
    */
    private boolean runProgram() {
        int accumulator = 0;
        int iptr = 0;
        Set<Integer> prev = new HashSet<>();
        
        // Continue to loop until the end of the program is reached, or a loop is discovered
        while(iptr < instructions.size() && prev.add(iptr)) {
            Instruction current = instructions.get(iptr);
            switch(current.op) {
                case "jmp":
                    iptr += current.val;
                    break;
                case "acc":
                    accumulator += current.val;
                case "nop":
                    iptr++;
                    break;
            }
        }
        System.out.printf("THe value in the accumulator is %d\n", accumulator);
        return iptr == instructions.size();
    }
    
    /**
    * Parses an instruction out of a string
    * 
    * @param s The string to parse
    * @return The instruction parsed from the string
    */
    private Instruction parseInstruction(String s) {
        Matcher m = Regex.getMatcher("(\\w+)\\s+((-|\\+)?\\d+)", s);
        if(m.find()) {
            return new Insrtuction(m.group(1), Integer.parseInt(m.group(2)));
        }
        return null;
    }
    
    /**
    * Holds data for a cimputer instruction
    */
    private class Instruction {
        private String op;
        private int val;
        
        /**
        * Creates a new instruction
        * 
        * @param op The operation
        * @param val The value
        */
        public Instruction(String op, int val) {
            this.op = op;
            this.val = val;
        }
    }
}
