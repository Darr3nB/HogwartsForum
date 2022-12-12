package com.HogwartsForum.controller;

import com.HogwartsForum.dto.Login;
import com.HogwartsForum.services.UserService;
import com.HogwartsForum.util.Utility;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    UserService userService;

    @PostMapping(value = "login")
    public HttpEntity<Void> doLogin(Login loginParams, HttpServletResponse response) {
        if (!userService.validateProfile(loginParams.getUsername(), loginParams.getPassword())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Cookie theCookie = new Cookie("hfUsername", loginParams.getUsername());
        theCookie.setMaxAge(Utility.oneDayForCookies);
        theCookie.setPath("/");

        response.addCookie(theCookie);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(value = "logout")
    public HttpEntity<Void> doLogout(HttpServletResponse response) {
        Cookie theCookie = new Cookie("hfUsername", null);
        theCookie.setMaxAge(0);
        theCookie.setPath("/");
        response.addCookie(theCookie);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
