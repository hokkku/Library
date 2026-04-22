package com.practice2.library.service;


import com.practice2.library.entity.Author;
import com.practice2.library.entity.Book;
import com.practice2.library.repository.AuthorRepository;
import com.practice2.library.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            throw new RuntimeException("Такая книга уже существует");
        }
        Set<Author> authors = new HashSet<>();
        for (String name : authorNames) {
            Author author = authorRep.findByName(name)
                    .orElseGet(() -> {
                        Author newAuthor = new Author();
                        newAuthor.setName(name);
                        return authorRep.save(newAuthor);
                    });

            authors.add(author);
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
}


