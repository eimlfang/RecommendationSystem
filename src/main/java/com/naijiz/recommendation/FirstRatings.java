package com.naijiz.recommendation;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FirstRatings {
    public List<Movie> loadMovies(String filename){
        List<CSVRecord> records = getCsvRecords(filename);
        List<Movie> movies = new ArrayList<>(records.size());
        for (CSVRecord record : records) {
//            System.out.println(record);

            if (record.getRecordNumber() == 1) {
                continue;
            }
            Movie movie = new Movie(
                    record.get(0).trim(),
                    record.get(1).trim(),
                    record.get(2).trim(),
                    record.get(4).trim(),
                    record.get(5).trim(),
                    record.get(3).trim(),
                    record.get(7).trim(),
                    Integer.parseInt(record.get(6)));
            movies.add(movie);
        }
        return movies;
    }

    public List<Rater> loadRaters(String filename) {
        List<CSVRecord> records = getCsvRecords(filename);

        Map<String, Rater> raterMap = new HashMap<>();

        for (CSVRecord record : records) {
            if (record.getRecordNumber() == 1) {
                continue;
            }
            String raterId = record.get(0);
            raterMap.putIfAbsent(raterId, new Rater(raterId));

            Rater rater = raterMap.get(raterId);
            rater.addRating(record.get(1), Double.parseDouble(record.get(2)));
            raterMap.put(raterId, rater);
        }

        return new ArrayList<>(raterMap.values());
    }

    private List<CSVRecord> getCsvRecords(String filename) {
        // process record from csv file
        Reader reader = null;
        try {
            reader = new FileReader(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        CSVParser parser = null;
        try {
            parser = CSVParser.parse(new File(filename), StandardCharsets.UTF_8, CSVFormat.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<CSVRecord> records = null;
        try {
            records = parser.getRecords();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

}
