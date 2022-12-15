package com.HogwartsForum.controller;

import com.HogwartsForum.model.Question;
import com.HogwartsForum.services.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor
@RequestMapping("/question")
public class QuestionController {
    QuestionService questionService;

    @PostMapping("post-question")
    public HttpEntity<Void> postQuestion(@RequestBody Question question) {
        if (question.getTitle().length() < 5 || question.getQuestionText().length() < 5) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Question newQuestion = new Question(question.getTitle(), question.getQuestionText());
        questionService.addQuestion(newQuestion);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
