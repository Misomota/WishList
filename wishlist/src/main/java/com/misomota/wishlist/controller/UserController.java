package com.misomota.wishlist.controller;

import com.misomota.wishlist.model.User;
import com.misomota.wishlist.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/User")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Register a user.
    @GetMapping("/Register")
    public String showRegisteredUser(Model model) {
        model.addAttribute("user", new User());
        return "registerAndLogin";
    }

    @PostMapping("/Register")
    public String registerUser(@ModelAttribute User user, Model model) {
        userService.saveUser(user);
        model.addAttribute("signupMessage", "User registered successfully!");
        return "showWishList";
    }

    //User Login. Hvis login er korrekt, return showWishList(kan ændres til profilsiden senere).
    @GetMapping("/Login")
    public String showLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "registerAndLogin";
    }

    @PostMapping("/Login")
    public String loginUser(@ModelAttribute User user, Model model) {
        boolean valid = userService.validateLogin(user.getUsername(), user.getPassword());
        if (valid) {
            model.addAttribute("username", user.getUsername());
            return "showWishList";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "registerAndLogin";
        }
    }
}