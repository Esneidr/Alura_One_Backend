package com.aluracursos.audio.models;

public class Myfavorites {
    public void add (Audio audio) {
        if (audio.getRating() >= 8) {
            System.out.printf("%s es uno de los favoritos del momento.%n", audio.getTitle());
        } else {
            System.out.printf("%s tambien es uno de los favoritos%n", audio.getTitle());
        }
    }
}
