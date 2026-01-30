package com.example.Ecommerce.controller;

import com.example.Ecommerce.entities.*;
import com.example.Ecommerce.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/Users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/working")
    public String working() {
        return "working";
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUp user) {
        try {
            userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Success");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login, HttpSession session) {
        User user = userService.checkLogin(login.getEmail(), login.getPassword());
        if (user != null) {
            session.setAttribute("user", user);
            return ResponseEntity.ok("Login Success");
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @GetMapping("/getProfile")
    public User getProfile(HttpSession session) {
        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null) return null;

        return userService.getUser(sessionUser.getEmail());
    }

    @PutMapping("/order")
    public User checkout(
            @RequestParam String address,
            @RequestParam String phone,
            HttpSession session
    ) {
        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null) return null;


        User user = userService.getUser(sessionUser.getEmail());


        user.setAddress(address);
        user.setPhoneNumber(phone);


        user = userService.addProductsFromCarttoUserOrders(user);

        session.setAttribute("user", user);

        return user;
    }


    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Logged out successfully";
    }
}
