package com.aluracursos.audio.models;

public class Song extends Audio {
    private String album;
    private String singer;
    private String genre;

    @Override
    public int getRating() {
        if (getTotalLikes() > 10000) {
            return 8;
        } else {
            return 4;
        }
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
