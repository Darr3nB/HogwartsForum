package com.example.HogwartsForum.Controller;

import com.example.HogwartsForum.Model.HogwartsUser;
import com.example.HogwartsForum.Model.RegistrationModel;
import com.example.HogwartsForum.Security.PasswordAgent;
import com.example.HogwartsForum.Services.UserService;
import lombok.AllArgsConstructor;
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
    public String doRegister(@RequestBody RegistrationModel newRegistration) {
        HogwartsUser newUser = new HogwartsUser(newRegistration.getUsername(), passwordAgent.hashPassword(newRegistration.getPassword()),
                newRegistration.getHouse(), newRegistration.getPetType());
        userService.addUser(newUser);

        return "redirect:";
    }
}
