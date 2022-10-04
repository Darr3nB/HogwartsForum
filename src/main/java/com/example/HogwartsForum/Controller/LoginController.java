package com.example.HogwartsForum.Controller;

import com.example.HogwartsForum.Model.LoginParams;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String login(){
        return "login";
    }

    @PostMapping
    public String doLogin(@RequestBody LoginParams loginParams){
        System.out.println("This is login post method.");
        System.out.println("Username: " + loginParams.getUsername());
        System.out.println("Password: " + loginParams.getPassword());

        return "redirect:";
    }
}
