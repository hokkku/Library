package com.practice2.library.service;


import com.practice2.library.DTO.AuthorResponse;
import com.practice2.library.entity.Author;
import com.practice2.library.entity.Book;
import com.practice2.library.repository.AuthorRepository;
import com.practice2.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorServ {
    private final AuthorRepository authorRep;
    private final BookRepository bookRep;
    public AuthorServ(AuthorRepository authorRep, BookRepository bookRep) {
        this.authorRep = authorRep;
        this.bookRep = bookRep;
    }
    public AuthorResponse toResponse(Author author){
        return new AuthorResponse(
                author.getId(),
                author.getName(),

                author.getBooks()
                        .stream()
                        .map(Book::getTitle)
                        .collect(Collectors.toSet())
        );
    }
    public Author saveAuthor(String name, Set<String> bookTitles) {
        Author author = new Author();
        author.setName(name);
        Set<Book> books = new HashSet<>(bookRep.findByTitleIn(bookTitles));
        author.setBooks(books);
        Author savedAuthor = authorRep.save(author);
        for (Book book : books) {
            book.getAuthors().add(savedAuthor);
        }
        bookRep.saveAll(books);
        return savedAuthor;
    }

            public Optional<Author> getAuthor(String name) {
                return authorRep.findByName(name);
            }

            public void deleteAuthor(Long id) {

                Author author = authorRep.findById(id)
                        .orElseThrow();

                author.getBooks().forEach(book ->
                        book.getAuthors().remove(author)
                );

                authorRep.delete(author);
            }

            public List<Author> getAllAuthors() {
                return authorRep.findAll();
            }
        }