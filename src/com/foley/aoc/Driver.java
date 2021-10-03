package com.foley.aoc;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.Tuple;

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

        // Check for command line arguments
        if(args.length > 0) {
            // Ensure flags are paired
            if(args.length % 2 != 0) {
                System.err.printf("An invalid number of arguments has been provided. Ensure arguments are in" +
                        "the format \'-flag value\'\n");
                System.exit(1);
            }
            date = getDateData(args);

            // Was a year provided or is day invalid
            if(date.getFirst() < 0) {
                System.err.printf("Year was not provided as input\n");
                System.exit(1);
            }
            if(date.getSecond() < 0) {
                System.err.printf("An invalid day value was provided. Day cannot be negative\n");
                System.exit(1);
            }
        }

        assert date != null : "No date was provided on the command line";

        // Run all days or only the requested day
        if(date.getSecond() == 0) {
            // Get and run all days for the year
            long total = 0L;
            for(int i = 1; i < 26; i++) {
                String inputPath = "./res/" + date.getFirst() + "/day" + i + ".txt";
                String className = "com.foley.aoc.year" + date.getFirst() + ".Day" + i;
                Daily d = Daily.getDaily(inputPath, className);
                total += d.doTasks();
            }
            System.out.printf("Total time for all days to run is %fms\n", total / 1000.0 / 1000.0);
        } else {
            String inputPath = "./res/" + date.getFirst() + "/day" + date.getSecond() + ".txt";
            String className = "com.foley.aoc.year" + date.getFirst() + ".Day" + date.getSecond();
            Daily d = Daily.getDaily(inputPath, className);
            d.doTasks();
        }
    }

    /**
     * Attempts to get date data from an array of Strings that represent flags
     *
     * @param args the arguments
     * @return a tuple containing a year and a day
     */
    private static Tuple<Integer, Integer> getDateData(String[] args) {
        int year = -1;
        int day = -1;

        // Get the year and day values
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
}
