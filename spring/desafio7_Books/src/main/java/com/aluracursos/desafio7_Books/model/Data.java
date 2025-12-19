package com.aluracursos.desafio7_Books.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Data(
       @JsonAlias("results") List<BooksData> resultsList
) {
}
