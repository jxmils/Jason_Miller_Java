package com.company.Summative_Project_1.controller;

import com.company.Summative_Project_1.model.Answer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.*;

@RestController
public class Magic8BallController {

    private final List<String> answers = Arrays.asList(
            "It is certain.",
            "You may rely on it.",
            "As I see it, yes.",
            "Most likely.",
            "Outlook good.",
            "Yes.",
            "Signs point to yes.",
            "Reply hazy, try again.",
            "Ask again later.",
            "Cannot predict now.",
            "Concentrate and ask again.",
            "My sources say no.",
            "Outlook not so good.",
            "Very doubtful."
    );

    private int idCounter = 0;

    // route to use the POST method in order to get an Answer from the Magic 8 ball after giving a question
    @RequestMapping(value = "/magic", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Answer getMagic8BallAnswer(@RequestBody String question) {
        String answerText = generateRandomAnswer();
        return new Answer(idCounter++, question, answerText);
    }

    private String generateRandomAnswer() {
        Random random = new Random();
        int index = random.nextInt(answers.size());
        return answers.get(index);
    }
}
