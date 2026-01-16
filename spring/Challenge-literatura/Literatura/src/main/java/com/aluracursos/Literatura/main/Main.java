package com.aluracursos.Literatura.main;

import com.aluracursos.Literatura.models.*;
import com.aluracursos.Literatura.repository.AuthorRepository;
import com.aluracursos.Literatura.repository.BooksRepository;
import com.aluracursos.Literatura.services.ApiConsumer;
import com.aluracursos.Literatura.services.MapData;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private Scanner scanner = new Scanner(System.in);
    private ApiConsumer api = new ApiConsumer();
    private final String URL_BASE = "https://gutendex.com/books/";
    private MapData map = new MapData();
    private BooksRepository bookRepo;
    private AuthorRepository authorRepo;
    private Optional<Author> searchAutor;
    private List<Book> searchBook;
    private List<Author> authorList;

    public Main(BooksRepository bookRepo,
                AuthorRepository authorRepo
                ) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
    }

    public void mainMenu() {
        var option = -1;
        while (option != 0) {
            var menu = """
                    1 - Buscar un libro
                    2 - Consultar libros por autor
                    3 - Consultar libros por idioma
                    4 - Listar autores vivos en determinado año
                    
                    0 - Salir
                    """;
            System.out.print(menu);
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    searchBookWeb();
                    break;
                case 2:
                    searchAuthor();
                    break;
                case 3:
                    searchBookLanguage();
                    break;
                case 4:
                    searchAuthorsByYear();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private void searchBookWeb() {
        BookData data = getBookData();
        if (data == null) {
            System.out.println("No se encontró libro");
            return;
        }

        //Toma el primer autor
        AuthorData authorData = data.authors().get(0);

        //Buscar autor por nombre, o crearlo si no existe
        Author author = authorRepo.findByNameContainsIgnoreCase(authorData.name())
                .orElseGet(() -> authorRepo.save(new Author(authorData)));

        //Busca el libro por su titulo y autor.
        boolean bookExists = bookRepo.existsByTitleIgnoreCaseAndAuthor_NameIgnoreCase(
                data.title(), authorData.name());

        if (bookExists) {
            System.out.println("El libro ya existe en la DB.");
            return;
        }

        //Guarda el libro
        Book book = new Book(data);
        book.setAuthor(author);
        bookRepo.save(book);

        System.out.println("Libro guardado exitosamente en la base de datos.");
    }

    private void searchAuthor() {
        System.out.print("Ingresa el nombre del autor a buscar: ");
        var nameAuthor = scanner.nextLine();

        searchAutor = authorRepo.findByNameContainsIgnoreCase(nameAuthor);

        if (searchAutor.isPresent()) {
            System.out.printf("Se encontró al autor %s.%n", searchAutor.get().getName());

            searchBook = bookRepo.getBooksByAuthor(searchAutor.get().getId());
            System.out.println("Libros registrados: ");
            searchBook.forEach(b -> System.out.printf("Título: %s - lenguaje: %s.%n",
                    b.getTitle(), b.getLanguage())
            );

        } else {
            System.out.printf("El autor %s no esta registrado.%n", nameAuthor);
        }
    }

    private void searchBookLanguage() {
        System.out.print("Ingresa el código (dos letras) del idioma: ");
        var searchLang = scanner.nextLine().trim();

        if (searchLang.length() != 2) {
            System.out.println("\n Código inválido.");
            System.out.println("Debes ingresar un código ISO 639-1 de dos letras.");
            System.out.println("Ejemplos:");
            System.out.println(" en - Inglés");
            System.out.println(" es - Español");
            System.out.println(" pt - Portugués\n");

            searchBookLanguage();
            return;
        }

        searchBook = bookRepo.getBooksByLanguage(searchLang.toLowerCase());
        if (searchBook.isEmpty()) {
            System.out.printf("No hay libros registrados para el idioma %s.%n", searchLang);
            return;
        }

        System.out.printf("Libros encontrados para el idioma %s:%n", searchLang);
        searchBook.forEach(b -> System.out.printf("Título: %s - Autor: %s - Descargas: %.2f%n",
                b.getTitle(), b.getAuthor().getName(), b.getDownload_count())
        );
    }

    private void searchAuthorsByYear() {
        int year;

        while (true) {
            System.out.print("Ingresa el año a buscar: ");
            String searchYear = scanner.nextLine().trim();

            if (searchYear.matches("\\d{4}")) {
                year = Integer.parseInt(searchYear);
                break;
            }

            System.out.printf("Error, año %s inválido. Inténtalo de nuevo.%n", searchYear);
        }

        authorList = authorRepo.findAuthorsAliveInYear(year);

        if (authorList.isEmpty()) {
            System.out.printf("No hay autores vivos en el año %d.%n", year);
            return;
        }

        System.out.printf("Autores vivos en el año %d:%n", year);
        authorList.forEach(a ->
                System.out.printf("- %s (%d - %s)%n",
                        a.getName(),
                        a.getBirth_year(),
                        a.getDeath_year() == null ? "actualidad" : a.getDeath_year())
        );
    }

    private BookData getBookData() {
        System.out.print("Busca un libro: ");
        var searchBook = scanner.nextLine();
        var json = api.getData(URL_BASE + "?search=" + searchBook.replace(" ", "+"));

        ApiResponse response = map.getData(json, ApiResponse.class);
        return response.booksResult().get(0);
    }
}
