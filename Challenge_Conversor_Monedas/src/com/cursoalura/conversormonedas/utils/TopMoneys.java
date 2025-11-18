package com.cursoalura.conversormonedas.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class TopMoneys {
    public static Map<String, Double> top(Map<String, Double> rates, String code) {
        return rates.entrySet()
                .stream()
                .filter(entry -> !entry.getKey().equalsIgnoreCase(code))
                .sorted(Map.Entry.<String, Double>comparingByValue())
                .limit(15)
                .collect(
                        java.util.stream.Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (a, b) -> b, //si hay claves replicadas
                                LinkedHashMap::new
                        )
                );
    }
}
