package com.example.guitar.controllers;

import java.util.List;

import com.example.guitar.models.User;
import com.example.guitar.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/getall")
    public List<User> getAll() {
        return userService.getAll();
    }
}
