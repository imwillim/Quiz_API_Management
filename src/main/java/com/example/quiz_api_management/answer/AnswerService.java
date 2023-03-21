package com.example.quiz_api_management.answer;

import com.example.quiz_api_management.question.Question;
import com.example.quiz_api_management.question.QuestionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionService questionService;
    private final AnswerDTOMapper answerDTOMapper;
    @Autowired
    public AnswerService(AnswerRepository answerRepository, AnswerDTOMapper answerDTOMapper, QuestionService questionService){
        this.answerRepository = answerRepository;
        this.answerDTOMapper = answerDTOMapper;
        this.questionService = questionService;
    }

    /*
    Though IlegateStateException return a signal if function invoke at an illegal or inappropriate time, it is not a good case for this scenario
    Use EntityNotFoundException will be appropriate in this scenario, due to the meaning of NotFound.
     */

    public Optional<Question> getQuestionById(int questionId){
        Optional<Question> question = questionService.getQuestion(questionId);
        if (question.isEmpty()) {
            throw new EntityNotFoundException("Question not found.");
        }
        return question;
    }

    public List<AnswerDTO> getAnswersByQuestion(int questionId) {
        Optional<Question> question =getQuestionById(questionId);
        return answerRepository.findAnswerByQuestion(question)
                .stream()
                .map(answerDTOMapper)
                .toList();
    }

    /*
    We should shuffle list of model Answer instead of list of answerDTO, due to answerDTO will also have questionId,
    and it will be immutable (cause is maybe having 2 integers to order). It does not shuffle if the collection is immutable
     */
    public List<AnswerDTO> shuffleAnswers(int questionId) {
        Optional<Question> question = getQuestionById(questionId);
        List<Answer> answers = answerRepository.findAnswerByQuestion(question);
        Collections.shuffle(answers);
        return answers.stream().map(answerDTOMapper).toList();
    }

    public AnswerDTO getAnswer(int questionId, int answerId){
        if (getQuestionById(questionId).isEmpty()){
            throw new EntityNotFoundException("Cannot find answer due to question is not found.");
        }
        Optional <Answer> answer = answerRepository.findById(answerId);
        if(answer.isEmpty()){
            throw new EntityNotFoundException("Answer not found.");
        }
        return answer.map(answerDTOMapper).get();
    }

    public <Key, Value> void addAnswer(int questionId, Map<Key, Value> reqBody) {
        System.out.println(reqBody);
        Optional<Question> paramQuestion = getQuestionById(questionId);
        if (paramQuestion.isEmpty()) {
            throw new EntityNotFoundException("Question not found.");
        }
        Question question = paramQuestion.get();
        answerRepository.save(new Answer((String) reqBody.get("name"),
                (Boolean) reqBody.get("correct"), question));
    }


    /*
    Annotation @Transactional provokes the rollback if an exception occurs.
     */
    @Transactional
    public <Key, Value> void updateAnswer(int questionId, int answerId, Map<Key, Value> reqBody) {
        if (getAnswersByQuestion(questionId) == null){
            throw new EntityNotFoundException("Cannot find answer due to question is not found.");
        }
        Answer answer =
                answerRepository.findById(answerId)
                        .orElseThrow(() -> new EntityNotFoundException(
                                "Answer with ID" + answerId + "does not exist."
                        ));

        if(reqBody.get("name") != null) {
            answer.setName((String) reqBody.get("name"));
            answer.setCorrect((boolean) reqBody.get("correct"));
        }
    }

    public void deleteAnswer(int questionId, int answerId) {
        if (getAnswersByQuestion(questionId) == null){
            throw new IllegalStateException("Cannot find answer due to question is not found.");
        }
        boolean exists = answerRepository.existsById(answerId);
        if (!exists) {
            throw new IllegalStateException(
                    "Answer with id" + answerId + "is not valid.");
        }
        answerRepository.deleteById(answerId);
    }
}
