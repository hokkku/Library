package com.practice2.library.repository;

import com.practice2.library.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
class BookRepTest {

    @Autowired
    private BookRepository bookRepository;
    @Container
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:15")
                    .withDatabaseName("test_db")
                    .withUsername("postgres")
                    .withPassword("postgres");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add(
                "spring.datasource.url",
                postgres::getJdbcUrl
        );
        registry.add(
                "spring.datasource.username",
                postgres::getUsername
        );
        registry.add(
                "spring.datasource.password",
                postgres::getPassword
        );
        registry.add(
                "spring.jpa.hibernate.ddl-auto",
                () -> "update"
        );
    }
    @Test
    void SaveBook() {
        Book book = new Book();
        book.setTitle("Java Core");
        bookRepository.save(book);
        List<Book> books = bookRepository.findAll();
        assertThat(books).isNotEmpty();
        assertThat(books.get(0).getTitle())
                .isEqualTo("Java Core");
    }
}