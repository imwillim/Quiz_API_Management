package com.example.quiz_api_management.answer;

import com.example.quiz_api_management.question.Question;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Table (name ="answer")
/*
 These annotations belong to lombok, which can generate setter and getter methods automatically
 Moreover, it reduces verbosity of the code and avoid repetition.
 */
@Getter
@Setter
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

    @Transient
    @Column(name="is_removed", nullable = false)
    private boolean isRemoved;
    @ManyToOne
    @JoinColumn(name = "question_id")
    /*
    JsonManageReference used to avoid infinite recursion
     */
    // @JsonManagedReference(value = "id") - If it exists, it simply cannot create Answer.
    private Question question;

    /*
    Change LocalDate to LocalDateTime to gain full information related to time.
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Answer(String name, boolean isCorrect, Question question) {
        this.name = name;
        this.isCorrect = isCorrect;
        this.isRemoved = false;
        this.question = question;
        this.createdAt = LocalDateTime.now(); // Initialized
        this.updatedAt = LocalDateTime.now(); // Initialized
    }

    public Answer() {
    }

}
