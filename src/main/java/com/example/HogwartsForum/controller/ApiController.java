package com.example.HogwartsForum.controller;

import com.example.HogwartsForum.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class ApiController {
    UserService userService;

    @GetMapping(value = "checkUsernameInDatabase/{name}")
    public Boolean checkUsernameInDatabase(@PathVariable String name) {
        return userService.countUsersByName(name);
    }
}
