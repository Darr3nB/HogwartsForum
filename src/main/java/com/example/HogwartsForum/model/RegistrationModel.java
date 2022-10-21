package com.example.HogwartsForum.model;

import lombok.Data;

@Data
public class RegistrationModel {
    private String username;
    private String password;
    private String passwordAgain;
    private String house;
    private String petType;

    public Boolean variableValidationForRegistration() {
        return username.length() >= 3 && password.length() >= 3 && passwordAgain.length() >= 3 && password.equals(passwordAgain)
                && !house.equals("noneSelected") && !petType.equals("noneSelected");
    }
}
