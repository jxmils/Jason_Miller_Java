package com.company.bookstore.controller;

import com.company.bookstore.models.Book;
import com.company.bookstore.models.Publisher;
import com.company.bookstore.models.Author;
import com.company.bookstore.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper  mapper = new ObjectMapper();

    @MockBean
    private BookRepository bookRepository;

    Book book = new Book();
    Author author = new Author();
    @BeforeEach
    public void setUp() {
        Publisher publisher = new Publisher();
        publisher.setEmail("famous@gmail.com");
        publisher.setPhone("201-213-0657");
        publisher.setCity("Fort Lee");
        publisher.setState("New Jersey");
        publisher.setPostalCode("07456");
        publisher.setName("Penguin House");

        Author author = new Author();
        author.setFirstName("Stephanie");
        author.setLastName("Perkins");
        author.setCity("Atlanta");
        author.setState("Georgia");
        author.setPostalCode("54647");
        author.setPhone("451-765-8765");
        author.setEmail("perkins@writers.com");

        book.setIsbn("10ave");
        book.setPublishDate("October 31, 2022");
        book.setPrice(12.99);
        book.setTitle("Live your life");
        book.setAuthorId(author.getId());
        book.setPublisherId(publisher.getId());
    }

    @Test
    public void shouldAddBook() throws Exception {
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/books")
                .content(mapper.writeValueAsString(book))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldUpdateBook() throws Exception {
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        mockMvc.perform(MockMvcRequestBuilders
                .put("/books")
                .content(mapper.writeValueAsString(book))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/books/{id}", 1))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldGetBookByID() throws Exception {
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/books/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetBooks() throws Exception {
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/books"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetBooksWithAuthorId() throws Exception {
        // Mock the behavior of bookRepository.findByAuthorId
        when(bookRepository.findByAuthorId(author.getId())).thenReturn(Arrays.asList(book));

        // Perform the mockMvc request with the authorId as a path variable
        mockMvc.perform(MockMvcRequestBuilders.get("/books/byAuthor/{authorId}", author.getId()))
                .andExpect(status().isOk());
    }
}
