package com.foley.aoc.util.functions;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a range of integers that can be either continuous or non-continuous
 *
 * @author Evan Foley
 * @version 28 Jan 2020
 */
public class Range {
  private int minInclusive;
  private int maxInclusive;
  private List<Range> exclusions;
  
  /**
  * Creates a new range
  * 
  * @param min The minimum value of the range (inclusive)
  * @param max The maximum value of the range (inclusive)
  */
  public Range(int min, int max) {
    if(max < min) {
      throw new IllegalArgumentException("Max must be greater than min for a valid range");
    }
    minInclusive = min;
    maxInclusive = max;
    exclusions = new ArrayList<>();
  }
  
  /**
  * Determines if a value falls into the defined scope of this range
  * 
  * @param val The value
  * @return True if the value is in the scope of the range
  */
  public boolean inRange(int val) {
    // If the value does not fall between the minimum or maximum, return false
    if(val < min || val > max) {
      return false;
    }
    
    // Ensure value does not fall into an excluded range
    for(Range r : exclusions) {
      if(r.inRange(val)) {
        return false;
      }
    }
    // Value must be in range
    return true;
  }
  
  /**
  * Excludes values from this range
  * 
  * @param min The minimum value to exclude (inclusive)
  * @param max The maximum value to exclude (inclusive)
  */
  public void exclude(int min, int max) {
    // If the minimum is greater than the defined maximum, or the maximum is less than the defined minimum, return
    if(min > this.max || max < this.min) {
      return;
    }
    // Create the range and add it to the exclusions list
    //Range r = new Range(Math.max(min, this.min), Math.min(max, this.max));
    Range r = new Range(min, max);
    exclusions.add(r);
  }
  
  /**
  * Excludes values from this range
  * 
  * @param r The range of values to exclude
  */
  public void exclude(Range r) {
    // If the minimum is greater than the defined maximum, or the maximum is less than the defined minimum, return
    if(r.min > max || r.max < min) {
      return;
    }
    exclusions.add(r);
  }
  
  /**
  * Includes values from this range
  * 
  * @param min The minimum value to include (inclusive)
  * @param max The maximum value to include (inclusive)
  */
  public void include(int min, int max) {}
  
  /**
  * Includes values from this range
  * 
  * @param r The range of values to include
  */
  public void include(int min, int max) {}
  
  /**
  * The number of valid values in the range
  * 
  * @return The number of valued values in the range
  */
  public int rangeSize() {
    int total = (max - min + 1);
    // Subtract the exclusions
    if(!exclusions.isEmpty()) {
      for(Range r : exclusions) {
        total -= r.rangeSize();
      }
    }
    return total;
  }
  
  /**
  * Determines if this range is continuous and contains no exclusions
  * 
  * @return True if this range contains no exclusions
  */
  public boolean isContinuous() {
    return exclusions.isEmpty();
}
