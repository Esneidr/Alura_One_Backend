package com.aluracursos.Literatura.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String language;
    private Double download_count;

    @ManyToOne
    private Author author;

    public Book() {}

    public Book(BookData data) {
        this.title = data.title();
        this.language = data.languages() == null || data.languages().isEmpty()
                ? "ND"
                : data.languages().get(0);
        this.download_count = data.download_count() == null ? 0.0 : data.download_count();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Double getDownload_count() {
        return download_count;
    }

    public void setDownload_count(Double download_count) {
        this.download_count = download_count;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
