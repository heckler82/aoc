package com.foley.aoc.year2021;

import com.foley.aoc.util.Daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
            inputs.sort((String s1, String s2) -> (s1.length() == 5 && s2.length() == 6) ? s2.length() - s1.length() : (s1.length() == 6 && s2.length() == 5 ? s2.length() - s1.length() : s1.length() - s2.length()));
            for(String in : inputs) {
                char[] arr = in.toCharArray();
                var set = createCharSet(arr);
                switch(in.length()) {
                    case 2: // 1
                        cache.put(1, set);
                        break;
                    case 3: // 7
                        cache.put(7, set);
                        break;
                    case 4: // 4
                        cache.put(4, set);
                        break;
                    case 5: // 2, 3, or 5
                        if(set.containsAll(cache.get(1)) && set.containsAll(cache.get(7))) {
                            cache.put(3, set);
                        } else {
                            // 2 or 5
                            if(cache.get(6).containsAll(set)) {
                                cache.put(5, set);
                            } else {
                                cache.put(2, set);
                            }
                        }
                        break;
                    case 6: // 0, 6, or 9
                        if(set.containsAll(cache.get(1)) && set.containsAll(cache.get(7))) {
                            if(set.containsAll(cache.get(4))) {
                                cache.put(9, set);
                            } else {
                                cache.put(0, set);
                            }
                        } else {
                            cache.put(6, set);
                        }
                        break;
                    case 7: // 8
                        cache.put(8, set);
                }
            }
            String[] output = s.split(" \\| ")[1].split("\\s+");
            StringBuilder decode = new StringBuilder();
            for(String o : output) {
                if(o.length() == 2 || o.length() == 3 || o.length() == 4 || o.length() == 7) {
                    count++;
                }
                for(var key : cache.keySet()) {
                    if(cache.get(key).size() == o.length()) {
                        var oSet = createCharSet(o.toCharArray());
                        oSet.retainAll(cache.get(key));
                        if(oSet.size() == o.length()) {
                            decode.append(key);
                            break;
                        }
                    }
                }
            }
            decoded.add(Integer.parseInt(decode.toString()));
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

    private Set<Character> createCharSet(char[] arr) {
        return IntStream.range(0, arr.length)
                .mapToObj(i -> arr[i])
                .collect(Collectors.toSet());
    }
}