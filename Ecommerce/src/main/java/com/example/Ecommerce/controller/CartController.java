package com.example.Ecommerce.controller;

import com.example.Ecommerce.entities.Cart;
import com.example.Ecommerce.entities.ProductsData;
import com.example.Ecommerce.entities.User;
import com.example.Ecommerce.service.CartService;
import com.example.Ecommerce.service.ProductsDataService;
import com.example.Ecommerce.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/Cart")
public class CartController {

    private final CartService cartService;
    private final ProductsDataService productsDataService;
    private final UserService userService;

    @Autowired
    public CartController(CartService cartService, ProductsDataService productsDataService, UserService userService) {
        this.cartService = cartService;
        this.productsDataService = productsDataService;
        this.userService = userService;
    }

    @PutMapping("/{productId}")
    public Cart addProductToCart(@PathVariable int productId, HttpSession session) {
        User user = (User) session.getAttribute("user"); //  Get user from session
        if (user == null) throw new RuntimeException("Please login first");
        System.out.println("user: " + productId);
        ProductsData product = productsDataService.findById(productId); //  Use productId from path
        return cartService.addProductToCart(user, product);
    }
}
