package com.company.Summative_Project_1.controller;

import com.company.Summative_Project_1.model.Quote;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
public class QuoteController {
    List<Quote> quotes;

    public QuoteController() {
        Quote num1 = new Quote(1000, "Abby", "Correction does much, but encouragement does a lot more!");
        Quote num2 = new Quote(1001, "Beatrice", "Being angry never solves anything!");
        Quote num3 = new Quote(1002, "Candy", "Mind is everything; muscle is not!");
        Quote num4 = new Quote(1003, "Dorothy", "A beautiful thing is never perfect!");
        Quote num5 = new Quote(1004, "Evan", "The only failure in life is not to be true to the best one who knows :) ! ");
        Quote num6 = new Quote(1005, "Griffin", "From small beginnings come great things.");
        Quote num7 = new Quote(1006, "Hopkin", "Being angry never solves anything!");
        Quote num8 = new Quote(1007, "Issac", "We do what we do because we believe.");
        Quote num9 = new Quote(1008, "Jess", "No matter how hard the past, you can always begin again.");
        Quote num10 = new Quote(1009, "Kathy", "A good rest is half the work!");
        Quote num11 = new Quote(1010, "Leo", "Be you and never stop!");

        quotes = Arrays.asList(num1, num2, num3, num4, num5, num6, num7, num8, num9, num10, num11);
    }

    //route to GET a quote of the day without input
    @RequestMapping(value = "/quote", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Quote getRandomDayQuote() {
        Random random = new Random();
        int index = random.nextInt(quotes.size());
        return quotes.get(index);
    }
}

