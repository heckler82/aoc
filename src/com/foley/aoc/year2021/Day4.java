package com.foley.aoc.year2021;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.Tuple;
import com.foley.aoc.util.functions.Regex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * Solutions for day 1
 *
 * @author Evan Foley
 * @version 04 Dec 2021
 */
public class Day4 extends Daily {
    private List<Integer> guesses;
    private List<Board> boards;
    private long finalWin;

    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day4(String fileName) {
        super(fileName);
        guesses = new ArrayList<>();
        boards = new ArrayList<>();
        finalWin = 0L;
        String[] guessRaw = input[0].split(",");
        for(String s : guessRaw) {
            guesses.add(Integer.parseInt(s));
        }
        setupBoards(input);
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        int curr = 0;
        List<Board> winners = new ArrayList<>();
        while(boards.size() > 0) {
            int num = guesses.get(curr);
            for(Board b : boards) {
                if(b.checkNumber(num)) {
                    winners.add(b);
                    System.out.printf("Board won: %d\n", b.computeBoardScore(num));
                }
            }
            if(winners.size() > 0) {
                boards.removeAll(winners);
                if(boards.size() == 0) {
                    finalWin = winners.get(winners.size() - 1).computeBoardScore(num);
                }
            }
            winners.clear();
            curr = (curr + 1) % guesses.size();
        }
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        System.out.printf("The squid wins, and you lose with a final score of %d\n", finalWin);
    }

    private void setupBoards(String[] in) {
        for(int i = 2; i < in.length; i += 6) {
            Board board = new Board();
            board.addBoardRow(in[i], 0);
            board.addBoardRow(in[i + 1], 1);
            board.addBoardRow(in[i + 2], 2);
            board.addBoardRow(in[i + 3], 3);
            board.addBoardRow(in[i + 4], 4);
            boards.add(board);
        }
    }

    private class Board {
        Map<Integer, Tuple<Integer, Integer>> map;
        int[] rows = {5, 5, 5, 5, 5};
        int[] cols = {5, 5, 5, 5, 5};

        public Board() {
            map = new HashMap<>();
        }

        public boolean checkNumber(int value) {
            if(map.containsKey(value)) {
                int row = map.get(value).getFirst();
                int col = map.get(value).getSecond();
                map.remove(value);
                rows[row]--;
                cols[col]--;
                if(rows[row] == 0 || cols[col] == 0) {
                    return true;
                }
            }
            return false;
        }

        public void addBoardRow(String rowText, int row) {
            Matcher m = Regex.getMatcher("\\d+", rowText);
            int col = 0;
            while(m.find()) {
                map.put(Integer.parseInt(m.group()), Tuple.pair(row, col));
                col++;
            }
        }

        public long computeBoardScore(int prev) {
            int sum = 0;
            for(int i : map.keySet()) {
                sum += i;
            }
            return sum * prev;
        }
    }
}