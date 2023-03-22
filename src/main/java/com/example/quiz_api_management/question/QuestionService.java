package com.example.quiz_api_management.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    @Autowired
    public QuestionService(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    public List<Question> getQuestions(){
        return questionRepository.findAll();
    }

    public Optional<Question> getQuestion(int questionId) {
        return questionRepository.findById(questionId);
    }
}
