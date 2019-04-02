package com.example.entities;


import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;
import java.util.UUID;


@Table("quiz")
public class QuizEntity {

    @PrimaryKey("quiz_id")
    UUID quizId;

    @Column("name")
    private String name;

    @Column("topics")
    private List<String> topics;

    public UUID getQuizId() {
        return quizId;
    }

    public QuizEntity setQuizId(UUID quizId) {
        this.quizId = quizId;
        return this;
    }

    public String getName() {
        return name;
    }

    public QuizEntity setName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getTopics() {
        return topics;
    }

    public QuizEntity setTopics(List<String> topics) {
        this.topics = topics;
        return this;
    }
}
