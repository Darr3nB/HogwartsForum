package com.example.HogwartsForum.Controller;

import com.example.HogwartsForum.Model.LoginParams;
import com.example.HogwartsForum.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/login")
public class LoginController {
    UserService userService;
    @PostMapping
    public String doLogin(@RequestBody LoginParams loginParams){
        userService.validateLogin(loginParams.getUsername());

        return "redirect:";
    }
}
