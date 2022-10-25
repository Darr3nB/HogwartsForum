package com.example.HogwartsForum.services;

import com.example.HogwartsForum.daos.QuestionsDao;
import com.example.HogwartsForum.model.Question;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionService {
    QuestionsDao questionsDao;

    public void addQuestion(Question question) {
        questionsDao.save(question);
    }

    public List<Question> getAllQuestions() {
        return questionsDao.findAll();
    }
}
