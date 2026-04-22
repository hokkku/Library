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
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }
    public Book saveBook(String title, Set<String> authorNames) {

        if (bookRepository.findByTitle(title).isPresent()) {
            throw new RuntimeException("Такая книга уже существует");
        }
        Set<Author> authors = new HashSet<>();
        for (String name : authorNames) {
            Author author = authorRepository.findByName(name)
                    .orElseGet(() -> {
                        Author newAuthor = new Author();
                        newAuthor.setName(name);
                        return authorRepository.save(newAuthor);
                    });

            authors.add(author);
        }

        Book book = new Book();
        book.setTitle(title);
        book.setAuthors(authors);

        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    public List<Book> getBooksByAuthor(String name) {
        return bookRepository.findByAuthorsName(name);
    }
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}


