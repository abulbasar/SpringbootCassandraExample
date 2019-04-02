package com.example.repositories;


import com.example.entities.QuizEntity;
import org.springframework.data.cassandra.core.mapping.MapId;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends CassandraRepository<QuizEntity, MapId> {

}
