package com.benchraid.benchraid.controller;

import com.benchraid.benchraid.entities.User;
import com.benchraid.benchraid.enums.Role;
import com.benchraid.benchraid.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("title", "Registration Page");
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model){
        User ExistUser = userRepository.findByUsername(user.getUsername());
        if (ExistUser != null){
            model.addAttribute("response", "User already exists!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.EMPLOYEE));
        userRepository.save(user);
        return "redirect:/login";
    }

}