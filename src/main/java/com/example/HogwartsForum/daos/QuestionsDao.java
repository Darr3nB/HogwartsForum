package com.example.HogwartsForum.daos;

import com.example.HogwartsForum.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsDao extends JpaRepository<Question, Integer> {
}
