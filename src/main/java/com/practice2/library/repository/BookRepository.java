package com.practice2.library.repository;

import com.practice2.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("""
            SELECT b
            FROM Book b
            JOIN b.authors a
            WHERE a.name = :name
            """)
    List<Book> findByAuthorsName(@Param("name") String name);
    Optional<Book> findByTitle(String title);
    @Query("""
        SELECT b
        FROM Book b
        WHERE LOWER(b.title)
        LIKE LOWER(CONCAT('%', :title, '%'))
        """)
    List<Book> searchBooksByTitle(
            @Param("title") String title
    );
}