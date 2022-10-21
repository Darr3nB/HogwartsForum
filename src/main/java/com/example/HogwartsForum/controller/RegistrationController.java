package com.example.HogwartsForum.controller;

import com.example.HogwartsForum.model.HogwartsUser;
import com.example.HogwartsForum.model.RegistrationModel;
import com.example.HogwartsForum.security.PasswordAgent;
import com.example.HogwartsForum.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {
    UserService userService;
    PasswordAgent passwordAgent;

    @GetMapping
    public String registration() {
        return "registration";
    }

    @PostMapping
    public HttpEntity<Void> doRegister(@RequestBody RegistrationModel newRegistration) {
        if (!newRegistration.variableValidationForRegistration()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        HogwartsUser newUser = new HogwartsUser(newRegistration.getUsername(), passwordAgent.hashPassword(newRegistration.getPassword()),
                newRegistration.getHouse(), newRegistration.getPetType());
        userService.addUser(newUser);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
