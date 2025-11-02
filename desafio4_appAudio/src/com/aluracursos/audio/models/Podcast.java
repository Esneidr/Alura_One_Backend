package com.aluracursos.audio.models;

public class Podcast extends Audio {
    private String host;
    private String description;

    @Override
    public int getRating() {
        if (getTotalPlays() >= 1000) {
            return 9;
        } else {
            return 2;
        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
