package com.company.bookstore.controller;

import com.company.bookstore.models.Book;
import com.company.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/books")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBook(@RequestBody Book book) {
        bookRepository.save(book);
    }

    @DeleteMapping("/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable int id) {
        bookRepository.deleteById(id);
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable int id) {
        Optional<Book> returnVal = bookRepository.findById(id);
        return returnVal.orElse(null);
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/books/byAuthor/{authorId}")
    public List<Book> getBooksByAuthor(@PathVariable int authorId) {
        return bookRepository.findByAuthorId(authorId);
    }
}
