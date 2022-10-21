package com.example.HogwartsForum.daos;

import com.example.HogwartsForum.model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsDao extends JpaRepository<Questions, Integer> {
}
