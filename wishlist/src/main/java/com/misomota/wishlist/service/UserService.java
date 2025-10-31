package com.misomota.wishlist.service;

import com.misomota.wishlist.model.User;
import com.misomota.wishlist.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.saveUser(user);
    }

    public boolean validateLogin(String username, String password) {
        return userRepository.validateLogin(username, password);
    }
}