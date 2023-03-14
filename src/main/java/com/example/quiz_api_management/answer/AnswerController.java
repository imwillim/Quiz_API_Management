package com.example.quiz_api_management.answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class AnswerController {
    private final AnswerService answerService;
    @Autowired
    public AnswerController(AnswerService answerService){
        this.answerService = answerService;
    }

}
