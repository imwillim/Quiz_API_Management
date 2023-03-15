package com.example.quiz_api_management.quiz;

import com.example.quiz_api_management.question.Question;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "quiz")
@Getter
@Setter
public class Quiz {
    @Id
    @SequenceGenerator(
            name = "quiz_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "quiz_sequence"
    )

    @Column(name = "id", nullable = false)
    private int id;
    @Column(name="name", nullable = false)
    private String name;
    @Column(name = "created_at")
    private LocalDate createdAt;
    @Column(name = "updated_at")
    private LocalDate updatedAt;

    public Quiz(String name) {
        this.name = name;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    public Quiz() {
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
