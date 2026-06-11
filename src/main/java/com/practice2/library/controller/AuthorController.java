package com.practice2.library.controller;

import com.practice2.library.DTO.AuthorReq;
import com.practice2.library.DTO.AuthorResponse;
import com.practice2.library.entity.Author;
import com.practice2.library.service.AuthorServ;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorServ authorServ;
    public AuthorController(AuthorServ authorServ) {
        this.authorServ = authorServ;
    }

    @PostMapping
    public AuthorResponse saveAuthor(@RequestBody AuthorReq request) {
        Author author = authorServ.saveAuthor(request.getName(), request.getBookTitles());
        return authorServ.toResponse(author);
    }

    @GetMapping("")
    public AuthorResponse getAuthor(@RequestParam String name) {
        Author author = authorServ
                .getAuthor(name)
                .orElseThrow();

        return authorServ.toResponse(author);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorServ.deleteAuthor(id);
    }

    @GetMapping("/all")
    public List<AuthorResponse> getAllAuthors() {
        return authorServ.getAllAuthors()
                .stream()
                .map(authorServ::toResponse)
                .toList();
    }
}
