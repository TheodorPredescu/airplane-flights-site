package com.site.air_tickets.controllers;

import org.springframework.ui.Model;
import com.site.air_tickets.models.Users;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.site.air_tickets.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Controller
public class CreateUserController {
    @Autowired
    private UserRepository userRepository;

    // @ResponseBody
    @GetMapping("/user")
    public String index(Model model) {

        Optional<Users> optional_user = userRepository.findById(1);

        if (optional_user.isPresent()) {
            Users user = optional_user.get();
            model.addAttribute("nume", user.getFirstName());
            model.addAttribute("prenume", user.getLastName());

        } else {
            model.addAttribute("nume", "none");
            model.addAttribute("prenume", "none");
        }

        return "user_add";
    }
}
