package com.aluracursos.desafio7_Books.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record authorData(
        @JsonAlias("name") String name,
        @JsonAlias("birth_year") String birthDate
) {
}
