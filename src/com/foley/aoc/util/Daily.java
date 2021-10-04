package com.foley.aoc.util;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Provides initial setup and template for an advent of code day
 *
 * @author Evan Foley
 * @version 24 Nov 2020
 */
public abstract class Daily {
    protected String[] input;
    private int day;

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

            input = rawInput.toArray(new String[0]);
        } catch(FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Could not find the specified file: " + fileName,
                    "File Not Found", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "There was an error conducting IO operations:\n" + e.getMessage(), "IOException", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    /**
     * Does both tasks for the day
     */
    public long doTasks() {
        System.out.printf("======================== DAY %d ========================\n", day);
        long start = System.nanoTime();
        System.out.println("---- Task 1 ----");
        task1();
        System.out.println("\n---- Task 2 ----");
        task2();
        long elapsed = System.nanoTime() - start;
        System.out.println();
        System.out.printf("Total task runtime was %fms\n", elapsed / 1000.0 / 1000.0);
        return elapsed;
    }

    /**
     * Accomplishes the first task for the day
     */
    protected abstract void task1();

    /**
     * Accomplishes the second task for the day
     */
    protected abstract void task2();

    /**
     * Dynamically gets a new daily
     *
     * @param inputPath The path to the input file
     * @param className The name of the class to instantiate
     * @return A new daily
     */
    public static Daily getDaily(String inputPath, String className) {
        if(inputPath == null || className == null) {
            throw new IllegalArgumentException("Path to input file or class name is null");
        }
        try {
            Class cl = Class.forName(className);
            Constructor<Daily> con = cl.getConstructor(inputPath.getClass());
            return con.newInstance(inputPath);
        } catch(ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
