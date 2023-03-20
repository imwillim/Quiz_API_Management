package com.example.quiz_api_management.answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController()
@RequestMapping(path = "/api/v1/")
public class AnswerController {
    private final AnswerService answerService;
    @Autowired
    public AnswerController(AnswerService answerService){
        this.answerService = answerService;
    }

    @GetMapping("questions/{questionid}/answers")
    @ResponseBody
    public List<AnswerDTO> getAnswersByQuestion(@PathVariable("questionid") int questionId){
        return answerService.getAnswersByQuestion(questionId);
    }

    @GetMapping("questions/{questionid}/answers/shuffle")
    public List<AnswerDTO> shuffleAnswers(@PathVariable("questionid") int questionId) {
        return answerService.shuffleAnswers(questionId);
    }

    @GetMapping("questions/{questionid}/answers/{answerid}")
    public AnswerDTO getAnswer(@PathVariable("questionid") int questionId,
                               @PathVariable("answerid") int answerid) {
        return answerService.getAnswer(questionId, answerid);
    }

    @PostMapping("questions/{questionid}/answers/add")
    public <K, V> void addAnswer(@PathVariable("questionid") int questionId,
                                 @RequestBody Map<K, V> reqBody) {
        answerService.addAnswer(questionId, reqBody);
    }

    @PutMapping("questions/{questionid}/answers/{answerid}/edit")
    public <K, V> void updateAnswer(@PathVariable("questionid") int questionId,
                             @PathVariable("answerid") int answerId,
                             @RequestBody Map<K, V> reqBody){
        answerService.updateAnswer(questionId, answerId, reqBody);
    }

    @DeleteMapping("questions/{questionid}/answers/{answerid}/delete")
    public void deleteAnswer(@PathVariable("questionid") int questionId,
                             @PathVariable("answerid") int answerId){
        answerService.deleteAnswer(questionId, answerId);
    }
}
