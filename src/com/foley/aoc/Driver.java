package com.foley.aoc;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.Tuple;

import java.util.ArrayList;
import java.util.List;

/**
 * Runs the program
 *
 * @author Evan Foley
 * @version 09 Aug 2022
 */
public class Driver {
    /**
     * Main entry point for the program
     *
     * @param args CLI arguments provided to the program
     */
    public static void main(String[] args) {
        // If no values passed in, cannot run
        if(args.length < 1) {
            System.err.println("No date values were provided, could not determine which year to run");
            System.exit(1);
        }

        var day = args.length > 1 ? args[1] : "0";
        var date = getDate(args[0], day);
        List<Daily> tasks = getTasks(date);

        long total = 0L;
        for(Daily d : tasks) {
            total += d.doTasks();
        }
        System.out.printf("The total time to finish all requested tasks is %fms\n", total / 1000.0 / 1000.0);
    }

    /**
     * Attempts to get a year and day from an array of Strings
     *
     * @param year the year
     * @param day the day
     * @return a tuple of ints containing a year and a day
     */
    private static Tuple<Integer, Integer> getDate(String year, String day) throws NumberFormatException {
        int numYear = 0;
        int numDay = 0;

        try {
            numYear = Integer.parseInt(year);
            numDay = Integer.parseInt(day);
        } catch (NumberFormatException e) {
            System.err.println("Invalid date parameters. Date values must be numerical integers");
            System.exit(1);
        }

        if(numYear < 1) {
            throw new IllegalArgumentException("Year must be greater than 0");
        }
        if(numDay < 0 || numDay > 25) {
            throw new IllegalArgumentException("Day must be between 0 and 25 inclusive");
        }

        return Tuple.pair(numYear, numDay);
    }

    /**
     * Gets the requested tasks based on the date
     *
     * @param date the date
     * @return the list of tasks to copmlete
     */
    private static List<Daily> getTasks(Tuple<Integer, Integer> date) {
        List<Daily> list = new ArrayList<>();

        int start = date.getSecond() == 0 ? 1 : date.getSecond();
        int end = date.getSecond() == 0 ? 25 : date.getSecond();

        for(int i = start; i <= end; i++) {
            String inputPath = "./res/" + date.getFirst() + "/day" + i + ".txt";
            String className = "com.foley.aoc.year" + date.getFirst() + ".Day" + i;
            list.add(Daily.getDaily(date.getFirst(), inputPath, className));
        }

        return list;
    }
}
