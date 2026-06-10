package com.practice2.library.controller;

import com.practice2.library.DTO.BookReq;
import com.practice2.library.DTO.BookResponse;
import com.practice2.library.entity.Book;
import com.practice2.library.service.BookServ;
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
    public BookResponse saveBook(@RequestBody BookReq request) {
        Book book = bookServ.saveBook(request.getTitle(), request.getAuthors());
        return bookServ.toResponse(book);
    }
    @GetMapping("/all")
    public List<BookResponse> getAllBooks() {

        return bookServ.getAllBooks()
                .stream()
                .map(bookServ::toResponse)
                .toList();
    }
    @GetMapping("/by-author")
    public List<BookResponse> getBooksByAuthor(@RequestParam String name) {
        return bookServ.getBooksByAuthor(name)
                .stream()
                .map(bookServ::toResponse)
                .toList();
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookServ.deleteBook(id);
    }
    @GetMapping("/search")
    public List<BookResponse> searchBooksByTitle(@RequestParam String title){
        return bookServ.searchBooksByTitle(title)
                .stream()
                .map(bookServ::toResponse)
                .toList();
    }
}