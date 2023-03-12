package com.example.quiz_api_management.answer;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;


public class Answer {
    @Id
    @SequenceGenerator(
            name = "answer_sequence",
            sequenceName = "answer_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "answer_sequence"
    )

    private int id;
    private String name;
    private boolean isCorrect;
    private int question_id;

    public Answer(int id, String name, boolean isCorrect, int question_id) {
        this.id = id;
        this.name = name;
        this.isCorrect = isCorrect;
        this.question_id = question_id;
    }

    public Answer(String name, boolean isCorrect, int question_id) {
        this.name = name;
        this.isCorrect = isCorrect;
        this.question_id = question_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }
}
