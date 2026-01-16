package com.aluracursos.Literatura.repository;

import com.aluracursos.Literatura.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BooksRepository extends JpaRepository<Book,Long> {
    boolean existsByTitleIgnoreCaseAndAuthor_NameIgnoreCase(String title, String authorName);

    @Query("SELECT b FROM Book b WHERE b.author.id = :id")
    List<Book> getBooksByAuthor(Long id);

    @Query("SELECT b FROM Book b JOIN b.author a WHERE LOWER(b.language) = LOWER(:lang)")
    List<Book> getBooksByLanguage(String lang);

}
