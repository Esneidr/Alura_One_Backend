package com.aluracursos.main;

import com.aluracursos.sreenmatch.models.Movie;
import com.aluracursos.sreenmatch.models.Series;
import com.aluracursos.sreenmatch.models.Title;

import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Lists {
    public static void main(String[] args) {
        var movie = new Movie("Encanto", 2021);
        movie.rating(9);
        var movie1 = new Movie("Matrix", 1998);
        movie1.rating(6);
        var movie2 = new Movie("EL señor de los anillos", 2001);
        movie2.rating(10);
        var serie = new Series("La casa del dragón", 2022);


        var list = new ArrayList<Title>();
        list.add(movie);

        list.add(movie1);
        list.add(movie2);
        list.add(serie);

        for (Title item: list) {
            System.out.println(item.getTitle());
            if (item instanceof Movie movies) {
                System.out.println(movies.getRating());
            }
        }

        List<String> artistList = new ArrayList<>();
        artistList.add("Penélope Cruz");
        artistList.add("Antonio Banderas");
        artistList.add("Ricardo Darin");

        Collections.sort(artistList);
        Collections.sort(list);

        System.out.println(list);

        list.sort(Comparator.comparing(Title::getReleaseDate));


    }
}
