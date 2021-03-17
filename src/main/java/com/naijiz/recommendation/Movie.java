package com.naijiz.recommendation;

/**
 * An immutable passive data object (PDO) to represent item data
 * @author eimlf
 */
public class Movie {
    private String id;
    /**
     * movie’s title
     */
    private String title;
    /**
     * representing the year
     */
    private int year;
    /**
     * one or more genres separated by commas
     */
    private String genres;
    /**
     * one or more directors of the movie separated by commas
     */
    private String director;
    /**
     * one or more countries the film was made in, separated by comma
     */
    private String country;
    /**
     * a link to an image of the movie poster if one exists, or “N/A” if no poster exists
     */
    private String poster;
    /**
     * length of the movie
     */
    private int minutes;

    public Movie(String anID, String aTitle, String aYear, String theGenres) {
        // just in case data file contains extra whitespace
        id = anID.trim();
        title = aTitle.trim();
        year = Integer.parseInt(aYear.trim());
        genres = theGenres;
    }

    public Movie(String anID, String aTitle, String aYear, String theGenres, String aDirector,
                 String aCountry, String aPoster, int theMinutes) {
        // just in case data file contains extra whitespace
        id = anID.trim();
        title = aTitle.trim();
        year = Integer.parseInt(aYear.trim());
        genres = theGenres;
        director = aDirector;
        country = aCountry;
        poster = aPoster;
        minutes = theMinutes;
    }

    // Returns ID associated with this item
    public String getID() {
        return id;
    }

    // Returns title of this item
    public String getTitle() {
        return title;
    }

    // Returns year in which this item was published
    public int getYear() {
        return year;
    }

    // Returns genres associated with this item
    public String getGenres() {
        return genres;
    }

    public String getCountry() {
        return country;
    }

    public String getDirector() {
        return director;
    }

    public String getPoster() {
        return poster;
    }

    public int getMinutes() {
        return minutes;
    }

    /**
     * Returns a string of the item's information
     * @return
     */
    @Override
    public String toString() {
        String result = "Movie [id=" + id + ", title=" + title + ", year=" + year;
        result += ", genres= " + genres + "]";
        return result;
    }
}
