package com.example.HogwartsForum.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Login {
    Integer id;
    String username;
    String password;
}
