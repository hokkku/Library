package com.practice2.library.service;


import com.practice2.library.DTO.AuthorResponse;
import com.practice2.library.entity.Author;
import com.practice2.library.entity.Book;
import com.practice2.library.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServ {
    private final AuthorRepository authorRep;
    public AuthorServ(AuthorRepository authorRep) {
        this.authorRep = authorRep;
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
            public Author saveAuthor(String name) {
                Author author = new Author();
                author.setName(name);
                return authorRep.save(author);
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