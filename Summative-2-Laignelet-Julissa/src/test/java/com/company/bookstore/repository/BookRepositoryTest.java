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
public class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    PublisherRepository publisherRepository;
    private Book book;
    private Author author;
    @BeforeEach
    public void setUp() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        publisherRepository.deleteAll();

        book = new Book();

        Publisher publisher = new Publisher();
        publisher.setEmail("famous@gmail.com");
        publisher.setPhone("201-213-0657");
        publisher.setCity("Fort Lee");
        publisher.setState("NJ");
        publisher.setPostalCode("07456");
        publisher.setName("Penguin House");
        publisher.setStreet("Penguin St.");
        publisher.setBooks(new ArrayList<>());
        publisher = publisherRepository.save(publisher);

        author = new Author();
        author.setFirstName("Stephanie");
        author.setLastName("Perkins");
        author.setCity("Atlanta");
        author.setStreet("Penguin St.");
        author.setState("GA");
        author.setPostalCode("54647");
        author.setPhone("451-765-8765");
        author.setEmail("perkins@writers.com");
        author.setBooks(new ArrayList<>());

        author = authorRepository.save(author);

        book.setIsbn("978-1234567890");
        book.setPublishDate("2023-08-06");
        book.setPrice(12.99);
        book.setTitle("Live your life");
        book.setAuthorId(author.getId());
        book.setPublisherId(publisher.getId());

        book = bookRepository.save(book);
    }

    @Test
    public void shouldAddBook() {
        Optional<Book> addBook = bookRepository.findById(book.getId());
        assertEquals(addBook.get(), book);
    }

    @Test
    public void shouldUpdateBook() {
        book.setIsbn("HappyTimes");
        bookRepository.save(book);

        Optional<Book> updateBook = bookRepository.findById(book.getId());
        assertEquals(updateBook.get(), book);
    }

    @Test
    public void shouldDeleteBook() {
        bookRepository.deleteById(book.getId());
        Optional<Book> deleteBook = bookRepository.findById(book.getId());
        assertFalse(deleteBook.isPresent());
    }

    @Test
    public void shouldGetBookById() {
        Book getBookById = bookRepository.findById(book.getId()).orElse(null);
        assertEquals(book, getBookById);
    }

    @Test
    public void shouldGetBooksByAuthor() {
        Publisher publisher = new Publisher();
        publisher.setEmail("famoso@gmail.com");
        publisher.setPhone("201-214-0657");
        publisher.setCity("Orlanda");
        publisher.setStreet("GoodVibes St.");
        publisher.setState("FL");
        publisher.setPostalCode("074568");
        publisher.setName("Florida House");

        publisherRepository.save(publisher); // Save the publisher before using it in the book

        Book book2 = new Book();
        book2.setIsbn("LifeIsGood");
        book2.setAuthorId(author.getId());
        book2.setTitle("You got this!");
        book2.setPrice(10.98);
        book2.setPublishDate("2023-08-06");
        book2.setPublisherId(publisher.getId()); // Set the saved publisher in the book

        authorRepository.save(author);
        bookRepository.save(book2);

        List<Book> books = bookRepository.findByAuthorId(author.getId());
        assertTrue(books.contains(book));
        assertTrue(books.contains(book2));
    }
}

//must add to find all books
