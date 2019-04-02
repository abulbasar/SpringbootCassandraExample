package com.example.controllers;


import com.example.entities.QuizEntity;
import com.example.requests.QuizRequest;
import com.example.responses.CollectionType;
import com.example.responses.QuizResponse;
import com.example.services.QuizService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @GetMapping
    public ResponseEntity<CollectionType<QuizResponse>> getAll(){
        List<QuizEntity> all = quizService.getAll();

        List<QuizResponse> records = new ArrayList<>();
        all.stream().forEach(r -> {
            QuizResponse response = new QuizResponse();
            BeanUtils.copyProperties(r, response);
            records.add(response);
        });
        return new ResponseEntity<>(new CollectionType<>(records), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<QuizResponse> create(@RequestBody QuizRequest request){
        QuizEntity entity = quizService.create(request);
        QuizResponse response = quizService.toReponse(entity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping(value = "/{quizId}")
    public ResponseEntity<QuizResponse> update(@PathVariable UUID quizId
            , @RequestBody QuizRequest request){

        QuizEntity entity = quizService.update(quizId, request);
        QuizResponse response = quizService.toReponse(entity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{quizId}")
    public ResponseEntity<Boolean> delete(@PathVariable UUID quizId){
        quizService.delete(quizId);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping(value = "/{quizId}")
    public ResponseEntity<QuizResponse> getById(@PathVariable UUID quizId){
        QuizEntity entity = quizService.getById(quizId);
        QuizResponse response = quizService.toReponse(entity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
