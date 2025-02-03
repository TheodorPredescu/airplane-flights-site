package com.site.air_tickets.controllers;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.site.air_tickets.models.Users;
import com.site.air_tickets.repositories.UserRepository;
import com.site.air_tickets.service.LoginUserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
// @SessionAttributes({ "user", "log_checked" })
public class CreateUserController {

    @Autowired
    LoginUserService loginUserService;
    private Boolean isLogin = true;
    private Users user;

    // @ResponseBody
    @GetMapping("/user")
    public String index(Model model, HttpSession session) {

        // Optional<Users> optional_user = userRepository.findById(1);
        model.addAttribute("isLogin", isLogin);

        return "user_login-create";
    }

    @PostMapping("/user/login")
    public String loginUser(Model model,
            @RequestParam("user-name") String user_name,
            @RequestParam("password") String password,
            HttpSession session) {

        // System.out.println(user_name);
        // System.out.println(password);
        // System.out.println(loginUserService.userExists(user_name, password));
        // System.out.println(loginUserService.getUserByUsernameAndPasswd(user_name,
        // password).getLastName());

        if (!loginUserService.userExists(user_name, password)) {
            model.addAttribute("nonexistent", false);
            return "redirect:/user";

        } else {
            // model.addAttribute("user", attributeValue);

            user = loginUserService.getUserByUsernameAndPasswd(user_name, password);
            session.setAttribute("log_checked", true);
            session.setAttribute("user", user);

            return "redirect:/menu";
        }

    }

    @PostMapping("/user/toggle")
    public String toggleForm(Model model) {

        isLogin = !isLogin;
        model.addAttribute("isLogin", isLogin);

        return "redirect:/user";
    }
}
