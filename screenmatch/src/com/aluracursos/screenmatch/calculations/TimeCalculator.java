package com.aluracursos.screenmatch.calculations;

import com.aluracursos.sreenmatch.models.Title;

public class TimeCalculator {
    private int timeTotal;

    public int getTimeTotal() {
        return timeTotal;
    }

    public void includes(Title title) {
        this.timeTotal += title.getDurationInMinutes();
    }

}
