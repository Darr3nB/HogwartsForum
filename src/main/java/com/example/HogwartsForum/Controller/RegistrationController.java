package com.example.HogwartsForum.Controller;

import com.example.HogwartsForum.Model.RegistrationParams;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @GetMapping
    public String registration(){
        return "registration";
    }

    @PostMapping
    public String doRegister(@RequestBody RegistrationParams registrationParams){
        System.out.println("This is registration post method.");
        System.out.println("Username :" + registrationParams.getUsername());
        System.out.println("Password :" + registrationParams.getPassword());
        System.out.println("House :" + registrationParams.getHouse());
        System.out.println("Pet : " + registrationParams.getPet());

        return "redirect:";
    }
}
