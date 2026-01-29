package com.example.Ecommerce.controller;

import com.example.Ecommerce.entities.Cart;
import com.example.Ecommerce.entities.ProductsData;
import com.example.Ecommerce.entities.User;
import com.example.Ecommerce.service.CartService;
import com.example.Ecommerce.service.ProductService;
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
    private final ProductService productService;

    @Autowired
    public CartController(CartService cartService, ProductsDataService productsDataService, UserService userService, ProductService productService) {
        this.cartService = cartService;
        this.productsDataService = productsDataService;
        this.userService = userService;
        this.productService = productService;
    }

    @PutMapping("/{productId}")
    public Cart addProductToCart(@PathVariable int productId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) throw new RuntimeException("please login first");
        System.out.println("user: " + productId);
        ProductsData product = productsDataService.findById(productId);
        return cartService.addProductToCart(user, product);
    }
    @DeleteMapping("/{productId}")
    public Cart removeProductFromCart(@PathVariable int productId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) throw new RuntimeException("please login first");
        System.out.println("user: " + productId);
        //ProductsData product = productsDataService.findById(productId);
        return cartService.removeProductFromCart(user, productId);
    }
}
