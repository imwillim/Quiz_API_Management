package com.example.quiz_api_management.answer;

import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<Answer, Integer> {
    Answer findById(int answerId);
}
