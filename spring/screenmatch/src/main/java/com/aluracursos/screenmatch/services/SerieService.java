package com.aluracursos.screenmatch.services;

import com.aluracursos.screenmatch.dto.EpisodeDTO;
import com.aluracursos.screenmatch.dto.SerieDTO;
import com.aluracursos.screenmatch.enums.Category;
import com.aluracursos.screenmatch.models.Serie;
import com.aluracursos.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private SerieRepository repository;

    public List<SerieDTO> getAllSeries() {
        return convertData(repository.findAll());
    }

    public List<SerieDTO> getTop5series() {
        return convertData(repository.findTop5ByOrderByRatingDesc());
    }

    public List<SerieDTO> getLatestReleases() {
        return  convertData(repository.latestReleases());
    }

    public SerieDTO getId(Long id) {
        Optional<Serie> serie = repository.findById(id);

        if (serie.isPresent()) {
            Serie s = serie.get();
            return new SerieDTO(s.getId(), s.getTitle(), s.getTotalSeasons(), s.getRating(), s.getPoster(),
                    s.getGenre(), s.getActors(), s.getSynopsis());
        }

        return null;
    }

    public List<EpisodeDTO> getAllSeasons(Long id) {
        Optional<Serie> serie = repository.findById(id);

        if (serie.isPresent()) {
            Serie s = serie.get();
            return s.getEpisodes().stream()
                    .map(e -> new EpisodeDTO(e.getSeasonNumber(), e.getTitle(),
                            e.getEpisodeNumber())).collect(Collectors.toList());
        }
        return null;
    }

    public List<EpisodeDTO> getSeasonsByNumber(Long id, Long numeroTemporada) {
        return repository.getSeasonsByNumber(id, numeroTemporada).stream()
                .map(e -> new EpisodeDTO(e.getSeasonNumber(), e.getTitle(),
                        e.getEpisodeNumber())).collect(Collectors.toList());
    }

    public List<SerieDTO> getSeriesByGenre(String nameGenre) {
        Category category = Category.fromSpanish(nameGenre);

        return convertData(repository.findByGenre(category));
    }

    private List<SerieDTO> convertData(List<Serie> serieList) {
        return serieList.stream()
                .map(s -> new SerieDTO(s.getId(), s.getTitle(), s.getTotalSeasons(), s.getRating(), s.getPoster(),
                        s.getGenre(), s.getActors(), s.getSynopsis())).collect(Collectors.toList());
    }
}
