package com.benchraid.benchraid.controller;

import com.benchraid.benchraid.entities.User;
import com.benchraid.benchraid.enums.Role;
import com.benchraid.benchraid.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String userList(Model model){
        model.addAttribute("title", "Employees");
        model.addAttribute("users", userRepository.findAll());
        return "usersList";
    }

    @GetMapping("/{user}")
    public String user(@PathVariable User user,
            Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping
    public String saveUser(User new_user,
                           @RequestParam ("userId") User user){
        user.setRoles(new_user.getRoles());
        user.setUsername(new_user.getUsername());
        user.setLastname(new_user.getLastname());
        user.setFirstname(new_user.getFirstname());
        user.setPassword(new_user.getPassword());
        userRepository.save(user);
        return "redirect:/user";
    }

    @GetMapping("/add")
    public String user(Model model){
        model.addAttribute("roles", Role.values());
        return "userAdd";
    }

    @PostMapping("/add")
    public String addUser(User user){

        userRepository.save(user);
        return "redirect:/user";
    }
}
