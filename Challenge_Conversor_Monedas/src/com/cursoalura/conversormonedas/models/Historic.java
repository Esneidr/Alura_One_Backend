package com.cursoalura.conversormonedas.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Historic {
    private final String from;
    private final String to;
    private final double amount;
    private final double result;
    private final LocalDateTime timestamp;

    public Historic(String from, String to, double amount, double result) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.result = result;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("[%s] %.2f %s → %.2f %s",
                timestamp.format(formatter), amount, from, result, to
        );
    }
}

