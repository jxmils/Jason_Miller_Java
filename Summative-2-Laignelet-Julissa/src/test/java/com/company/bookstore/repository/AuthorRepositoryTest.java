package com.company.bookstore.repository;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Book;
import com.company.bookstore.models.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AuthorRepositoryTest {
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    PublisherRepository publisherRepository;
    private Author author;
    private Publisher publisher;

    @BeforeEach
    public void setUp() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        publisherRepository.deleteAll();

        author = new Author();
        author.setFirstName("Stephanie");
        author.setLastName("Perkins");
        author.setCity("Atlanta");
        author.setStreet("GoodVibes St.");
        author.setState("GA");
        author.setPostalCode("54647");
        author.setPhone("451-765-8765");
        author.setEmail("perkins@writers.com");
        author.setBooks(new ArrayList<>());

        author = authorRepository.save(author);


        publisher = new Publisher(); // Initialize the publisher object first

        publisher.setEmail("famous@gmail.com");
        publisher.setPhone("201-213-0657");
        publisher.setCity("Fort Lee");
        publisher.setStreet("GoodVibes St.");
        publisher.setState("NJ");
        publisher.setPostalCode("07456");
        publisher.setName("Penguin House");

        publisher = publisherRepository.save(publisher);
    }

    // None of them work give an error
    @Test
    public void shouldAddAuthor() {
        Optional<Author> addAuthor = authorRepository.findById(author.getId());
        assertEquals(addAuthor.get(), author);
    }

    @Test
    public void shouldUpdateAuthor() {
        author.setState("NM");

        // Fetch the associated books
        List<Book> books = bookRepository.findByAuthorId(author.getId());

        // Save the updated author
        authorRepository.save(author);

        // Verify the update
        Optional<Author> updateAuthor = authorRepository.findById(author.getId());
        assertTrue(updateAuthor.isPresent());
        assertEquals("NM", updateAuthor.get().getState());
    }

    @Test
    public void shouldDeleteAuthor() {
        authorRepository.deleteById(author.getId());
        Optional<Author> deleteAuthor = authorRepository.findById(author.getId());
        assertFalse(deleteAuthor.isPresent());
    }


    @Test
    public void shouldGetAuthorById() {
        Author getAuthorById = authorRepository.findById(author.getId()).orElse(null);
        assertEquals(author, getAuthorById);
    }
}
