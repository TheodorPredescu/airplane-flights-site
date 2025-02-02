package com.site.air_tickets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.air_tickets.models.Users;
import com.site.air_tickets.repositories.UserRepository;

@Service
public class LoginUserService {

    private final UserRepository userRepository;

    @Autowired
    public LoginUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Boolean userExists(String user_name, String passwd) {
        return userRepository.findByUsernameAndPassword(user_name, passwd) != null;
    }

    public Users getUserByUsernameAndPasswd(String user_name, String passwd) {
        return userRepository.findByUsernameAndPassword(user_name, passwd);
    }
}
