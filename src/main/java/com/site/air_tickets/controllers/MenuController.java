package com.site.air_tickets.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({ "user" })
public class MenuController {

    @GetMapping("/menu")
    public String slow_menu(Model model) {
        return "menu";
    }

}
