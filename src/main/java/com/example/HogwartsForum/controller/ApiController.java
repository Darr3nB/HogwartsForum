package com.example.HogwartsForum.controller;

import com.example.HogwartsForum.model.Question;
import com.example.HogwartsForum.services.QuestionService;
import com.example.HogwartsForum.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping(value = "get-all-questions-filtered/")
    public void getAllQuestionsFiltered(@RequestParam(name = "title") String title, @RequestParam(name = "description") String description){
        // TODO finish this method
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
    }
}
