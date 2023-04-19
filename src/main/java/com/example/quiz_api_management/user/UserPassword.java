package com.example.quiz_api_management.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserPassword {
    @NotNull
    @Size(min = 5, max = 30)
    private String password;

    public UserPassword(String password) {
        this.password = password;
    }

    public UserPassword(){

    }
}
