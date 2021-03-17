package com.naijiz.recommendation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SecondRatings {
    private List<Movie> myMovies;
    private List<Rater> myRaters;

    public SecondRatings() {
        // default constructor
//        this("ratedmoviesfull.csv", "ratings.csv");
        this("ratedmovies_short.csv", "ratings_short.csv");
    }

    public SecondRatings(String movieFilename, String ratingFilename) {
        FirstRatings firstRatings = new FirstRatings();
        String basePath = "C:\\Workspace\\github\\eimlfang\\coursera_duke\\src\\main\\resources\\data\\";
        List<Movie> movies = firstRatings.loadMovies(basePath + movieFilename);
        List<Rater> raters = firstRatings.loadRaters(basePath + ratingFilename);
        this.myMovies = movies;
        this.myRaters = raters;
    }

    public int getMovieSize() {
        return myMovies.size();
    }

    public int getRaterSize() {
        return myRaters.size();
    }

    public List<Rating> getAverageRatings(int minimalRaters) {
        double ratingSum = 0;
        List<Rating> ratings = new ArrayList<>();
        for (Movie myMovie : myMovies) {
            double averageRating = getAverageByID(myMovie.getID(), minimalRaters);
            if (averageRating > 0.0) {
                Rating rating = new Rating(myMovie.getID(), averageRating);
                ratings.add(rating);
            }
        }

        List<Rating> collect = ratings.stream().sorted().collect(Collectors.toList());
        return collect;
    }

    public String getTitle(String mid) {

        for (Movie myMovie : myMovies) {
            if (myMovie.getID().equals(mid)) {
                return myMovie.getTitle();
            }
        }

        return "ID was not found";
    }

    public String getID(String title) {

        for (Movie movie : myMovies) {
            if (movie.getTitle().equals(title)) {
                return movie.getID();
            }
        }

        return "NO SUCH TITLE";
    }

    private double getAverageByID(String movieId, int minimalRaters) {
        List<Rater> raters = myRaters.stream().filter(rater -> {
            return rater.hasRating(movieId);
        }).collect(Collectors.toList());
        if (raters.size() < minimalRaters) {
            return 0.0;
        }

        double sum = raters.stream().mapToDouble(r -> r.getRating(movieId)).sum();

        return sum / raters.size();
    }
}
