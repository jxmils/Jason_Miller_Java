package com.company.Summative_Project_1.model;

//provides the ability to create a unique answer from magic 8 ball that includes two more characteristics --> id and question
public class Answer {
    private int id;
    private String question;
    private String answer;

    // Constructors
    public Answer() {}

    public Answer(int id, String question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
