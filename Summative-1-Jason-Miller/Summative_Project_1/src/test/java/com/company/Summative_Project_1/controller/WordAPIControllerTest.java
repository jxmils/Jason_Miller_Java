package com.company.Summative_Project_1.controller;


import com.company.Summative_Project_1.model.Word;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WordController.class)
public class WordAPIControllerTest {
    // Wiring in the MockMvc object
    @Autowired
    private MockMvc mockMvc;

    // ObjectMapper used to convert Java objects to JSON and vice versa
    private ObjectMapper mapper = new ObjectMapper();

    // A list of quotes for testing purposes
    private List<Word> wordList;

    @BeforeEach
    public void setUp() {
        // Standard set up method, for instantiating test objects --> autowired
    }

    @Test
    public void getRandomWordTest() throws Exception {
//      // convert Java object to Json
        String outputJson = mapper.writeValueAsString(wordList);

        // ACT
        mockMvc.perform(get("/word")) // does the get request
                .andDo(print()) // prints the result in the console
                .andExpect(status().isOk()); // ASSERT (200 - status code)
    }
}
