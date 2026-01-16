package com.aluracursos.screenmatch.controllers;

import com.aluracursos.screenmatch.dto.EpisodeDTO;
import com.aluracursos.screenmatch.dto.SerieDTO;
import com.aluracursos.screenmatch.services.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService serieService;

    @GetMapping()
    public List<SerieDTO> getAllSeries() {
        return serieService.getAllSeries();
    }

    @GetMapping("/top5")
    public List<SerieDTO> getTop5Series() {
        return serieService.getTop5series();
    }

    @GetMapping("/lanzamientos")
    public List<SerieDTO> getLatestReleases() {
        return serieService.getLatestReleases();
    }

    @GetMapping("/{id}")
    public SerieDTO getId(@PathVariable Long id) {
        return serieService.getId(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodeDTO> getAllSeasons(@PathVariable Long id) {
        return serieService.getAllSeasons(id);
    }

    @GetMapping("/{id}/temporadas/{numeroTemporada}")
    public List<EpisodeDTO> getSeasonsByNumber(@PathVariable Long id, @PathVariable Long numeroTemporada) {
        return  serieService.getSeasonsByNumber(id, numeroTemporada);
    }

    @GetMapping("/categoria/{nombreGenero}")
    public List<SerieDTO> getSeriesByGenre(@PathVariable String nombreGenero) {
        return serieService.getSeriesByGenre(nombreGenero);
    }
}
