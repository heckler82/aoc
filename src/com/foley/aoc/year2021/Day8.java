package com.foley.aoc.year2021;

import com.foley.aoc.util.Daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.*;

/**
 * Solutions for day 8
 *
 * @author Evan Foley
 * @version 08 Dec 2021
 */
public class Day8 extends Daily {
    List<Integer> decoded;

    /**
     * Creates a new daily
     *
     * @param fileName The name of the input file
     */
    public Day8(String fileName) {
        super(fileName);
        decoded = new ArrayList<>();
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        int count = 0;
        for(String s : input) {
            var cache = new HashMap<Integer, Set<Character>>();
            var inputs = Arrays.asList(s.split(" \\| ")[0].split("\\s+"));
            Decoder decoder = new Decoder();
            decoder.seed(inputs);
            var output = Arrays.asList(s.split(" \\| ")[1].split("\\s+"));
            decoded.add(decoder.decode(output));

            for(String o : output) {
                if(o.length() == 2 || o.length() == 3 || o.length() == 4 || o.length() == 7) {
                    count++;
                }
            }
        }
        System.out.printf("The digits 1, 4, 7, and 8 appear %d times\n", count);
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        long sum = 0L;
        for(int i : decoded) {
            sum += i;
        }
        System.out.printf("The final sum is %d\n", sum);
    }

    /**
     * Creates a new decoder
     */
    private class Decoder {
        private Map<Integer, Set<Character>> digiPatterns;

        public Decoder() {
            digiPatterns = new HashMap<>();
        }

        /**
         * Seeds the decoder with values for unscrambling digits
         *
         * @param seeds the seed values
         */
        public void seed(List<String> seeds) {
            seeds.sort((String s1, String s2) -> s1.length() - s2.length());
            for(String s : seeds) {
                var set = createCharSet(s.toCharArray());
                switch(s.length()) {
                    case 2: // 1
                        digiPatterns.put(1, set);
                        break;
                    case 3: // 7
                        digiPatterns.put(7, set);
                        break;
                    case 4: // 4
                        digiPatterns.put(4, set);
                        break;
                    case 5: // 2, 3, or 5
                        if(set.containsAll(digiPatterns.get(1))) {
                            digiPatterns.put(3, set);
                            break;
                        }
                        var key = new HashSet<Character>(digiPatterns.get(4));
                        key.removeAll(digiPatterns.get(1));
                        if(set.containsAll(key)) {
                            digiPatterns.put(5, set);
                            break;
                        }
                        digiPatterns.put(2, set);
                        break;
                    case 6: // 0, 6, or 9
                        if(!set.containsAll(digiPatterns.get(1))) {
                            digiPatterns.put(6, set);
                            break;
                        }
                        key = new HashSet<Character>(digiPatterns.get(4));
                        key.removeAll(digiPatterns.get(1));
                        if(set.containsAll(key)) {
                            digiPatterns.put(9, set);
                            break;
                        }
                        digiPatterns.put(0, set);
                        break;
                    case 7: // 8
                        digiPatterns.put(8, set);
                }
            }
        }

        /**
         * Decodes a list of input strings into a single integer value
         *
         * @param decode the input strings
         * @return the integer representation of the input strings
         */
        public int decode(List<String> decode) {
            StringBuilder sb = new StringBuilder();
            for(String s : decode) {
                for(var key : digiPatterns.keySet()) {
                    if(digiPatterns.get(key).size() == s.length()) {
                        var oSet = createCharSet(s.toCharArray());
                        oSet.retainAll(digiPatterns.get(key));
                        if(oSet.size() == s.length()) {
                            sb.append(key);
                            break;
                        }
                    }
                }
            }
            return Integer.parseInt(sb.toString());
        }

        /**
         * Creates a set made up of the unique characters of the input array
         *
         * @param arr the input array
         * @return a set of unique characters from the input array
         */
        private Set<Character> createCharSet(char[] arr) {
            return IntStream.range(0, arr.length)
                    .mapToObj(i -> arr[i])
                    .collect(Collectors.toSet());
        }
    }
}