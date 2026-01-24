package com.example.Ecommerce.controller;

import com.example.Ecommerce.entities.*;
import com.example.Ecommerce.jwtUtility.JwtUtil;
import com.example.Ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/Users")
public class userController {
    private UserService userService;
    private JwtUtil jwtUtil;

    @Autowired
    public userController(UserService business, JwtUtil jwtUtil) {
        this.userService = business;
        this.jwtUtil = jwtUtil;
    }

    @Autowired
    public UserService getUserService() {
        return userService;
    }

    @GetMapping("/working")
    String function()
    {
        return "working";
    }

    @GetMapping("/byId/{email}")
    User getUserById(@PathVariable("email") String Email)
    {
        return userService.getUser(Email);
    }
    @PostMapping("/signup")
    String createUser(@RequestBody SignUp user)
    {
        return userService.createUser(user);
    }

    
    @PostMapping("/Login")
    public ResponseEntity<String> login(@RequestBody Login login) {
        User user = userService.checkLogin(login.getEmail(), login.getPassword());
        if (user != null) {

            String token = jwtUtil.generateToken(user.getEmail());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("No user ");
        }
    }

    @GetMapping("/getProfile")
    public User getUserByToken(@RequestParam("token") String token) {
        return userService.getUserByToken(token);
    }


    @PutMapping("/order")
    public User addOrderToUser(@RequestParam String token, @RequestParam String address, @RequestParam String phone)
    {
          User user = userService.getUserByToken(token);
          if(user==null)
          {
              return null;
          }
          user.setAddress(address);
          user.setPhone(phone);
          return userService.addProductsFromCarttoUserOrders(user);
    }




  /*  @PutMapping("/Update")
    public ResponseEntity<String> createCart(@RequestBody User user)
    {
    }*/

}
