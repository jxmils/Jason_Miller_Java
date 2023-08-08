package com.company.bookstore.controller;

import com.company.bookstore.models.Publisher;

import com.company.bookstore.repository.AuthorRepository;
import com.company.bookstore.repository.BookRepository;
import com.company.bookstore.repository.PublisherRepository;
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
@WebMvcTest(PublisherController.class)
public class PublisherControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();
    @MockBean
    private PublisherRepository publisherRepository;
    @MockBean
    BookRepository bookRepository;
    @MockBean
    AuthorRepository authorRepository;
    Publisher publisher = new Publisher();
    @BeforeEach
    public void setUp() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        publisherRepository.deleteAll();

        publisher.setEmail("barbie@gmail.com");
        publisher.setPhone("315-645-7658");
        publisher.setCity("Hollywood");
        publisher.setState("CA");
        publisher.setPostalCode("10002");
        publisher.setStreet("1400 Barbieland");
        publisher.setName("Barbie World");
    }

    // gives an error
    @Test
    public void shouldAddPublisher() throws Exception {
        when(publisherRepository.save(any(Publisher.class))).thenReturn(publisher);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/publishers")
                        .content(mapper.writeValueAsString(publisher))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldUpdatePublisher() throws Exception {
        when(publisherRepository.save(any(Publisher.class))).thenReturn(publisher);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/publishers")
                        .content(mapper.writeValueAsString(publisher))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeletePublisher() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/publishers/{id}", 1))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldGetPublisherById() throws Exception {
        when(publisherRepository.findById(1)).thenReturn(Optional.of(publisher));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/publishers/{id}", 1))
                .andExpect(status().isOk());
    }

    //gives an error
    @Test
    public void shouldGetPublishers() throws Exception {
        when(publisherRepository.findAll()).thenReturn(Arrays.asList(publisher));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/publishers"))
                .andExpect(status().isOk());
    }
}
