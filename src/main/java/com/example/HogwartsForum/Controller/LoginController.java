package com.example.HogwartsForum.Controller;

import com.example.HogwartsForum.Model.LoginParams;
import com.example.HogwartsForum.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/login")
public class LoginController {
    UserService userService;

    @PostMapping
    public HttpEntity<Void> doLogin(@RequestBody LoginParams loginParams) {
        if (!userService.validateLogin(loginParams.getUsername(), loginParams.getPassword())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        System.out.println("Valid login");
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
