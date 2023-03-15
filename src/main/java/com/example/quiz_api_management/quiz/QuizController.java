package com.example.quiz_api_management.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/quizzes")
public class QuizController {
    private final QuizService quizService;
    @Autowired
    public QuizController(QuizService quizService){
        this.quizService = quizService;
    }
    public List<Quiz> getQuizzes(){
        return quizService.getQuizzes();
    }
}
