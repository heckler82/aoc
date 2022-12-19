package com.foley.aoc.year2022;

import com.foley.aoc.util.Daily;
import com.foley.aoc.util.Tuple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Solutions for day 2
 *
 * @author Evan Foley
 * @version 2 Dec 2022
 */
public class Day2 extends Daily {
    private final Map<Character, Integer> shapeKey;
    private final Map<Character, Map<Character, Integer>> key;
    private final List<Tuple<Character, Character>> matches;

    /**
     * Creates a new daily
     *
     * @param year     The year
     * @param fileName The name of the input file
     */
    public Day2(int year, String fileName) {
        super(year, fileName);
        shapeKey = new HashMap<>();
        shapeKey.put('X', 1);
        shapeKey.put('Y', 2);
        shapeKey.put('Z', 3);

        key = new HashMap<>();
        var choice = new HashMap<Character, Integer>();
        choice.put('X', 3);
        choice.put('Y', 6);
        choice.put('Z', 0);
        key.put('A', choice);

        choice = new HashMap<Character, Integer>();
        choice.put('X', 0);
        choice.put('Y', 3);
        choice.put('Z', 6);
        key.put('B', choice);

        choice = new HashMap<Character, Integer>();
        choice.put('X', 6);
        choice.put('Y', 0);
        choice.put('Z', 3);
        key.put('C', choice);

        matches = new ArrayList<>();
        for(String s : input){
            matches.add(Tuple.pair(s.charAt(0), s.charAt(2)));
        }
    }

    @Override
    protected void task1() {
        long result = 0L;
        for(var m : matches) {
            char opponent = m.getFirst();
            char mine = m.getSecond();
            result += key.get(opponent).get(mine);
            result += shapeKey.get(mine);
        }
        System.out.printf("My total score is %d\n", result);
    }

    @Override
    protected void task2() {
        long result = 0L;
        for(var m : matches) {
            char opponent = m.getFirst();
            char need = m.getSecond();
            int ans = (shapeKey.get(need) - 1) * 3;
            var needed = key.get(opponent).entrySet().stream().filter(e -> e.getValue().equals(ans)).map(Map.Entry::getKey).findFirst();
            result += shapeKey.get(needed.orElse('X'));
            result += ans;
        }
        System.out.printf("My total score is %d\n", result);
    }
}
