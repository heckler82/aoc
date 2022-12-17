package com.foley.aoc.year2022;

import com.foley.aoc.util.Daily;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Solutions for day 1
 *
 * @author Evan Foley
 * @version 1 Dec 2022
 */
public class Day1 extends Daily {
    private List<Elf> list = new ArrayList<>();
    /**
     * Creates a new daily
     *
     * @param year     The year
     * @param fileName The name of the input file
     */
    public Day1(int year, String fileName) {
        super(year, fileName);
    }

    @Override
    protected void task1() {
        Elf elf = new Elf();

        for(String s : input) {
            if(!s.equals("")) {
                elf.addCalories(Integer.parseInt(s));
            } else {
                list.add(elf);
                elf = new Elf();
            }
        }
        list.add(elf);
        list.sort((e1, e2) -> Integer.compare(e2.cal, e1.cal));
        System.out.printf("The elf with the most calories is carrying %d calories\n", list.get(0).cal);
    }

    @Override
    protected void task2() {
        System.out.printf("The top 3 elves are carrying %d calories\n", list.get(0).cal + list.get(1).cal + list.get(2).cal);
    }

    private class Elf {
        private int cal;
        private int numItems;

        public Elf() {
            cal = 0;
            numItems = 0;
        }

        public void addCalories(int calories) {
            numItems++;
            cal +=  calories;
        }
    }
}
