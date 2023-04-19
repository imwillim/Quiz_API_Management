package com.example.quiz_api_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


// 401 - Unauthorized when user does not type correctly for login.
@ResponseStatus(HttpStatus.UNAUTHORIZED)

// Credential is related to username and password
public class NotValidCredentialException extends RuntimeException{
    public NotValidCredentialException(String message){
        super(message);
    }
}
