package com.taskmanagement.controller;

import com.taskmanagement.entity.User;
import com.taskmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        model.addAttribute("user",new User());
        return "register";
    }

    @PostMapping("/register")
    public String regiserUser(@ModelAttribute("user") User user,Model model){
        // Check if the username is already taken

        if (userService.findByUsername(user.getUsername()) != null) {
            model.addAttribute("registrationError", "Username already exists!");
            return "redirect:/register";
        }
        userService.registerUser(user.getUsername(), user.getPassword());
        return  "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }
}
