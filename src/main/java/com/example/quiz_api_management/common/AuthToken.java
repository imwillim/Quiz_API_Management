package com.example.quiz_api_management.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthToken {
    private String tokenType;

    private String token;

    private long expirationTime;

    public AuthToken(){

    }



}
