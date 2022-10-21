package com.example.HogwartsForum.Controller;

import com.example.HogwartsForum.Model.LoginParams;
import com.example.HogwartsForum.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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

//        model.addAttribute("username", username);
        model.addAttribute("username", null);
        return "index";
    }

    @PostMapping(value = "/login")
    public HttpEntity<Void> doLogin(@RequestBody LoginParams loginParams, HttpServletResponse response) {
        if (!userService.validateLogin(loginParams.getUsername(), loginParams.getPassword())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        System.out.println("Valid login");
        Cookie theCookie = new Cookie("hfUsername", loginParams.getUsername());
        theCookie.setMaxAge(60 * 60 * 24);

        response.addCookie(theCookie);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
