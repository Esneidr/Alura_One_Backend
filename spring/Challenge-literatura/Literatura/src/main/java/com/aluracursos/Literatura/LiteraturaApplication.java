package com.aluracursos.Literatura;

import com.aluracursos.Literatura.main.Main;
import com.aluracursos.Literatura.repository.AuthorRepository;
import com.aluracursos.Literatura.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

    @Autowired
    private BooksRepository bookRepo;
    @Autowired
    private AuthorRepository authorRepo;

    public static void main(String[] args) {
        SpringApplication.run(LiteraturaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Main main = new Main(bookRepo, authorRepo);
        main.mainMenu();
    }
}
