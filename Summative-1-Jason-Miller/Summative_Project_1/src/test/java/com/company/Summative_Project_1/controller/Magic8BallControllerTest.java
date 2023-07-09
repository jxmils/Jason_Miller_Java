package com.company.Summative_Project_1.controller;

import com.company.Summative_Project_1.model.Answer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(Magic8BallController.class)
public class Magic8BallControllerTest {
    // Wiring in the MockMvc object
    @Autowired
    private MockMvc mockMvc;

    // ObjectMapper to convert between JSON
    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        // Standard set up method, for instantiating test objects --> autowired
    }

    @Test
    public void getMagic8BallAnswer() throws Exception {
        // Question
        String question = "Will it rain today?";

        String outputJson = mapper.writeValueAsString(question);


        mockMvc.perform(MockMvcRequestBuilders.post("/magic")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(question))
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }
    @Test
    public void getMagic8BallAnswerWithEmptyQuestion() throws Exception {
        // Question
        String question = "";

        String outputJson = mapper.writeValueAsString(question);


        mockMvc.perform(MockMvcRequestBuilders.post("/magic")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(question))
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }
}
