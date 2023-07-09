package com.company.Summative_Project_1.controller;

import com.company.Summative_Project_1.model.Word;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
public class WordController {
    List<Word> words;

    public WordController(){
        Word word1 = new Word(100, "grenadine", "thin syrup mae from pomegranate juice");
        Word word2 = new Word(101, "pomegranate", "shrub or small tree having large red many-seeded fruit");
        Word word3 = new Word(102, "hefty", "of considerable weight and size");
        Word word4 = new Word(103, "carbasus", "lint or gauze");
        Word word5 = new Word(104, "proximo", "of next month");
        Word word6 = new Word(105, "bilious", "ill-tempered; very unpleasant");
        Word word7 = new Word(106, "mnemon", "unit of memory, largely hyptheical");
        Word word8 = new Word(107, "wheeple", "to whistle feebly");
        Word word9 = new Word(108, "miosis", "abnormal contraction of the pupil of the eye");
        Word word10 = new Word(109, "damine", "having antlers like a fallow deer");
        Word word11 = new Word(110, "futurology", "study of future");
        words = Arrays.asList(word1, word2, word3, word4, word5, word6, word7, word8, word9, word10, word11);
    }

    //route to GET a word of the day without input
    @RequestMapping(value = "/word", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Word getRandomDayWord() {
        Random random = new Random();
        int index = random.nextInt(words.size());
        return words.get(index);
    }
}
