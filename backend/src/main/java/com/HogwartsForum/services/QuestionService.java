package com.HogwartsForum.services;

import com.HogwartsForum.dao.QuestionsDao;
import com.HogwartsForum.model.Question;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionService {
    QuestionsDao questionsDao;

    public void addQuestion(Question question) {
        questionsDao.save(question);
    }

    public Page<Question> getAllQuestions() {
        return questionsDao.findAll(PageRequest.of(0, 10));
    }

    public List<Question> getTop5QuestionBySubmissionTime() {
        return questionsDao.findTop5ByOrderBySubmissionTimeDesc();
    }

    public Question getQuestionById(Integer id) {
        return questionsDao.getById(id);
    }
}
