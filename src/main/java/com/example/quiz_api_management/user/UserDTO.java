package com.example.quiz_api_management.user;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

// Purpose is to not show password field in response return for security
@Data
@AllArgsConstructor
public class UserDTO {
    private int id;
    private String userName;
    private String email;

    @Size(min = 3, max = 20)
    private String firstName;

    @Size(min = 3, max = 20)
    private String lastName;
    private LocalDate birthday;

    private LocalDate createdAt;
    private LocalDate updatedAt;
    private boolean isDeleted;
}
