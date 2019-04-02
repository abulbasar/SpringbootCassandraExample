package com.example.responses;


import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;
import java.util.UUID;


public class QuizResponse {

    private UUID quizId;
    private String name;
    private List<String> topics;

    public UUID getQuizId() {
        return quizId;
    }

    public QuizResponse setQuizId(UUID quizId) {
        this.quizId = quizId;
        return this;
    }

    public String getName() {
        return name;
    }

    public QuizResponse setName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getTopics() {
        return topics;
    }

    public QuizResponse setTopics(List<String> topics) {
        this.topics = topics;
        return this;
    }
}
