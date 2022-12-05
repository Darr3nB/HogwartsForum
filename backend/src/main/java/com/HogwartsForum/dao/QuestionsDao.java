package com.HogwartsForum.dao;

import com.HogwartsForum.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionsDao extends JpaRepository<Question, Integer> {
    List<Question> findTop5ByOrderBySubmissionTimeDesc();
}
