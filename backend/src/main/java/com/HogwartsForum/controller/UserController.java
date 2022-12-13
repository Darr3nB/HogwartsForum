package com.HogwartsForum.controller;

import com.HogwartsForum.dto.Login;
import com.HogwartsForum.model.HogwartsUser;
import com.HogwartsForum.model.RegistrationModel;
import com.HogwartsForum.security.PasswordAgent;
import com.HogwartsForum.services.UserService;
import com.HogwartsForum.util.Utility;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    UserService userService;
    PasswordAgent passwordAgent;

    @PostMapping(value = "login")
    public HttpEntity<Void> doLogin(@RequestBody Login loginParams, HttpServletResponse response) {
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

    @GetMapping(value = "logged-in")
    public ResponseEntity<HogwartsUser> isLoggedIn(HttpServletRequest request) {
        String username = null;
        HogwartsUser user;
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        for (Cookie cookie : cookies) {
            if ("hfUsername".equals(cookie.getName())) {
                username = cookie.getValue();
            }
        }

        if (username == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        user = userService.getUserByUsername(username);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping(value = "delete-profile")
    public HttpEntity<Void> deleteProfile(@RequestBody Login profileParams) {
        if (!userService.validateProfile(profileParams.getUsername(), profileParams.getPassword())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        userService.deleteUserById(profileParams.getId());

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(value = "registration")
    public HttpEntity<Void> doRegister(@RequestBody RegistrationModel newRegistration) {
        if (!newRegistration.validateRegistrationData()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        HogwartsUser newUser = new HogwartsUser(newRegistration.getUsername(), passwordAgent.hashPassword(newRegistration.getPassword()),
                newRegistration.getHouse(), newRegistration.getPetType());
        userService.addUser(newUser);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
