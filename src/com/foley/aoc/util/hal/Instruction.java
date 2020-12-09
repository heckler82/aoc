package com.foley.aoc.util.hal;

/**
 * Holds data for a computer instruction
 *
 * @author Evan Foley
 * @version 08 Dec 2020
 */
public class Instruction {
    private String op;
    private int val;

    /**
     * Creates a new instruction
     *
     * @param op The command operation
     * @param val The value
     */
    public Instruction(String op, int val) {
        this.op = op;
        this.val = val;
    }

    /**
     * Gets the command operation
     *
     * @return The command operation
     */
    public String getOp() {
        return op;
    }

    /**
     * Gets the value
     *
     * @return The value
     */
    public int getVal() {
        return val;
    }

    /**
     * Sets the instruction's command operation
     *
     * @param op The command operation
     */
    public void setOp(String op) {
        this.op = op;
    }

    /**
     * Sets the instruction's value
     *
     * @param val The value
     */
    public void setVal(int val) {
        this.val = val;
    }

    /**
     * Parses an instruction from a string
     *
     * @param s
     */
    public static Instruction parseInstruction(String s) {
        String[] split = s.split("\\s+");
        return new Instruction(split[0], Integer.parseInt(split[1]));
    }
}
