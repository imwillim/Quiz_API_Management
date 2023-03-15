
package com.example.quiz_api_management.question;


import com.example.quiz_api_management.answer.Answer;
import com.example.quiz_api_management.quiz.Quiz;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.util.List;

@Entity
@Table (name="question")
@Getter
@Setter
public class Question {
    @Id
    @SequenceGenerator(
            name = "question_sequence",
            sequenceName = "question_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "question_sequence"
    )
    @Column(name = "id", unique = true)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;

    /*
    Cascade in Hibernate is the way to achieve dependent relationship (in this case, this relationship is aggregration).
    In this case we have relationship Question-Answer, if question is deleted completely, list of answers also are deleted.
    Without Question, the Answer model does not make sense if it exists alone
    */
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers;

    /*
    While question-quiz has a many-to-one relationship,
    quiz-question only has a zero-to-one relationship
     */
    @ManyToOne()
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    public Question(String name, String type, Quiz quiz) {
        this.name = name;
        this.type = type;
        this.quiz = quiz;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    public Question(){

    }

}
