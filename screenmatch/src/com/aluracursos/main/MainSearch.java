package com.aluracursos.main;

import com.aluracursos.sreenmatch.models.Title;
import com.aluracursos.sreenmatch.models.TitleOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainSearch {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        List<Title> titles = new ArrayList<>();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        while (true) {


            System.out.println("Escriba el nombre de un pelicula: ");
            var search = scanner.nextLine();

            if (search.equalsIgnoreCase("Salir")) {
                break;
            }

            var url = "http://www.omdbapi.com/?t=" +
                    search.replace(" ", "+") +
                    "&apikey=6e5006c4";

            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .build();

                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();
                System.out.println(json);

                Title myTitle = gson.fromJson(json, Title.class);
                //System.out.println(myTitle);

                TitleOmdb myTitleOmdb = gson.fromJson(json, TitleOmdb.class);
                System.out.println(myTitleOmdb);

                Title title = new Title(myTitleOmdb);

                titles.add(title);
            } catch (Exception e) {
                System.out.printf("Ocurrió un error inesperado en: %s%n", e.getMessage());
            }
        }
        System.out.println(titles);

        FileWriter writing = new FileWriter("movies.json");
        writing.write(gson.toJson(titles));
        writing.close();

        System.out.println("Saliendo del programa...");
    }
}
