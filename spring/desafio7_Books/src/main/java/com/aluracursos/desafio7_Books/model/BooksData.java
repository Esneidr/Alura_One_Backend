package com.aluracursos.desafio7_Books.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BooksData(
       @JsonAlias("title") String title,
       @JsonAlias("authors") List<authorData> author,
       @JsonAlias("languages") List<String> languages,
       @JsonAlias("download_count") Double totalDownloads
) {
}
