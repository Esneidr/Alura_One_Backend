package com.aluracursos.screenmatch.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Entity
@Table(name = "episodes")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Integer seasonNumber;
    private String title;
    private Integer episodeNumber;
    private Double rating;
    private LocalDate releaseDate;

    @ManyToOne
    private Serie serie;

    public Episode() {}

    public Episode(Integer number, EpisodeInfo d) {
        this.seasonNumber = number;
        this.title = d.title();
        this.episodeNumber = d.numberEpisode();

        try {
            this.rating = Double.valueOf(d.evaluation());
        } catch (NumberFormatException e) {
            this.rating = 0.0;
        }

        try {
            this.releaseDate = LocalDate.parse(d.releaseDate());
        } catch (DateTimeParseException e) {
            this.releaseDate = null;
        }
    }

    public Long getIdEpisode() {
        return Id;
    }

    public void setIdEpisode(Long id) {
        Id = id;
    }

    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    @Override
    public String toString() {
        return
                "temporada=" + seasonNumber +
                ", titulo='" + title + '\'' +
                ", episodio=" + episodeNumber +
                ", Evaluación=" + rating +
                ", Lanzamiento=" + releaseDate;
    }
}
