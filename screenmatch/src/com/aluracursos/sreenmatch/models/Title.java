package com.aluracursos.sreenmatch.models;

import com.aluracursos.sreenmatch.excepcion.ErrorRuntimeException;
import com.google.gson.annotations.SerializedName;
import com.sun.jdi.Value;

import java.time.Year;

public class Title implements Comparable<Title> {
    private String title;
    private int releaseDate;
    private int durationInMinutes;
    private boolean includedInPlan;
    private double sumRating;
    private int total;

    public Title(String title, int releaseDate) {
        this.title = title;
        this.releaseDate = releaseDate;
    }

    public Title(TitleOmdb myTitleOmdb) {
        this.title = myTitleOmdb.title();
        this.releaseDate = Integer.parseInt(myTitleOmdb.year());

        if (myTitleOmdb.runtime().contains("N/A")) {
            throw new ErrorRuntimeException("El campo contiene un N/A");
        }

        this.durationInMinutes = Integer.parseInt(
                myTitleOmdb.runtime().substring(0,3).replace(" ", "")
        );
    }

    public String getTitle() {
        return title;
    }

    public int getReleaseDate() {
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

    public void setReleaseDate(int releaseDate) {
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
        System.out.printf("Fecha de lanzamiento: %d%n", releaseDate);
        System.out.printf("Duración en minutos: %d min%n", getDurationInMinutes());
    }

    public void rating(double score) {
        sumRating += score;
        total++;
    }

    public double averageRating() {
        return sumRating / total;
    }

    @Override
    public int compareTo(Title anotherTitle) {
        return this.getTitle().compareTo(anotherTitle.getTitle());
    }

    @Override
    public String toString() {
        return "(Nombre = " + title +
                ", Fecha de lanzamiento = " + releaseDate +
                ", Duración = " + durationInMinutes+")";
    }
}
