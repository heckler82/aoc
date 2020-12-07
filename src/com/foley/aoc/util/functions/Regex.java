package com.foley.aoc.util.functions;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* Simple functions for working with regular expressions
*/
public class Regex {
  /**
  * Determines if a string can be matched against a pattern
  * 
  * @param pattern The pattern to match against
  * @param match The string to test
  * @return True if the string matches against the pattern
  */
  public static boolean canMatchPattern(String pattern, String match) {
    Pattern p = pattern.compile(pattern);
    return p.matcher(match).find();
  }
  
  /**
  * Determines if a string is a 1:1 match against a pattern
  * 
  * @param pattern The pattern to match against
  * @param match The string to test
  * @return True if the string completely matches against the pattern
  */
  public static boolean completelyMatchPattern(String pattern, String match) {
    Pattern p = pattern.compile(pattern);
    return p.matcher(match).matches();
  }
  
  /**
  * Gets the matching engine for a pattern against a string
  * 
  * @param pattern The pattern
  * @param match The string
  * @return The matching engine for string and pattern
  */
  public static Matcher getMatcher(String pattern, String match) {
    Pattern p = pattern.compile(pattern);
    return p.matcher();
  }
  
  /**
  * Gets a list of the strings contained within match that match against a pattern
  * 
  * @param pattern The pattern to match against
  * @param match The string to test
  * @return A list of all the matches
  */
  public List<String> getMatches(String pattern, String match) {
    Matcher m = getMatcher(pattern, match);
    List<String> list = new ArrayList<>();
    while(m.find()) {
      for(int i = 0; i < m.groupCount(); i++) {
        list.add(m.group(i + 1));
      }
    }
    return list;
  }
}
