package com.example.requests;


import java.util.List;
import java.util.UUID;


public class QuizRequest {

    private String name;
    private List<String> topics;

    public String getName() {
        return name;
    }

    public QuizRequest setName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getTopics() {
        return topics;
    }

    public QuizRequest setTopics(List<String> topics) {
        this.topics = topics;
        return this;
    }
}
