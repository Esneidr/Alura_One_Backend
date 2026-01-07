package com.aluracursos.screenmatch.repository;

import com.aluracursos.screenmatch.enums.Category;
import com.aluracursos.screenmatch.models.Episode;
import com.aluracursos.screenmatch.models.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie,Long> {
    Optional<Serie> findByTitleContainsIgnoreCase(String nameSerie);
    List<Serie> findTop5ByOrderByRatingDesc();
    List<Serie> findByGenre(Category category);
    //List<Serie> findByTotalSeasonsLessThanEqualAndRatingGreaterThanEqual(int totalSeasons, Double rating);
    @Query("SELECT s FROM Serie s WHERE s.totalSeasons <= :maxSeasons AND s.rating >= :minRating")
    List<Serie> seireSeansonsAndRating(int maxSeasons, Double minRating);

    @Query("SELECT e FROM Serie s JOIN s.episodes e WHERE e.title ILIKE %:nameEpisode%")
    List<Episode> episodesByName(String nameEpisode);

    @Query("SELECT e FROM Serie s JOIN s.episodes e WHERE s = :serie ORDER BY e.rating DESC LIMIT 5")
    List<Episode> top5Episodes(Serie serie);
}
