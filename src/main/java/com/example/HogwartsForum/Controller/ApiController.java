package com.example.HogwartsForum.Controller;

import com.example.HogwartsForum.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class ApiController {
    UserService userService;

    @GetMapping(value = "checkUsernameInDatabase/{name}")
    public Boolean checkUsernameInDatabase(@PathVariable String name){
        return userService.countUsersByName(name);
    }
}
