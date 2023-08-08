package com.company.bookstore.controller;

import com.company.bookstore.models.Book;
import com.company.bookstore.models.Author;
import com.company.bookstore.models.Publisher;
import com.company.bookstore.repository.AuthorRepository;
import com.company.bookstore.repository.BookRepository;
import com.company.bookstore.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class GraphController {

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @QueryMapping
    public Book findBookById(@Argument int id) {
        return bookRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public List<Book> findBooksByAuthorId(@Argument int authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

    @QueryMapping
    public Author findAuthorById(@Argument int id) {
        return authorRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public Publisher findPublisherById(@Argument int id) {
        Optional<Publisher> optionalPublisher = publisherRepository.findById(id);
        if (optionalPublisher.isPresent()) {
            Publisher publisher = optionalPublisher.get();
            List<Book> books = bookRepository.findByPublisherId(publisher.getId());
            publisher.setBooks(books);
            return publisher;
        }
        return null;
    }

    @QueryMapping
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }

    @MutationMapping
    public Publisher addPublisher (
            @Argument String name,
            @Argument String street,
            @Argument String city,
            @Argument String state,
            @Argument String postalCode,
            @Argument String phone,
            @Argument String email
    ) {
        Publisher newPublisher = new Publisher(name, street, city, state, postalCode, phone, email);

        // Fetch the list of books associated with the new publisher's ID (which will be generated upon saving)
        List<Book> publisherBooks = bookRepository.findByPublisherId(newPublisher.getId());
        newPublisher.setBooks(publisherBooks);

        return publisherRepository.save(newPublisher);
    }


    @MutationMapping
    public void deletePublisherById(@Argument int id) {
        publisherRepository.deleteById(id);
    }

    @MutationMapping
    public Author addAuthor (
            @Argument String firstName,
            @Argument String lastName,
            @Argument String street,
            @Argument String city,
            @Argument String state,
            @Argument String postalCode,
            @Argument String phone,
            @Argument String email
    ) {

        Author newAuthor = new Author(firstName, lastName, street, city, state, postalCode, phone, email);

        // Fetch the list of books associated with the new author's ID (which will be generated upon saving)
        List<Book> authorBooks = bookRepository.findByAuthorId(newAuthor.getId());
        newAuthor.setBooks(authorBooks);

        return authorRepository.save(newAuthor);
    }


    @MutationMapping
    public void deleteAuthorById(@Argument int id) {
        authorRepository.deleteById(id);
    }

    @MutationMapping
    public Book addBook(
            @Argument String isbn,
            @Argument String publishDate,
            @Argument int authorId,
            @Argument String title,
            @Argument int publisherId,
            @Argument double price
    ) {
        Book newBook = new Book(isbn, publishDate, authorId, title, publisherId, price);
        return bookRepository.save(newBook);
    }

    @MutationMapping
    public void deleteBookById(@Argument int id) {
        bookRepository.deleteById(id);
    }

    @MutationMapping
    public Publisher updatePublisher(
            @Argument int id,
            @Argument String name,
            @Argument String street,
            @Argument String city,
            @Argument String state,
            @Argument String postalCode,
            @Argument String phone,
            @Argument String email
    ) {
        Publisher updatePublisher = publisherRepository.findById(id).orElseThrow();
        updatePublisher.setName(name);
        updatePublisher.setStreet(street);
        updatePublisher.setCity(city);
        updatePublisher.setState(state);
        updatePublisher.setPostalCode(postalCode);
        updatePublisher.setPhone(phone);
        updatePublisher.setEmail(email);
        return publisherRepository.save(updatePublisher);
    }

    @MutationMapping
    public Author updateAuthor(
            @Argument int id,
            @Argument String firstName,
            @Argument String lastName,
            @Argument String street,
            @Argument String city,
            @Argument String state,
            @Argument String postalCode,
            @Argument String phone,
            @Argument String email
    ) {

        Author updateAuthor = authorRepository.findById(id).orElseThrow();

        updateAuthor.setFirstName(firstName);
        updateAuthor.setLastName(lastName);
        updateAuthor.setStreet(street);
        updateAuthor.setCity(city);
        updateAuthor.setState(state);
        updateAuthor.setPostalCode(postalCode);
        updateAuthor.setPhone(phone);
        updateAuthor.setEmail(email);
        return authorRepository.save(updateAuthor);
    }


    @MutationMapping
    public Book updateBook(
            @Argument int id,
            @Argument String isbn,
            @Argument String publishDate,
            @Argument int authorId,
            @Argument String title,
            @Argument int publisherId,
            @Argument double price
    ) {
        // Check if Author and Publisher exist based on the provided IDs
        Author author = authorRepository.findById(authorId).orElseThrow();
        Publisher publisher = publisherRepository.findById(publisherId).orElseThrow();
        Book updateBook = bookRepository.findById(id).orElseThrow();

        updateBook.setIsbn(isbn);
        updateBook.setPublishDate(publishDate);
        updateBook.setAuthorId(authorId);
        updateBook.setTitle(title);
        updateBook.setPublisherId(publisherId);
        updateBook.setPrice(price);

        return bookRepository.save(updateBook);
    }

    @SchemaMapping
    public Author author (Book book) {
        Optional<Author> returnVal = authorRepository.findById(book.getAuthorId());
        if (returnVal.isPresent()) {
            return returnVal.get();
        }else {
            return null;

        }
    }

    @SchemaMapping
    public Publisher publisher (Book book) {
        Optional<Publisher> returnVal = publisherRepository.findById(book.getPublisherId());
        if (returnVal.isPresent()) {
            return returnVal.get();
        }else {
            return null;

        }
    }

}
