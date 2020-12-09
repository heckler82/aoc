package com.foley.aoc.util.hal;

import java.util.List;

/**
 * Runs a set of instructions according to a set of defined rules
 *
 * @author Evan Foley
 * @version 08 Dec 2020
 */
public class CPU {
    private int acc;
    private int iptr;

    /**
     * Creates a new cpu
     */
    public CPU() {
        acc = 0;
        iptr = 0;
    }

    /**
     * Gets the current value in the accumulator
     *
     * @return The accumulator
     */
    public int getAccumulator() {
        return acc;
    }

    /**
     * Resets the accumulator to 0
     */
    public void resetAccumulator() {
        acc = 0;
    }

    /**
     * Gets the current value of the instruction pointer
     *
     * @return The instruction pointer
     */
    public int getInstructionPointer() {
        return iptr;
    }

    /**
     * Resets the instruction pointer to 0
     */
    public void resetInstructionPointer() {
        iptr = 0;
    }

    /**
     * Resets the cpu to its starting state
     */
    public void reset() {
        resetAccumulator();
        resetInstructionPointer();
    }

    /**
     * Runs an instruction from a program
     *
     * @param i The instruction to run
     */
    public void runInstruction(Instruction i) {
        switch(i.getOp()) {
            case "jmp":
                iptr += i.getVal();
                break;
            case "acc":
                acc += i.getVal();
            case "nop":
                iptr++;
                break;
        }
    }

    /**
     * Runs an entire program
     *
     * @param prgm The list of instructions to run
     */
    public void runProgram(List<Instruction> prgm) {
        while(iptr < prgm.size()) {
            Instruction i = prgm.get(iptr);
            runInstruction(i);
        }
    }
}
