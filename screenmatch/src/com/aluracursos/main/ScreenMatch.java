package com.aluracursos.main;

import com.aluracursos.screenmatch.calculations.RecommendationFilter;
import com.aluracursos.screenmatch.calculations.TimeCalculator;
import com.aluracursos.sreenmatch.models.Episode;
import com.aluracursos.sreenmatch.models.Movie;
import com.aluracursos.sreenmatch.models.Series;

import java.time.Year;
import java.util.ArrayList;

public class ScreenMatch {
    public static void main(String[] args) {
        var movie = new Movie("Encanto", 2021);
        var movie1 = new Movie("Matrix", 1998);
        var movie2 = new Movie("EL señor de los anillos", 2001);
        var serie = new Series("La casa del dragón", 2022);

        TimeCalculator calculator = new TimeCalculator();
        RecommendationFilter filter = new RecommendationFilter();
        Episode episode = new Episode();

        // película
        movie.setDurationInMinutes(120);
        movie.setIncludedInPlan(true);

        movie.rating(8.3);
        movie.rating(6.05);
        movie.rating(9.3);

        movie.technicalInfo();
        System.out.printf("Rating: %.2f%n", movie.averageRating());

        // película 2
        movie1.setDurationInMinutes(180);

        // serie
        serie.setSeason(1);
        serie.setMinutesPerEpisode(50);
        serie.setEpisodesPerSeason(10);
        serie.technicalInfo();

        // Calculo
        calculator.includes(movie);
        calculator.includes(movie1);
        calculator.includes(serie);
        System.out.printf("Total tiempo para ver tus titulos: %d min.%n", calculator.getTimeTotal());

        // filtro de recomendación
        filter.filer(movie);

        // Eposodio
        episode.setNumber(1);
        episode.setName("La casa Targaryen");
        episode.setSerie(serie);
        episode.setTotalViews(50);
        filter.filer(episode);

        // película 3
        movie2.setDurationInMinutes(180);

        var movies = new ArrayList<Movie>();
        movies.add(movie);
        movies.add(movie1);
        movies.add(movie2);
    }
}
