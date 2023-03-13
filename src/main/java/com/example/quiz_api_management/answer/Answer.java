package com.example.quiz_api_management.answer;

import com.example.quiz_api_management.question.Question;
import jakarta.persistence.*;

import java.util.Optional;


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
    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

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
        this.isCorrect = correct;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isCorrect=" + isCorrect +
                ", question=" + question +
                '}';
    }
}
