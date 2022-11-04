package com.example.HogwartsForum.controller;

import com.example.HogwartsForum.model.HogwartsUser;
import com.example.HogwartsForum.model.LoginParams;
import com.example.HogwartsForum.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class IndexController {
    UserService userService;

    @GetMapping
    public String index(Model model, HttpServletRequest request) {
        String username = null;
        Cookie[] cookies = request.getCookies();

        for (Cookie temp : cookies) {
            if ("hfUsername".equals(temp.getName())) {
                username = temp.getValue();
            }
        }

        model.addAttribute("username", username);
        return "index";
    }

    @PostMapping(value = "login")
    public HttpEntity<Void> doLogin(@RequestBody LoginParams loginParams, HttpServletResponse response) {
        if (!userService.validateProfile(loginParams.getUsername(), loginParams.getPassword())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Cookie theCookie = new Cookie("hfUsername", loginParams.getUsername());
        theCookie.setMaxAge(60 * 60 * 24);
        theCookie.setPath("/");

        response.addCookie(theCookie);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(value = "logout")
    public String doLogout(HttpServletResponse response) {
        Cookie theCookie = new Cookie("hfUsername", null);
        theCookie.setMaxAge(0);
        theCookie.setPath("/");
        response.addCookie(theCookie);

        return "redirect:";
    }

    @GetMapping(value = "profile")
    public String openProfilePage(Model model, HttpServletRequest request) {
        String username = null;
        Cookie[] cookies = request.getCookies();

        for (Cookie temp : cookies) {
            if ("hfUsername".equals(temp.getName())) {
                username = temp.getValue();
            }
        }

        if (username == null) {
            return errorPage();
        }

        HogwartsUser foundUser = userService.getUserByUsername(username);
        model.addAttribute("user", foundUser);
        model.addAttribute("username", username);

        return "profile";
    }

    @GetMapping(value = "error")
    public String errorPage() {
        return "error";
    }

    @PostMapping(value = "delete-profile")
    public HttpEntity<Void> deleteProfile(@RequestBody LoginParams profileParams){
        if (!userService.validateProfile(profileParams.getUsername(), profileParams.getPassword())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        userService.deleteUserById(profileParams.getId());

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(value = "see-all-questions")
    public String allQuestionsPage() {
        return "all-questions";
    }
}
