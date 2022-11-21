package com.example.HogwartsForum.security;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PasswordAgent {
    private BCryptPasswordEncoder passwordEncoder;

    public String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public Boolean passwordMatches(String hashed, String plain) {
        return passwordEncoder.matches(plain, hashed);
    }
}
