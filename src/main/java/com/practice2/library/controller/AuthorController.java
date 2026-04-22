package com.practice2.library.controller;

import com.practice2.library.DTO.AuthorReq;
import com.practice2.library.entity.Author;
import com.practice2.library.service.AuthorService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public Author saveAuthor(@RequestBody AuthorReq request) {
        return authorService.saveAuthor(request.getName());
    }

    @GetMapping
    public Optional<Author> getAuthor(@RequestParam String name) {
        return authorService.getAuthor(name);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(  Long id) {
        authorService.deleteAuthor(id);
    }

    @GetMapping("/all")
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }
}
