package com.practice2.library.service;


import com.practice2.library.DTO.BookResponse;
import com.practice2.library.Exception.BookAlreadyExistsException;
import com.practice2.library.entity.Author;
import com.practice2.library.entity.Book;
import com.practice2.library.repository.AuthorRepository;
import com.practice2.library.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServ {

    private final BookRepository bookRep;
    private final AuthorRepository authorRep;
    public BookServ(BookRepository bookRep, AuthorRepository authorRep) {
        this.bookRep = bookRep;
        this.authorRep = authorRep;
    }
    public Book saveBook(String title, Set<String> authorNames) {
        if (bookRep.findByTitle(title).isPresent()) {
            throw new BookAlreadyExistsException ("Книга " + title + " уже существует");
        }
        Set<Author> authors = new HashSet<>(authorRep.findByNameIn(authorNames));
        Set<String> foundNames = authors.stream()
                .map(Author::getName)
                .collect(Collectors.toSet());
        for (String name : authorNames) {
            if (!foundNames.contains(name)) {
                Author author = new Author();
                author.setName(name);
                authors.add(authorRep.save(author));
            }
        }
        Book book = new Book();
        book.setTitle(title);
        book.setAuthors(authors);
        return bookRep.save(book);
    }
    public List<Book> getAllBooks() {
        return bookRep.findAll();
    }
    public List<Book> getBooksByAuthor(String name) {
        return bookRep.findByAuthorsName(name);
    }
    public void deleteBook(Long id) {
        bookRep.deleteById(id);
    }

    public BookResponse toResponse(Book book){
        return new BookResponse(
                book.getId(),
                book.getTitle(),

                book.getAuthors()
                        .stream()
                        .map(Author::getName)
                        .collect(Collectors.toSet())
        );
    }
    public List<Book> searchBooksByTitle(String title){
        return bookRep.searchBooksByTitle(title);
    }
}


