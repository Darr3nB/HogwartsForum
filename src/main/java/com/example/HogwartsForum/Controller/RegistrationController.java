package com.example.HogwartsForum.Controller;

import com.example.HogwartsForum.Model.User;
import com.example.HogwartsForum.Services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    UserService userService;

    @GetMapping
    public String registration(){
        return "registration";
    }

    @PostMapping
    public String doRegister(@RequestBody User newUser){
        System.out.println("This is registration post method.");
        System.out.println("Username :" + newUser.getName());
        System.out.println("Password :" + newUser.getPassword());
        System.out.println("House :" + newUser.getHouse());
        System.out.println("Pet : " + newUser.getPet());
        userService.addUser(newUser);

        return "redirect:";
    }
}
