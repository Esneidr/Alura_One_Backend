package com.aluracursos.screenmatch.models;

import com.aluracursos.screenmatch.enums.Category;
import jakarta.persistence.*;

import javax.annotation.processing.Generated;
import java.util.List;
import java.util.OptionalDouble;

@Entity
@Table(name = "series")
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdSerie;

    @Column(unique = true)
    private String title;

    @Enumerated(EnumType.STRING)
    private Category genre;
    private String actors;
    private Integer totalSeasons;
    private Double rating;
    private String poster;
    private String synopsis;

    @Transient
    private List<Episode> episodes;

    public Serie() {}

    public Serie(SeriesData seriesData) {
        this.title = seriesData.title();
        this.genre = Category.fromString(seriesData.genre().split(",")[0].trim());
        this.actors = seriesData.actors();
        this.totalSeasons = seriesData.totalSeasons();
        this.rating = OptionalDouble.of(Double.valueOf(seriesData.rating())).orElse(0);
        this.poster = seriesData.poster();
        this.synopsis = seriesData.synopsis();
    }

    public Long getIdSerie() {
        return IdSerie;
    }

    public void setIdSerie(Long idSerie) {
        IdSerie = idSerie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getGenre() {
        return genre;
    }

    public void setGenre(Category genre) {
        this.genre = genre;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public Integer getTotalSeasons() {
        return totalSeasons;
    }

    public void setTotalSeasons(Integer totalSeasons) {
        this.totalSeasons = totalSeasons;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    @Override
    public String toString() {
        return
                "genre=" + genre +
                ", title='" + title + '\'' +
                ", actors='" + actors + '\'' +
                ", totalSeasons=" + totalSeasons +
                ", rating=" + rating +
                ", poster='" + poster + '\'' +
                ", synopsis='" + synopsis + '\'';
    }
}
