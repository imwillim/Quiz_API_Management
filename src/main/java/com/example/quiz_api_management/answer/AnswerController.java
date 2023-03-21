package com.example.quiz_api_management.answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    /*
    ok() method actually returns HTTP status OK.
    200 - OK returns when the request succeeded, and is allowed in GET, HEAD, PUT, POST & TRACE
     */
    @GetMapping("questions/{questionid}/answers")
    @ResponseBody
    public ResponseEntity<List<AnswerDTO>> getAnswersByQuestion(@PathVariable("questionid") int questionId){
        List<AnswerDTO> answerDTOS = answerService.getAnswersByQuestion(questionId);
        if(answerDTOS.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok().body(answerDTOS);
    }

    @GetMapping("questions/{questionid}/answers/shuffle")
    public ResponseEntity<List<AnswerDTO>> shuffleAnswers(@PathVariable("questionid") int questionId) {
        return ResponseEntity.ok().body(answerService.shuffleAnswers(questionId));
    }

    @GetMapping("questions/{questionid}/answers/{answerid}")
    public ResponseEntity<AnswerDTO> getAnswer(@PathVariable("questionid") int questionId,
                               @PathVariable("answerid") int answerid) {
        return ResponseEntity.ok().body(answerService.getAnswer(questionId, answerid));
    }

    /*
    <Key, Value> here is actually a generic used for RequestBody parameter. Generic will help the method reuse objects of different types.
    In this case, I use generics due to the request body is a Json object which are not constant primitive types.
     */

    /*
    201 - Created returns when the request succeeded, and a new resource was created as a result.
     */
    @PostMapping("questions/{questionid}/answers/add")
    public <Key, Value> ResponseEntity<Void> addAnswer(@PathVariable("questionid") int questionId,
                                         @RequestBody Map<Key, Value> reqBody){
        answerService.addAnswer(questionId, reqBody);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("questions/{questionid}/answers/{answerid}/edit")
    public <Key, Value> ResponseEntity<Void> updateAnswer(@PathVariable("questionid") int questionId,
                             @PathVariable("answerid") int answerId,
                             @RequestBody Map<Key, Value> reqBody){
        answerService.updateAnswer(questionId, answerId, reqBody);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }


    /*
    HttpStatus - 204 - No Content means that there is no content to send in this request.
     */
    @DeleteMapping("questions/{questionid}/answers/{answerid}/delete")
    public ResponseEntity<Void> deleteAnswer(@PathVariable("questionid") int questionId,
                                   @PathVariable("answerid") int answerId){
        answerService.deleteAnswer(questionId, answerId);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }
}
