package com.site.air_tickets.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homeStart() {
        return "home";
    }

    @GetMapping("/menuu")
    public String menu_sender(HttpSession session) {
        session.setAttribute("log_checked", false);
        return "redirect:/menu";
    }
}
