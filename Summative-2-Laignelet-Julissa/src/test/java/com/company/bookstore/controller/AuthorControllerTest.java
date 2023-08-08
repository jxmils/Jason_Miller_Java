package com.company.bookstore.controller;

import com.company.bookstore.models.Author;
import com.company.bookstore.repository.AuthorRepository;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private AuthorRepository authorRepository;

    Author author = new Author();
    @BeforeEach
    public void setUp() {
        author.setFirstName("Stephanie");
        author.setLastName("Perkins");
        author.setCity("Atlanta");
        author.setState("Georgia");
        author.setPostalCode("54647");
        author.setPhone("451-765-8765");
        author.setEmail("perkins@writers.com");
        author.setStreet("1400 Georgia Oranges");
    }

    @Test
    public void shouldAddAuthor() throws Exception {
        when(authorRepository.save(any(Author.class))).thenReturn(author);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/authors")
                .content(mapper.writeValueAsString(author))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldUpdateAuthor() throws Exception {
        when(authorRepository.save(any(Author.class))).thenReturn(author);
        mockMvc.perform(MockMvcRequestBuilders
                .put("/authors")
                .content(mapper.writeValueAsString(author))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteAuthor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/authors/{id}",1))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldGetAuthorById() throws Exception {
        when(authorRepository.findById(1)).thenReturn(Optional.of(author));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/authors/{id}",1))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetAuthors() throws Exception {
        when(authorRepository.findAll()).thenReturn(Arrays.asList(author));
        mockMvc.perform(MockMvcRequestBuilders
                .get("/authors"))
                .andExpect(status().isOk());
    }
}
