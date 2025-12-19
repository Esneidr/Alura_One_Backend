package com.aluracursos.screenmatch.main;

import com.aluracursos.screenmatch.models.*;
import com.aluracursos.screenmatch.repository.SerieRepository;
import com.aluracursos.screenmatch.services.ApiConsumer;
import com.aluracursos.screenmatch.services.MapData;

import java.time.format.DateTimeFormatter;
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

    public Main(SerieRepository serieRepository) {
        this.repository = serieRepository;
    }

    public void mainMenu() {
        var option = -1;
        while (option != 0) {
            var menu = """
                    1 - Buscar series
                    2 - Buscar episodios
                    3 - Ver series buscadas
                    
                    0 - Salir
                    """;
            System.out.println(menu);
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
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private SeriesData getSeriesData() {
        System.out.println("Busca una serie: ");
        var searchSerie = scanner.nextLine();
        var json = api.getData(URL_BASE + searchSerie.replace(" ", "+") + API_KEY);
        System.out.println(json);
        var data = map.getData(json, SeriesData.class);
        return data;
    }

    private void searchEpisode() {
        SeriesData data = getSeriesData();
        List<SeasonInfo> seasons = new ArrayList<>();

        for (int i = 1; i <= data.totalSeasons(); i++) {
            var json = api.getData(URL_BASE + data.title().replace(" ", "+")
                    + "&Season=" + i + API_KEY);
            var seasonDetails = map.getData(json, SeasonInfo.class);
            seasons.add(seasonDetails);
        }
        seasons.forEach(System.out::println);
    }

    private void searchSerieWeb() {
        SeriesData data = getSeriesData();
        Serie serie = new Serie(data);
        repository.save(serie);
        //seriesData.add(data);
        System.out.println(data);
    }

    private void showSeries() {
        List<Serie> serieList = repository.findAll();

        serieList.stream()
                .sorted(Comparator.comparing(Serie::getGenre))
                .forEach(System.out::println);
    }
}
