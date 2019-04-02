package com.example.services;


import com.datastax.driver.core.utils.UUIDs;
import com.example.entities.QuizEntity;
import com.example.repositories.QuizRepository;
import com.example.requests.QuizRequest;
import com.example.responses.QuizResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.mapping.BasicMapId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class QuizService {


    @Autowired
    QuizRepository quizRepository;

    public List<QuizEntity> getAll(){
        return quizRepository.findAll();
    }

    public QuizEntity create(QuizRequest request) {

        QuizEntity entity = new QuizEntity();
        BeanUtils.copyProperties(request, entity);
        entity.setQuizId(UUIDs.timeBased());
        quizRepository.save(entity);
        return entity;
    }

    public QuizResponse toReponse(QuizEntity entity){
        QuizResponse response = new QuizResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
    public QuizEntity getById(UUID id){
        return quizRepository.findById(BasicMapId.id("quizId", id)).get();
    }

    public QuizEntity update(UUID id, QuizRequest request){
        QuizEntity entity = getById(id);
        BeanUtils.copyProperties(request, entity);
        quizRepository.save(entity);
        return entity;
    }

    public void delete(UUID id){
        QuizEntity entity = getById(id);
        quizRepository.delete(entity);
    }




}
