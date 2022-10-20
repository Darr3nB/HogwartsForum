package com.example.HogwartsForum.Controller;

import com.example.HogwartsForum.Model.HogwartsUser;
import com.example.HogwartsForum.Model.RegistrationModel;
import com.example.HogwartsForum.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {
    UserService userService;

    @GetMapping
    public String registration(){
        return "registration";
    }

    @PostMapping
    public String doRegister(@RequestBody RegistrationModel newRegistration){
        HogwartsUser newUser = new HogwartsUser(newRegistration.getUsername(), newRegistration.getPassword(),
                                                newRegistration.getHouse(), newRegistration.getPetType());
        userService.addUser(newUser);

        return "redirect:";
    }
}
