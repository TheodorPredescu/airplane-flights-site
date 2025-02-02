package com.site.air_tickets.controllers;

import org.springframework.ui.Model;
import com.site.air_tickets.models.Users;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.site.air_tickets.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Controller
public class CreateUserController {
    @Autowired
    private UserRepository userRepository;

    private Boolean isLogin = true;

    // @ResponseBody
    @GetMapping("/user")
    public String index(Model model) {

        // Optional<Users> optional_user = userRepository.findById(1);

        model.addAttribute("isLogin", isLogin);

        return "user_login-create";
    }

    @PostMapping("/user/toggle")
    public String toggleForm(Model model) {

        isLogin = !isLogin;
        model.addAttribute("isLogin", isLogin);

        return "redirect:/user";
    }
}
