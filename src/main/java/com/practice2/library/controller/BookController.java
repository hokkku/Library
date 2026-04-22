package com.practice2.library.controller;



import com.practice2.library.DTO.BookReq;
import com.practice2.library.entity.Book;
import com.practice2.library.service.BookService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public Book saveBook(@RequestBody BookReq request) {
        return bookService.saveBook(request.getTitle(), request.getAuthors());
    }
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/by-author")
    public List<Book> getBooksByAuthor(@RequestParam String name) {
        return bookService.getBooksByAuthor(name);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}