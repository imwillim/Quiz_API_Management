package com.example.quiz_api_management.question;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class QuestionDTO {
    private int id;

    @NotNull
    @Size(min = 3, max = 50)
    private String value;

    @NotNull
    @Size(min = 3, max = 10)
    private String type;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    private int quizId;

}
