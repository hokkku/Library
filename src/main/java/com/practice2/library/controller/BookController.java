package com.practice2.library.controller;

import com.practice2.library.DTO.BookReq;
import com.practice2.library.entity.Book;
import com.practice2.library.service.BookServ;
import org.jspecify.annotations.NonNull;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookServ bookServ;
    public BookController(BookServ bookServ) {
        this.bookServ = bookServ;
    }

    @PostMapping
    public Book saveBook(@RequestBody @NonNull BookReq request) {
        return bookServ.saveBook(request.getTitle(), request.getAuthors());
    }
    @GetMapping
    public List<Book> getAllBooks() {
        return bookServ.getAllBooks();
    }

    @GetMapping("/by-author")
    public List<Book> getBooksByAuthor(@RequestParam String name) {
        return bookServ.getBooksByAuthor(name);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookServ.deleteBook(id);
    }
}