package com.example.quiz_api_management.answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    private AnswerRepository answerRepository;
    @Autowired
    public AnswerService(AnswerRepository answerRepository){
        this.answerRepository = answerRepository;
    }

}
