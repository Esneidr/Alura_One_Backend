package com.aluracursos.screenmatch.dto;

import com.aluracursos.screenmatch.enums.Category;

public record SerieDTO(
        Long id,
        String title,
        Integer totalSeasons,
        Double rating,
        String poster,
        Category genre,
        String actors,
        String synopsis) {
}
