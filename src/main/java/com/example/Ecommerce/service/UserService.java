package com.example.Ecommerce.service;

import com.example.Ecommerce.entities.User;
import com.example.Ecommerce.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepo repo;
    @Autowired
    public UserService(UserRepo repository) {
        this.repo = repository;
    }
    public String createUser( User user)
    {
            boolean T=repo.existsByEmail(user.getEmail());
            if(T!=true) {
                User newUser = new User();
                newUser.setEmail(user.getEmail());
                newUser.setPassword(user.getPassword());
                newUser.setName(user.getName());
                 repo.save(newUser);
                 return "Success";
            }
            else  {
                return "User Already Exists" ;
            }
    }
    public User  getUser(String email)
    {
        User newUser=repo.findByEmail(email);
       return newUser;
    }
}
