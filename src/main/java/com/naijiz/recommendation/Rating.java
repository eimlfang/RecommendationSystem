package com.naijiz.recommendation;

/**
 * An immutable passive data object (PDO) to represent the rating data
 * @author eimlf
 */
public class Rating implements Comparable<Rating> {
    /**
     * description of the item being rated (for this assignment you should use the IMDB ID of the movie being rated)
     */
    private String item;
    /**
     * a double of the actual rating
     */
    private double value;

    public Rating (String anItem, double aValue) {
        item = anItem;
        value = aValue;
    }

    // Returns item being rated
    public String getItem () {
        return item;
    }

    // Returns the value of this rating (as a number so it can be used in calculations)
    public double getValue () {
        return value;
    }

    // Returns a string of all the rating information
    @Override
    public String toString () {
        return "[" + getItem() + ", " + getValue() + "]";
    }

    @Override
    public int compareTo(Rating other) {
        if (value < other.value) return -1;
        if (value > other.value) return 1;
        
        return 0;
    }
}
