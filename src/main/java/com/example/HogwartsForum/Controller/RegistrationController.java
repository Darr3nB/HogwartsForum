package com.example.HogwartsForum.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @GetMapping
    public String registration(){
        return "registration";
    }

    @PostMapping
    public String doRegister(@RequestParam String username, @RequestParam String password,
                             @RequestParam String house, @RequestParam String pet){
        return "redirect:";
    }
}
