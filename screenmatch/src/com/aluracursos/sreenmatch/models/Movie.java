package com.aluracursos.sreenmatch.models;

import com.aluracursos.screenmatch.calculations.Rating;

import java.time.Year;

public class Movie extends Title implements Rating {

    private String director;

    public Movie(String name, Year releaseDate) {
        super(name, releaseDate);
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public int getRating() {
        return (int) (averageRating() / 2);
    }

    @Override
    public String toString() {
        return "Pelicula: " + this.getTitle() + " (" + getReleaseDate() + ")";
    }
}
