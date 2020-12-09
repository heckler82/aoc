package com.foley.aoc.year2020;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.hal.CPU;
import com.foley.aoc.util.hal.Instruction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Solutions for day 8
 *
 * @author Evan Foley
 * @version 24 Nov 2020
 */
public class Day8 extends Daily {
    private List<Instruction> instructions;
    private CPU cpu;

    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day8(String fileName) {
        super(fileName);
        instructions = new ArrayList<>();
        // Get all instructions from the input
        for(String s : input) {
            instructions.add(Instruction.parseInstruction(s));
        }
        cpu = new CPU();
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        // Run the unmodified program
        runProgram();
        System.out.printf("The value in the accumulator before the program repeats is %d\n", cpu.getAccumulator());
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        // Modify a single 'nop' or 'jmp' instruction until the program can run without loops
        for(Instruction i : instructions) {
            cpu.reset();
            // Ignore accumulate instructions
            if(i.getOp().equals("acc")) {
                continue;
            }
            // Modify a single instruction each run
            String mod = "jmp";
            if(i.getOp().equals("jmp")) {
                mod = "nop";
            }
            // If the modified run was successful, break out of the loop
            if(attemptModifiedRun(i, mod)) {
                break;
            }
        }
        System.out.printf("The final value in the accumulator is %d\n", cpu.getAccumulator());
    }
    
    /**
    * Modifies an instruction with a new operation and runs the program
    * 
    * @param i The instruction to modify
    * @param newOp The new operation
    * @return True if the program successfully reached termination
    */
    private boolean attemptModifiedRun(Instruction i, String newOp) {
        String oldOp = i.getOp();
        i.setOp(newOp);
        boolean b = runProgram();
        i.setOp(oldOp);
        return b;
    }
    
    /**
    * Runs the program defined by the list of instructions
    * 
    * @return True if the program terminated normally
    */
    private boolean runProgram() {
        // Hold instructions that have already run
        Set<Integer> prev = new HashSet<>();
        
        // Continue to loop until the end of the program is reached, or a loop is discovered
        while(cpu.getInstructionPointer() < instructions.size() && prev.add(cpu.getInstructionPointer())) {
            Instruction current = instructions.get(cpu.getInstructionPointer());
            cpu.runInstruction(current);
        }
        return cpu.getInstructionPointer() == instructions.size();
    }
}
