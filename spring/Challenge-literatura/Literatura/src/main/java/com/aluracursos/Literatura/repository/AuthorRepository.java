package com.aluracursos.Literatura.repository;

import com.aluracursos.Literatura.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author,Long> {
    Optional<Author> findByNameContainsIgnoreCase(String nameAuthor);

    @Query("SELECT a FROM Author a WHERE a.birth_year <= :year AND a.death_year >= :year")
    List<Author> findAuthorsAliveInYear(int year);
}
