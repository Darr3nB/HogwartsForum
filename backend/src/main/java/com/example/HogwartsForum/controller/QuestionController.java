package com.example.HogwartsForum.controller;

import com.example.HogwartsForum.model.Question;
import com.example.HogwartsForum.services.QuestionService;
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
@RequestMapping("/post-question")
public class QuestionController {
    QuestionService questionService;

    @GetMapping
    public String postQuestionPage(Model model, HttpServletRequest request) {
        String username = null;
        Cookie[] cookies = request.getCookies();

        for (Cookie temp : cookies) {
            if ("hfUsername".equals(temp.getName())) {
                username = temp.getValue();
            }
        }

        model.addAttribute("username", username);
        return "post-question";
    }

    @PostMapping
    public HttpEntity<Void> postQuestion(@RequestBody Question question) {
        if (question.getTitle().length() < 5 || question.getQuestionText().length() < 5) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Question newQuestion = new Question(question.getTitle(), question.getQuestionText());
        questionService.addQuestion(newQuestion);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(value = "/specific-question/{id}")
    public String openSelectedQuestionPage(@PathVariable Integer id, Model model) {
        try {
            Question foundQuestion = questionService.getQuestionById(id);
            model.addAttribute("question", foundQuestion);
        } catch (javax.persistence.EntityNotFoundException e) {
            return "error";
        } catch (Exception e) {
            System.out.println("An error has occurred: " + e);
            return "error";
        }

        return "single-question";
    }
}