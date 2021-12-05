package com.foley.aoc.year2021;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.hal.Instruction;
import com.foley.aoc.util.point.Point3D;

import java.util.ArrayList;
import java.util.List;

/**
 * Solutions for day 2
 *
 * @author Evan Foley
 * @version 02 Dec 2021
 */
public class Day2 extends Daily {
    private Point3D.Long pos;
    private List<Instruction> instructions;

    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day2(String fileName) {
        super(fileName);
        pos = Point3D.Long.zero();
        instructions = new ArrayList<>();
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        for(String s : input) {
            Instruction instr = Instruction.parseInstruction(s);
            instructions.add(instr);
            switch(instr.getOp()) {
                case "forward":
                    pos.x += instr.getVal();
                    break;
                case "down":
                    pos.y += instr.getVal();
                    break;
                case "up":
                    pos.y -= instr.getVal();
            }
        }
        System.out.printf("The final position is %d\n", pos.x * pos.y);
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        pos = Point3D.Long.zero();
        for(Instruction i : instructions) {
            switch(i.getOp()) {
                case "forward":
                    pos.x += i.getVal();
                    pos.y += i.getVal() * pos.z;
                    break;
                case "down":
                    pos.z += i.getVal();
                    break;
                case "up":
                    pos.z -= i.getVal();
            }
        }
        System.out.printf("The final position is %d\n", pos.x * pos.y);
    }
}