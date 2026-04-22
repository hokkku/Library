package com.practice2.library.controller;

import com.practice2.library.DTO.AuthorReq;
import com.practice2.library.entity.Author;
import com.practice2.library.service.AuthorServ;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorServ authorServ;
    public AuthorController(AuthorServ authorServ) {
        this.authorServ = authorServ;
    }

    @PostMapping
    public Author saveAuthor(@RequestBody AuthorReq request) {
        return authorServ.saveAuthor(request.getName());
    }

    @GetMapping
    public Optional<Author> getAuthor(@RequestParam String name) {
        return authorServ.getAuthor(name);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(  Long id) {
        authorServ.deleteAuthor(id);
    }

    @GetMapping("/all")
    public List<Author> getAllAuthors() {
        return authorServ.getAllAuthors();
    }
}
