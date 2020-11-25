package com.foley.aoc.util;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Provides initial setup and template for an advent of code day
 *
 * @author Evan Foley
 * @version 24 Nov 2020
 */
public abstract class Daily {
    protected int day = 0;
    protected String[] input;

    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Daily(String fileName) {
        setup(fileName);
        String name = getClass().getSimpleName();
        day = Integer.parseInt(name.substring(3));
    }

    /**
     * Polls the initial data from the specified input file
     *
     * @param fileName The name of the input file
     */
    private void setup(String fileName) {
        try (BufferedReader rdr = new BufferedReader(new FileReader(fileName))) {
            ArrayList<String> rawInput = new ArrayList<>();
            String line;

            // Read all input into the initial container
            while((line = rdr.readLine()) != null) {
                rawInput.add(line);
            }

            // Convert to array
            input = rawInput.toArray(new String[0]);
        } catch(FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Could not find the specified file: " + fileName,
                    "File Not Found", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "IOException", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    /**
     * Does both tasks for the day
     */
    public void doTasks() {
        System.out.printf("============ DAY %d ============\n", day);
        System.out.println("---- Task 1 ----");
        task1();
        System.out.println("\n---- Task 2 ----");
        task2();
    }

    /**
     * Accomplishes the first task for the day
     */
    protected abstract void task1();

    /**
     * Accomplishes the second task for the day
     */
    protected abstract void task2();
}
