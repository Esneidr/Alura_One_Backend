package com.aluracursos.screenmatch.main;

import com.aluracursos.screenmatch.enums.Category;
import com.aluracursos.screenmatch.models.*;
import com.aluracursos.screenmatch.repository.SerieRepository;
import com.aluracursos.screenmatch.services.ApiConsumer;
import com.aluracursos.screenmatch.services.MapData;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private Scanner scanner = new Scanner(System.in);
    private ApiConsumer api = new ApiConsumer();
    private final String URL_BASE = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6e5006c4";
    private MapData map = new MapData();
    private List<SeriesData> seriesData = new ArrayList<>();
    private SerieRepository repository;
    private List<Serie> serieList;
    private Optional<Serie> searchSerie;

    public Main(SerieRepository serieRepository) {
        this.repository = serieRepository;
    }

    public void mainMenu() {
        var option = -1;
        while (option != 0) {
            var menu = """
                    1 - Buscar nuevas series
                    2 - Buscar episodios
                    3 - Ver series buscadas
                    4 - Buscar serie guarda por titulo
                    5 - Top 5 mejores series
                    6 - Buscar serie guarda por categoría
                    7 - Filtar serie por temporada y rating
                    8 - Buscar episodios por titulo
                    9 - TOP 5 episodios por serie
                    
                    0 - Salir
                    """;
            System.out.print(menu);
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    searchSerieWeb();
                    break;
                case 2:
                    searchEpisode();
                    break;
                case 3:
                    showSeries();
                    break;
                case 4:
                    searchSerieTitle();
                    break;
                case 5:
                    topFiveSeries();
                    break;
                case 6:
                    searchSerieCategory();
                    break;
                case 7:
                    searchSerieSeasonRating();
                    break;
                case 8:
                    searchEpisodesName();
                    break;
                case 9:
                    searchTop5Episodes();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private SeriesData getSeriesData() {
        System.out.print("Busca una serie: ");
        var searchSerie = scanner.nextLine();
        var json = api.getData(URL_BASE + searchSerie.replace(" ", "+") + API_KEY);
        System.out.println(json);
        var data = map.getData(json, SeriesData.class);
        return data;
    }

    private void searchEpisode() {
        showSeries();
        System.out.print("Ingresa el nombre de la serie: ");
        var nameSerie = scanner.nextLine();

        Optional<Serie> serie = serieList.stream()
                .filter(s -> s.getTitle().toLowerCase().contains(nameSerie.toLowerCase()))
                .findFirst();

        if (serie.isPresent()) {
            var successData = serie.get();

            List<SeasonInfo> seasons = new ArrayList<>();

            for (int i = 1; i <= successData.getTotalSeasons(); i++) {
                var json = api.getData(URL_BASE + successData.getTitle().replace(" ", "+")
                        + "&Season=" + i + API_KEY);
                var seasonDetails = map.getData(json, SeasonInfo.class);
                seasons.add(seasonDetails);
            }

            seasons.forEach(System.out::println);

            List<Episode> episodeList = seasons.stream()
                    .flatMap(d -> d.episodes().stream()
                            .map(e -> new Episode(d.number(), e)))
                    .collect(Collectors.toList());

            successData.setEpisodes(episodeList);
            repository.save(successData);
        }
    }

    private void searchSerieWeb() {
        SeriesData data = getSeriesData();
        Serie serie = new Serie(data);
        repository.save(serie);
        //seriesData.add(data);
        System.out.println(data);
    }

    private void showSeries() {
         serieList = repository.findAll();

        serieList.stream()
                .sorted(Comparator.comparing(Serie::getGenre))
                .forEach(System.out::println);
    }

    private void searchSerieTitle() {
        System.out.print("Ingresa el nombre de la serie a buscar: ");
        var nameSerie = scanner.nextLine();

        searchSerie = repository.findByTitleContainsIgnoreCase(nameSerie);

        if(searchSerie.isPresent()) {
            System.out.println("Se encontró la serie: " + searchSerie.get());
        } else {
            System.out.printf("Serie  %s no encontrada.%n", nameSerie);
        }
    }

    private void topFiveSeries() {
        List<Serie> topSeries = repository.findTop5ByOrderByRatingDesc();
        topSeries.forEach(s -> System.out.printf("La serie %s tiene una evalaución: %.2f%n",
                s.getTitle(), s.getRating()));
    }

    private void searchSerieCategory() {
        System.out.print("Ingresa el género de la serie a buscar: ");
        String genreInput = scanner.nextLine().trim();

        try {
            Category category = Category.fromSpanish(genreInput);

            List<Serie> series = repository.findByGenre(category);
            System.out.printf("Series del género %s:%n", genreInput);
            series.forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.out.println("Género no válido. Intenta nuevamente.");
        }
    }

    private void searchSerieSeasonRating() {
        System.out.print("Buscar series con máximo cuántas temporadas: ");
        int maxSeasons = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Con un rating mínimo de: ");
        double minRating = scanner.nextDouble();
        scanner.nextLine();

        List<Serie> series = repository.seireSeansonsAndRating(maxSeasons, minRating);

        System.out.println("\n***** Series filtradas *****");

        if (series.isEmpty()) {
            System.out.println("No se encontraron series con esos criterios.");
            return;
        }

        series.forEach(serie ->
                System.out.printf("La serie %s - rating %.2f%n",
                        serie.getTitle(),
                        serie.getRating())
        );
    }

    private void searchEpisodesName() {
        System.out.print("\nIngresa el nombre del episodioa buscar: ");
        var nameEpisode = scanner.nextLine();

            List<Episode> episodeList = repository.episodesByName(nameEpisode);
            episodeList.forEach(e ->
                    System.out.printf("Serie: %s - Temporada %d - Episodio %d - Evaluación %.1f%n",
                            e.getSerie().getTitle(), e.getSeasonNumber(), e.getEpisodeNumber(), e.getRating())
            );
    }

    private void searchTop5Episodes() {
        searchSerieTitle();

        if (searchSerie.isPresent()) {
            Serie serie = searchSerie.get();
            List<Episode> topEpisodes = repository.top5Episodes(serie);
            topEpisodes.forEach(e ->
                    System.out.printf("Serie: %s - Temporada %d - Episodio %s - Evaluación %.1f%n",
                            e.getSerie().getTitle(), e.getSeasonNumber(), e.getTitle(), e.getRating())
            );
        }
    }

}
