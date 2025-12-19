package com.aluracursos.desafio7_Books.main;

import com.aluracursos.desafio7_Books.model.BooksData;
import com.aluracursos.desafio7_Books.model.Data;
import com.aluracursos.desafio7_Books.service.ApiConsumer;
import com.aluracursos.desafio7_Books.service.ConvertData;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ApiConsumer consumer = new ApiConsumer();
    private ConvertData convert = new ConvertData();
    private Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        var json = consumer.getData(URL_BASE);
        var data = convert.searchData(json, Data.class);

        //Top 10 Libros con más descargas
        System.out.println("Libros con más descargas: ");
        data.resultsList().stream()
                .sorted(Comparator.comparing(BooksData::totalDownloads).reversed())
                .limit(10)
                .map(l -> l.title().toUpperCase())
                .forEach(System.out::println);

        //Busqueda de libros por titulo
        System.out.println("Ingres el nombre del libro: ");
        var book = scanner.nextLine();
        json = consumer.getData(URL_BASE + "?search=" + book.replace(" ", "+"));
        var bookSearch = convert.searchData(json, Data.class);

        Optional<BooksData> booksData = bookSearch.resultsList().stream()
                .filter(l -> l.title().toUpperCase().contains(book.toUpperCase()))
                .findFirst();
        if (booksData.isPresent()) {
            System.out.println("Libro encontrado. ");
            System.out.println(booksData.get());
        } else {
            System.out.println("Libro no encontrado.");
        }

        //Estadisticas
        DoubleSummaryStatistics est = data.resultsList().stream()
                .filter(d -> d.totalDownloads() > 0)
                .collect(Collectors.summarizingDouble(BooksData::totalDownloads));
        System.out.println("Media descargas: " + est.getAverage());
        System.out.println("Maximas descargas: " + est.getMax());
        System.out.println("Minima descargas: " + est.getMin());
        System.out.println("Registros evaludos: " + est.getCount());
    }
}
