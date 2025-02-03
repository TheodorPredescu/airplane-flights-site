package com.site.air_tickets.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.site.air_tickets.models.Users;
import com.site.air_tickets.service.LoginUserService;

import jakarta.servlet.http.HttpSession;

@Controller
// @SessionAttributes({ "log_checked" })
public class MenuController {

    @Autowired
    LoginUserService loginUserService;
    private Boolean logged = false;
    private Users user;

    @GetMapping("/menu")
    public String slow_menu(Model model, HttpSession session) {

        Boolean logged_checking = (Boolean) session.getAttribute("log_checked");

        if (logged_checking != null) {

            if (logged_checking == true) {
                user = (Users) session.getAttribute("user");
                model.addAttribute("user", user);
                logged = true;
            }
        }

        model.addAttribute("log_checked", logged);
        return "menu";
    }

    @PostMapping("/menu/login")
    public String menu_login(Model model,
            HttpSession session,
            @RequestParam("user-name") String user_name,
            @RequestParam("password") String password) {

        if (!loginUserService.userExists(user_name, password)) {
            System.out.println("LOGIN USER SERVICE USER EXISTS!");
            return "menu";
        }
        user = (Users) loginUserService.getUserByUsernameAndPasswd(user_name, password);
        session.setAttribute("user", user);
        session.setAttribute("log_checked", true);
        return "redirect:/menu";
    }

}
