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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public ResponseEntity<?> addProductToCart(@PathVariable int productId, HttpSession session) {
        try {
            // Check if user is logged in
            User user = (User) session.getAttribute("user");
            if (user == null) {
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("error", "Please login first"));
            }

            System.out.println("Adding product to cart for user: " + user.getId() + ", productId: " + productId);

            // Check if product exists
            ProductsData product = productsDataService.findById(productId);
            if (product == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Product not found"));
            }

            // Add product to cart
            Cart updatedCart = cartService.addProductToCart(user, product);

            return ResponseEntity.ok(updatedCart);

        } catch (Exception e) {
            System.err.println("Error adding product to cart: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to add product to cart: " + e.getMessage()));
        }
    }
    @DeleteMapping("/{productId}")
    public Cart removeProductFromCart(@PathVariable int productId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) throw new RuntimeException("please login first");
        System.out.println("Product Id " + productId);
        //ProductsData product = productsDataService.findById(productId);
        return cartService.removeProductFromCart(user, productId);
    }
}
