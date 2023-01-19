package com.HogwartsForum.services;

import com.HogwartsForum.dao.QuestionsDao;
import com.HogwartsForum.model.Comment;
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
        return questionsDao.findQuestionById(id);
    }

    public void addCommentToQuestion(Integer questionId, Comment comment) {
        Question foundQuestionByGivenId = questionsDao.findQuestionById(questionId);
        foundQuestionByGivenId.getCommentList().add(comment);
        questionsDao.save(foundQuestionByGivenId);
    }

    public void removeCommentById(int questionId, int commentId) {
        Question foundQuestion = questionsDao.findQuestionById(questionId);

        for (Comment comment : foundQuestion.getCommentList()) {
            if (comment.getId() == commentId) {
                foundQuestion.getCommentList().remove(comment);

                questionsDao.save(foundQuestion);
            }
        }
    }

    public boolean thisQuestionHasCommentWithThisId(int questionId, int commentId) {
        Question foundQuestion = questionsDao.findQuestionById(questionId);

        if (foundQuestion.getCommentList().size() <= 0) {
            return false;
        }

        for (Comment comment : foundQuestion.getCommentList()) {
            if (comment.getId() == commentId) {
                return true;
            }
        }
        return false;
    }
}
