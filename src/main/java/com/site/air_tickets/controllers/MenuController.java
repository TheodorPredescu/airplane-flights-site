package com.site.air_tickets.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.site.air_tickets.models.Ticket;
import com.site.air_tickets.models.Users;
import com.site.air_tickets.service.LoginUserService;
import com.site.air_tickets.service.MenuService;
import com.site.air_tickets.service.ModifyUserService;

import jakarta.servlet.http.HttpSession;

@Controller
// @SessionAttributes({ "log_checked" })
public class MenuController {

    @Autowired
    MenuService menuService;
    @Autowired
    LoginUserService loginUserService;
    @Autowired
    ModifyUserService modifyUserService;

    private Boolean logged = false, filter_active = false;
    private Users user;
    private List<Ticket> shown_tickets = new ArrayList<>();

    // filters
    Double price_min_final, price_max_final;
    Integer stops_final = -1, class_ticket_final = -1;
    String airline_final;
    LocalDate departure_date_final;

    @GetMapping("/menu")
    public String slow_menu(Model model, HttpSession session,
            @RequestParam(value = "price_min", required = false) Double price_min) {

        Boolean logged_checking = (Boolean) session.getAttribute("log_checked");

        if (logged_checking != null) {

            if (logged_checking == true) {

                user = (Users) session.getAttribute("user");
                model.addAttribute("user", user);
            }

            logged = logged_checking;
        }

        if (!filter_active) {
            shown_tickets.clear();
            shown_tickets.addAll(menuService.allTickets());
        }

        model.addAttribute("allTickets", shown_tickets);
        model.addAttribute("log_checked", logged);
        model.addAttribute("price_min", price_min_final);
        model.addAttribute("price_max", price_max_final);
        model.addAttribute("stops", stops_final);
        model.addAttribute("class_ticket", class_ticket_final);
        model.addAttribute("departure_date", departure_date_final);
        model.addAttribute("airline", airline_final);

        return "menu";
    }

    @PostMapping("/menu/login")
    public String menu_login(Model model,
            HttpSession session,
            @RequestParam("user-name") String user_name,
            @RequestParam("password") String password) {

        if (!loginUserService.userExists(user_name, password)) {
            System.out.println("LOGIN USER SERVICE -- USER EXISTS!");
            return "menu";
        }

        user = (Users) loginUserService.getUserByUsernameAndPasswd(user_name, password);
        session.setAttribute("user", user);
        session.setAttribute("log_checked", true);
        return "redirect:/menu";
    }

    @GetMapping("/menu/logout")
    public String logout(HttpSession session) {
        // session.invalidate(); // Clears session data
        session.removeAttribute("user");
        session.setAttribute("log_checked", false);
        return "redirect:/menu";
    }

    @PostMapping("/menu/logout/change_user_info")
    public String change_info_user(Model model, Map<String, String> params) {
        modifyUserService.modify_params(user, params);
        return "redirect:/menu";
    }

    @GetMapping("menu/filter")
    public String filtering(Model model,
            @RequestParam(value = "price_min", required = false) Double price_min,
            @RequestParam(value = "price_max", required = false) Double price_max,
            @RequestParam(value = "stops") Integer stops,
            @RequestParam(value = "class_ticket") Integer class_ticket,
            @RequestParam(value = "departure_date", required = false) LocalDate departure_date,
            @RequestParam(value = "airline", required = false) String airline,
            @RequestParam(value = "action", required = false) String action) {

        // System.out.println("price: " + price_min + ", " + price_max);
        // System.out.println("stops: " + stops);
        // System.out.println("class_ticket: " + class_ticket);
        // System.out.println("departure_date: " + departure_date);
        // System.out.println("airline: " + airline);
        // System.out.println("action: " + action);
        // System.out.println();
        //
        filter_active = !"reset".equals(action);
        if (filter_active) {

            price_min_final = price_min;
            price_max_final = price_max;
            stops_final = stops;
            class_ticket_final = class_ticket;
            departure_date_final = departure_date;
            airline_final = airline;

            shown_tickets.clear();
            shown_tickets
                    .addAll(menuService.filterTickets(price_min, price_max, stops, class_ticket, departure_date,
                            airline));

        } else {

            price_max_final = null;
            price_min_final = null;
            stops_final = -1;
            class_ticket_final = -1;
            departure_date_final = null;
            airline_final = "";

        }

        return "redirect:/menu";
    }
}
