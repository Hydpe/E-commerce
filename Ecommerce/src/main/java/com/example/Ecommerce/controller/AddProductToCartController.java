package com.example.Ecommerce.controller;

import com.example.Ecommerce.entities.Cart;
import com.example.Ecommerce.entities.Product;
import com.example.Ecommerce.entities.ProductsData;
import com.example.Ecommerce.entities.User;
import com.example.Ecommerce.service.CartService;
import com.example.Ecommerce.service.ProductsDataService;
import com.example.Ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/Cart")
public class AddProductToCartController  {

    CartService cartService;
    ProductsDataService productsDataService;
    UserService userService;

    @Autowired
    public AddProductToCartController(CartService cartService, ProductsDataService productsDataService, UserService userService) {
        this.userService = userService;
        this.cartService = cartService;
        this.productsDataService = productsDataService;
    }
/*
     @PutMapping("/{id}")
    public Cart addProductToCart(@PathVariable int id, @RequestBody ProductsData product){
            return
     }*/
    @PutMapping("/{id}")
    public Cart addProductToCart(@RequestBody ProductsData product, @PathVariable int id, @RequestParam String token) {
          User user = userService.getUserByToken(token);
        if (user == null) {
            throw new RuntimeException("Invalid token or user not logged in");
        }
          return cartService.addProductToCart(user,product);
    }
}
