package com.example.HogwartsForum.controller;

import com.example.HogwartsForum.model.Question;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor
@RequestMapping("/post-question")
public class QuestionController {

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
    public HttpEntity<Void> postQuestion(@RequestBody Question question){
        if (question.getTitle().length() < 5 || question.getQuestionText().length() < 5){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
