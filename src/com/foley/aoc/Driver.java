package com.foley.aoc;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.Tuple;
import com.foley.aoc.util.functions.Range;

import java.util.ArrayList;
import java.util.List;

/**
 * Runs the program
 *
 * @author Evan Foley
 * @version 24 Nov 2020
 */
public class Driver {
    /**
     * Main entry point for the program
     *
     * @param args CLI arguments provided to the program
     */
    public static void main(String[] args) {
        Tuple<Integer, Integer> date = null;

        // Year and date are passed in through the command line
        if(args.length > 0) {
            // There should be at minimum 2 CLI args
            if(args.length % 2 != 0) {
                System.err.printf("An invalid number of arguments has been provided. Ensure arguments are in" +
                        " the format \'-flag value\'\n");
                System.exit(1);
            }
            date = getDateData(args);
        }

        assert date != null : "No date was provided on the command line";
        assert date.getFirst() >= 0 : "A valid year was not provided. Year cannot be negative";
        assert date.getSecond() >= 0 : "An invalid day value was provided. Day cannot be negative";

        List<Daily> tasks = getTasks(date);
        long total = 0L;
        for(Daily d : tasks) {
            total += d.doTasks();
        }
        System.out.printf("The total time to finish all requested tasks is %fms\n", total / 1000.0 / 1000.0);
    }

    /**
     * Attempts to get date data from an array of Strings that represent flags
     *
     * @param args the arguments
     * @return a tuple containing a year and a day
     */
    private static Tuple<Integer, Integer> getDateData(String[] args) {
        int year = -1;
        int day = 0;

        for(int i = 0; i < args.length; i += 2) {
            if("-year".equalsIgnoreCase(args[i])) {
                try {
                    year = Integer.parseInt(args[i + 1]);
                } catch(NumberFormatException e) {
                    System.err.printf("Could not parse an integer from %s\n", args[i + 1]);
                    System.exit(1);
                }
            } else {
                if("-day".equalsIgnoreCase(args[i])) {
                    try {
                        day = Integer.parseInt(args[i + 1]);
                    } catch(NumberFormatException e) {
                        System.err.printf("Could not parse an integer from %s\n", args[i + 1]);
                        System.exit(1);
                    }
                }
            }
        }

        Tuple<Integer, Integer> t = Tuple.pair(year, day);
        return t;
    }

    /**
     * Gets the requested tasks based on the date
     *
     * @param date the date
     * @return the list of tasks to copmlete
     */
    private static List<Daily> getTasks(Tuple<Integer, Integer> date) {
        List<Daily> list = new ArrayList<>();
        int start = 1;
        int end = 25;
        if(date.getSecond() != 0) {
            start = date.getSecond();
            end = date.getSecond();
        }

        for(;start <= end; start++) {
            String inputPath = "./res/" + date.getFirst() + "/day" + start + ".txt";
            String className = "com.foley.aoc.year" + date.getFirst() + ".Day" + start;
            list.add(Daily.getDaily(inputPath, className));
        }

        return list;
    }
}
