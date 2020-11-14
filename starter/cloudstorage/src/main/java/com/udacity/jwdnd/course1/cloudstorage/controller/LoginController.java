package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

        @GetMapping()
        public String loginView() {
            System.out.println("login");
            return "login";
        }

    @PostMapping("/logout")
    public String logout(){
        return "redirect:/login?logout";
    }
}

