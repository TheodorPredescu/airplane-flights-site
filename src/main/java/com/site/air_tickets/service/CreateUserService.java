package com.site.air_tickets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.air_tickets.models.Users;
import com.site.air_tickets.repositories.UserRepository;

@Service
public class CreateUserService {

    private final UserRepository userRepository;

    @Autowired
    public CreateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public Integer createNewUser(String username, String password, String confirm_Password, String email) {

        if (userRepository.findByEmail(email) != null)
            return 1;
        if (!checkUserName(username))
            return 2;
        if (!checkPassword(password))
            return 3;
        if (!checkConfirmedPassword(password, confirm_Password))
            return 4;
        if (!checkEmail(email))
            return 5;

        // Users user = new Users(username, password, email);
        // userRepository.save(user);

        return 0;
    }

    private Boolean checkUserName(String username) {
        Boolean length3 = false, notdigit = false;

        if (username.length() >= 3)
            length3 = true;

        if (Character.isUpperCase(username.charAt(0)) || Character.isLowerCase(username.charAt(0)))
            notdigit = true;

        return length3 && notdigit;
    }

    private Boolean checkPassword(String password) {
        Boolean lower_case = false, upper_case = false, digit = false, length8 = false;

        if (password.length() >= 8)
            length8 = true;

        for (int i = 0; i < password.length(); i++) {
            if (Character.isLowerCase(password.charAt(i)))
                lower_case = true;
            if (Character.isUpperCase(password.charAt(i)))
                upper_case = true;
            if (Character.isDigit(password.charAt(i)))
                digit = true;
        }

        return length8 && lower_case && upper_case && digit;
    }

    private Boolean checkConfirmedPassword(String password, String confirm_Password) {
        return password.equals(confirm_Password);
    }

    private Boolean checkEmail(String email) {

        return true;
    }
}
