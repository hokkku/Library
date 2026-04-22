package com.practice2.library.service;


import com.practice2.library.entity.Author;
import com.practice2.library.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServ {
    private final AuthorRepository authorRep;
    public AuthorServ(AuthorRepository authorRep) {
        this.authorRep = authorRep;
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
        authorRep.deleteById(id);
    }
    public List<Author> getAllAuthors() {
        return authorRep.findAll();
    }
}