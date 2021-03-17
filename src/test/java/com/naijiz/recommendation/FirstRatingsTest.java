package com.naijiz.recommendation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class FirstRatingsTest {
    FirstRatings firstRatings;

    @Before
    public void before() {
        firstRatings = new FirstRatings();
    }

    @After
    public void after() {
        firstRatings = null;
    }

    @Test
    public void loadMovies() {
        List<Movie> movies = firstRatings.loadMovies("C:\\Workspace\\github\\eimlfang\\coursera_duke\\src\\main\\resources\\data\\ratedmoviesfull.csv");

        movies.forEach(System.out::println);

        System.out.println("Total Movie: " + movies.size());
    }

    @Test
    public void determineGenres() {
        String filename = "C:\\Workspace\\github\\eimlfang\\coursera_duke\\src\\main\\resources\\data\\ratedmoviesfull.csv";
        List<Movie> movies = firstRatings.loadMovies(filename);

        String genres = "Comedy";

        long sum = movies.stream().filter(m -> Arrays.stream(m.getGenres().split(", "))
                .collect(Collectors.toList())
                .contains(genres)).count();

//        int sum = movies.stream().mapToInt(m -> {
//            if (Arrays.stream(m.getGenres().split(", ")).collect(Collectors.toList()).contains(genres)) {
//                return 1;
//            } else {
//                return 0;
//            }
//        }).sum();

        System.out.println(sum + " movies include " + genres);
    }

    @Test
    public void determineMinutes() {
        String filename = "C:\\Workspace\\github\\eimlfang\\coursera_duke\\src\\main\\resources\\data\\ratedmoviesfull.csv";
        List<Movie> movies = firstRatings.loadMovies(filename);

        int minutes = 150;

        long sum = movies.stream().filter(m -> m.getMinutes() > minutes).count();

//        int sum = movies.stream().mapToInt(m -> m.getMinutes() > minutes ? 1 : 0).sum();

        System.out.println(sum + " movies greater than " + minutes + " in length");
    }

    @Test
    public void determineMaxNumberOfDirector() {
        String filename = "C:\\Workspace\\github\\eimlfang\\coursera_duke\\src\\main\\resources\\data\\ratedmoviesfull.csv";
        List<Movie> movies = firstRatings.loadMovies(filename);

        int max = movies.stream()
                .mapToInt(m -> m.getDirector().split(", ").length)
                .max().orElse(0);

//        int max = movies.stream().filter(movie -> movie.getDirector().split(", ").length == 1).collect(Collectors.toList()).size();

        System.out.println("max number of director of movies are " + max);

//        movies.stream().
        Map<String, Integer> directorMovies = new HashMap<>();

        for (Movie movie : movies) {
            String[] directors = movie.getDirector().split(", ");
            for (String director : directors) {
                if (directorMovies.containsKey(director)) {
                    directorMovies.put(director, directorMovies.get(director) + 1);
                } else {
                    directorMovies.put(director, 1);
                }
            }
        }

        System.out.println(directorMovies);

        int maxCount = 0;
        String directorOfMaxCount = "";

        Set<String> dm = directorMovies.keySet();
        for (String s : dm) {
            Integer integer = directorMovies.get(s);
            if (integer > maxCount) {
                maxCount = integer;
                directorOfMaxCount = s;
            }
        }

        System.out.println("director " + directorOfMaxCount + " has the most " + maxCount + " movies");
    }

    @Test
    public void loadRaters() {
        String filename = "C:\\Workspace\\github\\eimlfang\\coursera_duke\\src\\main\\resources\\data\\ratings.csv";
        List<Rater> raters = firstRatings
                .loadRaters(filename);

        System.out.println("total number of raters " + raters.size());

        raters.forEach(System.out::println);
    }

    @Test
    public void testParticularRater() {
        String filename = "C:\\Workspace\\github\\eimlfang\\coursera_duke\\src\\main\\resources\\data\\ratings.csv";
        List<Rater> raters = firstRatings
                .loadRaters(filename);
        String raterId = "193";
        List<Rater> collect = raters.stream().filter(r -> {
            return r.getID().equals(raterId);
        }).collect(Collectors.toList());

        System.out.println(collect.get(0));
    }

    @Test
    public void testFindMaximumNumberOfRatings() {
        String filename = "C:\\Workspace\\github\\eimlfang\\coursera_duke\\src\\main\\resources\\data\\ratings.csv";
        List<Rater> raters = firstRatings
                .loadRaters(filename);
        int max = raters.stream().mapToInt(Rater::numRatings).max().orElse(0);
        System.out.println("max = " + max);

        List<Rater> ratersAfterFilter = raters.stream().filter(rater -> rater.numRatings() >= max).collect(Collectors.toList());
        ratersAfterFilter.forEach(System.out::println);
    }

    @Test
    public void testRatingOfMovie() {
        String filename = "C:\\Workspace\\github\\eimlfang\\coursera_duke\\src\\main\\resources\\data\\ratings.csv";
        List<Rater> raters = firstRatings
                .loadRaters(filename);

        List<Rater> l = new ArrayList<>();

        String movie = "1798709";

        for (Rater rater : raters) {
            double rating = rater.getRating(movie);
            if (rating != -1) {
                l.add(rater);
            }
        }
        System.out.println("rating: " + l.size());
        l.forEach(System.out::println);
    }

    @Test
    public void testHowManyDifferentMoviesBeenRated() {
        String filename = "C:\\Workspace\\github\\eimlfang\\coursera_duke\\src\\main\\resources\\data\\ratings.csv";
        List<Rater> raters = firstRatings
                .loadRaters(filename);
        List<String> movies = new ArrayList<>();

        for (Rater rater : raters) {
            ArrayList<String> itemsRated = rater.getItemsRated();
            movies.addAll(itemsRated);
        }

//        movies.forEach(System.out::println);
        Set<String> s = new HashSet<>(movies);

        System.out.println("total " + s.size());
//        s.forEach(System.out::println);
    }

}