package com.company.bookstore.repository;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Book;
import com.company.bookstore.models.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PublisherRepositoryTest {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    PublisherRepository publisherRepository;
    private Publisher publisher;

    @BeforeEach
    public void setUp() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        publisherRepository.deleteAll();

        publisher = new Publisher(); // Initialize the publisher object first

        publisher.setEmail("famous@gmail.com");
        publisher.setPhone("201-213-0657");
        publisher.setCity("Fort Lee");
        publisher.setStreet("GoodVibes St.");
        publisher.setState("NJ");
        publisher.setPostalCode("07456");
        publisher.setName("Penguin House");
        publisher.setBooks(new ArrayList<>());

        publisher = publisherRepository.save(publisher); // Save the publisher after setting its attribute
    }

    @Test
    public void shouldAddPublisher() {
        Optional<Publisher> addPublisher = publisherRepository.findById(publisher.getId());
        assertEquals(addPublisher.get(), publisher);
    }

    @Test
    public void shouldUpdatePublisher() {
        publisher.setCity("Laughter City");
        publisherRepository.save(publisher);

        Optional<Publisher> updatePublisher = publisherRepository.findById(publisher.getId());
        assertEquals(updatePublisher.get(), publisher);
    }

    @Test
    public void shouldDeletePublisher() {
        publisherRepository.deleteById(publisher.getId());
        Optional<Publisher> deletePublisher = publisherRepository.findById(publisher.getId());
        assertFalse(deletePublisher.isPresent());
    }

    @Test
    public void shouldGetPublisherById() {
        Publisher getPublisherById = publisherRepository.findById(publisher.getId()).orElse(null);
        assertEquals(publisher, getPublisherById);
    }
}

//get all