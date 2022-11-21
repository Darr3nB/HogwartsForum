package com.example.HogwartsForum.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginParams {
    Integer id;
    String username;
    String password;
}
