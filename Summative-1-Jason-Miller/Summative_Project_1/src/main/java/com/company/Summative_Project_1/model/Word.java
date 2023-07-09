package com.company.Summative_Project_1.model;

//provides the ability to create a unique word that includes two more characteristics --> id and definition
public class Word {
    private int id;
    private String word;
    private String defintion;

    public Word (int id, String word, String defintion) {
        this.id = id;
        this.word = word;
        this.defintion = defintion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefintion() {
        return defintion;
    }

    public void setDefintion(String defintion) {
        this.defintion = defintion;
    }
}
