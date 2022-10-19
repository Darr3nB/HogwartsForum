package com.example.HogwartsForum.Repositories;

import com.example.HogwartsForum.Model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsRepository extends JpaRepository<Questions, Integer> {
}
