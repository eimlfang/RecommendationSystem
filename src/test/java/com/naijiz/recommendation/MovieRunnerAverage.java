package com.naijiz.recommendation;

import org.junit.Test;

import java.util.List;

public class MovieRunnerAverage {

    @Test
    public void printAverageRatings() {
        SecondRatings secondRatings = new SecondRatings();
        System.out.println("movie size: " + secondRatings.getMovieSize());
        System.out.println("rater size: " + secondRatings.getRaterSize());
        List<Rating> ratings = secondRatings.getAverageRatings(3);
        ratings.forEach(rating -> {
            System.out.println(rating.getValue() + " " + secondRatings.getTitle(rating.getItem()));
        });
    }

    @Test
    public void getAverageRatingOneMovie() {
        SecondRatings secondRatings = new SecondRatings();

        String title = "The Godfather";
        String id = secondRatings.getID(title);

        List<Rating> averageRatings = secondRatings.getAverageRatings(2);

        for (Rating rating : averageRatings) {
            if (rating.getItem().equals(id)) {
                System.out.println(rating.getItem() + ", "
                + secondRatings.getTitle(rating.getItem()) + ", "
                + rating.getValue());
                break;
            }
        }
    }
}
