package com.aluracursos.sreenmatch.models;

import java.time.Year;

public class Title {
    private String title;
    private Year releaseDate;
    private int durationInMinutes;
    private boolean includedInPlan;
    private double sumRating;
    private int total;

    public String getTitle() {
        return title;
    }

    public Year getReleaseDate() {
        return releaseDate;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public boolean isIncludedInPlan() {
        return includedInPlan;
    }

    public int getTotal() {
        return total;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(Year releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public void setIncludedInPlan(boolean includedInPlan) {
        this.includedInPlan = includedInPlan;
    }

    public void technicalInfo() {
        System.out.printf("El nombre de la película es: %s%n", title);
        System.out.printf("Fecha de lanzamiento: %d%n", releaseDate.getValue());
        System.out.printf("Duración en minutos: %d min%n", getDurationInMinutes());
    }

    public void rating(double score) {
        sumRating += score;
        total++;
    }

    public double averageRating() {
        return sumRating / total;
    }
}
