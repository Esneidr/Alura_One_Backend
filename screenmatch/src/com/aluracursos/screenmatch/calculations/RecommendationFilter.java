package com.aluracursos.screenmatch.calculations;

public class RecommendationFilter {
    public void filer(Rating rating){
        if (rating.getRating() > 4){
            System.out.println("Tiene una buena evaluación.");
        } else if (rating.getRating()  >= 2) {
            System.out.println("Popular en el momento.");
        } else {
            System.out.println("Agregar a tu lista.");
        }
    }
}
