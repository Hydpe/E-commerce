package com.example.Ecommerce.serviceInterface;

import com.example.Ecommerce.entities.SignUp;
import com.example.Ecommerce.entities.User;

public interface IuserService {
    String createUser(SignUp user);
    User getUser(String email);
    User checkLogin(String email, String password);
   // User getUserByToken( String token);
    User saveUser(User user);
    User addProductsFromCarttoUserOrders(User user);
}
