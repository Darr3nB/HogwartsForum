package com.example.HogwartsForum.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationModel {
    private String username;
    private String password;
    private String passwordAgain;
    private String house;
    private String petType;

    public Boolean variableValidationForRegistration() {
        return username.length() >= 5 && password.length() >= 5 && passwordAgain.length() >= 5 && password.equals(passwordAgain)
                && !house.equals("noneSelected") && !petType.equals("noneSelected");
    }
}
