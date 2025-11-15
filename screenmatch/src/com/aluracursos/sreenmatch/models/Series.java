package com.aluracursos.sreenmatch.models;

public class Series extends Title {
    private int season;
    private int episodesPerSeason;
    private int minutesPerEpisode;

    public Series(String name, int releaseDate) {
        super(name, releaseDate);
    }
    @Override
    public int getDurationInMinutes() {
        return  season * episodesPerSeason * minutesPerEpisode;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getEpisodesPerSeason() {
        return episodesPerSeason;
    }

    public void setEpisodesPerSeason(int episodesPerSeason) {
        this.episodesPerSeason = episodesPerSeason;
    }

    public int getMinutesPerEpisode() {
        return minutesPerEpisode;
    }

    public void setMinutesPerEpisode(int minutesPerEpisode) {
        this.minutesPerEpisode = minutesPerEpisode;
    }

    @Override
    public String toString() {
        return "Serie: " + this.getTitle() + " (" + getReleaseDate() + ")";
    }
}
