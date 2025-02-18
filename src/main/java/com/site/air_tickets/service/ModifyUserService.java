package com.site.air_tickets.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.air_tickets.models.Users;
import com.site.air_tickets.repositories.UserRepository;

@Service
public class ModifyUserService {

    private final UserRepository userRepository;

    @Autowired
    public ModifyUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Boolean modify_params(Users user, Map<String, String> params) {

        try {

            String userName = params.getOrDefault("user-name-change", "").trim();
            String firstName = params.getOrDefault("first-name-change", "").trim();
            String lastName = params.getOrDefault("last-name-change", "").trim();
            String password = params.getOrDefault("password-change", "").trim();
            String passwordConfirm = params.getOrDefault("password-change-confirm", "").trim();
            String cnp = params.getOrDefault("cnp-change", "").trim();
            String city = params.getOrDefault("city-change", "").trim();
            String country = params.getOrDefault("country-change", "").trim();
            String dateOfBirth = params.getOrDefault("date-of-birth-change", "").trim();
            String email = params.getOrDefault("email-change", "").trim();
            String phoneNumber = params.getOrDefault("phone-number-change", "").trim();

            if (!userName.isEmpty()) {
                user.setUserName(userName);
            }
            if (!firstName.isEmpty()) {
                user.setFirst_name(firstName);
            }
            if (!lastName.isEmpty()) {
                user.setLast_name(lastName);
            }
            if (!password.isEmpty() && password.equals(passwordConfirm)) {
                user.setPassword(password);
            }
            if (!cnp.isEmpty()) {
                user.setCnp(cnp);
            }
            if (!city.isEmpty()) {
                user.setCity(city);
            }
            if (!country.isEmpty()) {
                user.setCountry(country);
            }
            if (!dateOfBirth.isEmpty()) {
                user.setDate_of_birth(dateOfBirth);
            }
            if (!email.isEmpty()) {
                user.setEmail(email);
            }
            if (!phoneNumber.isEmpty()) {
                user.setPhone_number(phoneNumber);
            }
            return true;

        } catch (Exception e) {
            return false;
        }

    }
}
