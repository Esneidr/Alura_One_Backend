package com.aluracursos.screenmatch.enums;

public enum Category {
    ACTION("Action", "Acción"),
    ROMANCE("Romance", "Romance"),
    COMEDY("Comedy", "Comedia"),
    DRAMA("Drama", "Drama"),
    CRIME("Crime", "Crimen"),
    ANIMATION("Animation", "Animación"),
    ADVENTURE("Adventure", "Aventura");

    private String categoryOmdb;
    private String categorySpanish;

    Category (String categoryOmdb, String categorySpanish) {
        this.categoryOmdb = categoryOmdb;
        this.categorySpanish = categorySpanish;
    }

    public static Category fromString(String text) {
        for (Category category :  Category.values()) {
            if (category.categoryOmdb.equalsIgnoreCase(text)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
    }

    public static Category fromSpanish(String text) {
        for (Category category :  Category.values()) {
            if (category.categorySpanish.equalsIgnoreCase(text)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
    }
}
