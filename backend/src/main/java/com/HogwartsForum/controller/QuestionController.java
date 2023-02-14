package com.HogwartsForum.controller;

import com.HogwartsForum.model.Question;
import com.HogwartsForum.services.QuestionService;
import com.HogwartsForum.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/question")
public class QuestionController {
    QuestionService questionService;
    UserService userService;
    UserController userController;

    @PostMapping("post-question/{id}")
    public HttpEntity<Void> postQuestion(@RequestBody Question question, @PathVariable String id) {
        if (question.getTitle().length() < 5 || question.getQuestionText().length() < 5) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Question newQuestion = new Question(question.getTitle(), question.getQuestionText(), question.getImage());

        questionService.addQuestion(newQuestion);
        userService.saveQuestionToUserSet(Integer.parseInt(id), newQuestion);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
