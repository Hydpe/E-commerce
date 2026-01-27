package com.example.Ecommerce.controller;

import com.example.Ecommerce.entities.*;
import com.example.Ecommerce.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String signup(@RequestBody SignUp user) {
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login, HttpSession session) {
        User user = userService.checkLogin(login.getEmail(), login.getPassword());
        if (user != null) {
            session.setAttribute("user", user); //  Store user in session
            return ResponseEntity.ok("Login Success");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @GetMapping("/getProfile")
    public User getProfile(HttpSession session) {
        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null) return null;
        return userService.getUser(sessionUser.getEmail());
    }

    @PutMapping("/order")
    public User checkout(@RequestParam String address, @RequestParam String phone, HttpSession session) {
      //  session.setAttribute("user",userService.getUser(user.getEmail()));

        User user = (User) session.getAttribute("user");
        if (user == null) return null;

        user.setAddress(address);
        user.setPhone(phone);
        session.setAttribute("user",userService.getUser(user.getEmail()));
        User user1 = (User) session.getAttribute("user");
        user1=userService.addProductsFromCarttoUserOrders(user1);
        return user1;
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Logged out successfully";
    }
}
