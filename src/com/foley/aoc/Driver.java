package com.foley.aoc;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.Functions;

import javax.swing.JOptionPane;

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
        // Parse the current year
        int year = -1;
        int day = -1;

        // If year was provided on the command line, attempt to parse it
        if(args.length > 0) {
            try {
                year = Integer.parseInt(args[0]);
            } catch(NumberFormatException e) {
                System.err.printf("Cannot parse \"%s\" into a number\n", args[0]);
                System.exit(1);
            }

            // An additional input is the requested day
            if(args.length > 1) {
                // Determine if user wants all days, or only 1 day to run
                if("all".equalsIgnoreCase(args[1])) {
                    day = 0;
                } else {
                    // Parse number from argument
                    try {
                        day = Integer.parseInt(args[1]);
                    } catch(NumberFormatException e) {
                        System.err.printf("Cannot parse \"%s\" into a number\n", args[1]);
                        System.exit(1);
                    }
                }
            }
        }

        // Ensure a valid year was provided if command line argument was not provided
        while(year < 0) {
            String in = JOptionPane.showInputDialog(null, "What is the current year (Enter exit or press cancel to exit)?", "Enter Current Year", JOptionPane.PLAIN_MESSAGE);
            // Determine exit cases
            if("exit".equalsIgnoreCase(in) || in == null) {
                System.exit(0);
            }
            // Attempt to parse the input to a numerical year value
            try {
                year = Integer.parseInt(in);
            } catch(NumberFormatException e) {
                System.err.printf("Cannot parse \"%s\" into a number\n", in);
            }
        }

        // Ensure a valid day was provided if command line argument was not provided
        while(day < 0 || day >25) {
            String in = JOptionPane.showInputDialog(null, "What day would you like to run (Enter 1-25 or \"all\")?", "Enter Desired Day", JOptionPane.PLAIN_MESSAGE);
            // Determine exit cases
            if(in == null) {
                continue;
            }

            // Short out for all days requested
            if("all".equalsIgnoreCase(in)) {
                day = 0;
                break;
            }

            // Attempt to parse the input to a numerical day value
            try {
                day = Integer.parseInt(in);
            } catch(NumberFormatException e) {
                System.err.printf("Cannot parse \"%s\" into a number\n", in);
            }
        }

        // Run all days or only the requested day
        if(day == 0) {
            // Get and run all days for the year
            for(int i = 1; i < 26; i++) {
                String inputPath = "./res/" + year + "/day" + i + ".txt";
                String className = "com.foley.aoc.year" + year + ".Day" + i;
                Daily d = Functions.getDaily(inputPath, className);
                d.doTasks();
            }
        } else {
            String inputPath = "./res/" + year + "/day" + day + ".txt";
            String className = "com.foley.aoc.year" + year + ".Day" + day;
            Daily d = Functions.getDaily(inputPath, className);
            d.doTasks();
        }
    }
}
