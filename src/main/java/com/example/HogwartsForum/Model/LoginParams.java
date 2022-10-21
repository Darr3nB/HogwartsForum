package com.example.HogwartsForum.Model;

import com.example.HogwartsForum.Services.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@AllArgsConstructor
public class LoginParams {
    String username;
    String password;

}
