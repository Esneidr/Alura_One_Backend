package com.aluracursos.audio.main;

import com.aluracursos.audio.models.Myfavorites;
import com.aluracursos.audio.models.Podcast;
import com.aluracursos.audio.models.Song;

public class Main {
    public static void main(String[] args) {
        Song song = new Song();
        Podcast podcast = new Podcast();
        Myfavorites favorites = new Myfavorites();

        song.setTitle("Good kid");
        song.setSinger("kendrick lamar");

        podcast.setHost("Esneider cordoba");
        podcast.setTitle("La vida es dura, pero bella");

        for (int i = 0; i < 984; i++) {
            song.likes();
        }
        for (int i = 0; i < 1030; i++) {
            song.plays();
        }

        for (int i = 0; i < 674; i++) {
            podcast.likes();
        }
        for (int i = 0; i < 1850; i++) {
            podcast.plays();
        }

        System.out.printf("Total de reproduciones para %s: %d%n", song.getTitle(), song.getTotalPlays());
        System.out.printf("Ttal de me gustas: %d%n", song.getTotalLikes());

        favorites.add(song);
        favorites.add(podcast);
    }
}
