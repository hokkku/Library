package com.practice2.library.service;


import com.practice2.library.entity.Author;
import com.practice2.library.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    public Author saveAuthor(String name) {
        Author author = new Author();
        author.setName(name);
        return authorRepository.save(author);
    }

    public Optional<Author> getAuthor(String name) {
        return authorRepository.findByName(name);
    }
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
}