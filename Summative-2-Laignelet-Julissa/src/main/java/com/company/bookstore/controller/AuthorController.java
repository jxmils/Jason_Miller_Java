package com.company.bookstore.controller;

import com.company.bookstore.models.Author;
import com.company.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AuthorController {
    @Autowired
    AuthorRepository authorRepository;

    @PostMapping("/authors")
    @ResponseStatus(HttpStatus.CREATED)
    public Author addAuthor(@RequestBody Author author) {
        return authorRepository.save(author);
    }

    @PutMapping("/authors")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAuthor(@RequestBody Author author) {
        authorRepository.save(author);
    }

    @DeleteMapping("/authors/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable int id) {
        authorRepository.deleteById(id);
    }

    @GetMapping("/authors/{id}")
    public Author getAuthorById(@PathVariable int id) {
        Optional<Author> returnVal = authorRepository.findById(id);
        return returnVal.orElse(null);
    }

    @GetMapping("/authors")
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
}
