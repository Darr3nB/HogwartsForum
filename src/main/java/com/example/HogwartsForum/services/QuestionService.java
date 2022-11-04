package com.example.HogwartsForum.services;

import com.example.HogwartsForum.daos.QuestionsDao;
import com.example.HogwartsForum.model.Question;
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

    public List<Question> getAllQuestions() {
        return questionsDao.findAll();
    }

    public Page<Question> getAllPageNumber(){
        return questionsDao.findAll(PageRequest.of(0, 10));
    }

    public List<Question> getTop5QuestionBySubmissionTime() {
        return questionsDao.findTop5ByOrderBySubmissionTimeDesc();
    }

    public Question getQuestionById(Integer id) {
        return questionsDao.getById(id);
    }

    public List<Question> getAllQuestionsPaged(int pageNumber){
        return questionsDao.findAll(PageRequest.of(pageNumber, 10)).getContent();
    }
}
