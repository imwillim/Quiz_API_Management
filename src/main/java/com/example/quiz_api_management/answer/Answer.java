package com.example.quiz_api_management.answer;

import com.example.quiz_api_management.question.Question;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table (name ="answer")
public class Answer {
    @Id
    /*
      Annotation SequenceGenerator is provoked for defining primary keys.
      allocationSize is used for how many sequences in memory are allocated to the database
     */
    @SequenceGenerator(
            name = "answer_sequence",
            sequenceName = "answer_sequence",
            allocationSize = 1
    )

    /*
      Annotation GeneratedValue is provoked for generating value of a primary key
     */
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "answer_sequence"
    )

    @Column(name="id", unique = true)
    private int id;
    @Column(name="name", nullable = false)
    private String name;
    @Column(name="is_correct", nullable = false)
    private boolean isCorrect;
    @Column(name="is_removed", nullable = false)
    private boolean isRemoved;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;

    public Answer(String name, boolean isCorrect, boolean isRemoved, Question question, LocalDate createdAt, LocalDate updatedAt) {
        this.name = name;
        this.isCorrect = isCorrect;
        this.isRemoved = isRemoved;
        this.question = question;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Answer() {
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

    public boolean isRemoved() {
        return isRemoved;
    }

    public void setRemoved(boolean removed) {
        isRemoved = removed;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
}
