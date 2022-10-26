package com.example.HogwartsForum.controller;

import com.example.HogwartsForum.model.Question;
import com.example.HogwartsForum.services.QuestionService;
import com.example.HogwartsForum.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class ApiController {
    UserService userService;
    QuestionService questionService;

    @GetMapping(value = "check-username-in-database/{name}")
    public Boolean checkUsernameInDatabase(@PathVariable String name) {
        return userService.existByName(name);
    }

    @GetMapping(value = "five-latest-question")
    public List<Question> getFiveLatestQuestions(){
        return questionService.getTop5QuestionBySubmissionTime();
    }

    @GetMapping(value = "get-all-questions")
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }
}
