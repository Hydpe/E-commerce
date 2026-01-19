package com.example.Ecommerce.controller;

import com.example.Ecommerce.entities.User;
import com.example.Ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Users")
public class userController {


    private UserService userService;

    @Autowired
    public userController(UserService business) {
        this.userService = business;
    }

    @Autowired
    public UserService getUserService() {
        return userService;
    }
    @GetMapping("/byId/{email}")
    User getUserById(@PathVariable("email") String Email)
    {
        return userService.getUser(Email);
    }
    @PostMapping("/post")
    String  createUser(@RequestBody User user)
    {
        return userService.createUser(user);
    }

}
