package com.vinnet.service;

import com.vinnet.model.User;
import com.vinnet.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User authenticate(String email, String password) {
        try {
            User user = userRepository.findByEmail(email);
            if (user != null && BCrypt.checkpw(password, user.getPassword())) {
                return user;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Error authenticating user: " + e.getMessage());
        }
    }
}

