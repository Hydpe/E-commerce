package com.example.Ecommerce.service;

import com.example.Ecommerce.entities.Cart;
import com.example.Ecommerce.entities.Product;
import com.example.Ecommerce.entities.ProductsData;
import com.example.Ecommerce.entities.User;
import com.example.Ecommerce.repository.CartRepo;
import com.example.Ecommerce.serviceInterface.IcartServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CartService implements IcartServiceImp {

    private final ProductsDataService productsDataService;
    private final CartRepo cartRepo;
    private final UserService userService;

    @Autowired
    public CartService(CartRepo cartRepo, ProductsDataService productsDataService, UserService userService) {
        this.cartRepo = cartRepo;
        this.productsDataService = productsDataService;
        this.userService = userService;
    }

    @Override
    public Cart addProductToCart(User user, ProductsData proData) {

        if (user == null) {
            throw new RuntimeException("User is null. Please login first");
        }


        User dbUser = userService.getUser(user.getEmail());
        if (dbUser == null) {
            throw new RuntimeException("User is not found in database");
        }


        if (dbUser.getCart() == null) {
            Cart cart = new Cart();
            cart.setUser(dbUser);
            cart.setProducts(new ArrayList<>());
            dbUser.setCart(cart);
        }

        if (proData == null) {
            throw new RuntimeException("Product not found");
        }


        Product pro = new Product();
        pro.setProductName(proData.getProductName());
        pro.setPrice(proData.getPrice());
        pro.setImage(proData.getImage());
        pro.setQuantity(1);
        pro.setDescription(proData.getDescription());
        pro.setCart(dbUser.getCart());

        dbUser.getCart().getProducts().add(pro);

        cartRepo.save(dbUser.getCart());
        userService.saveUser(dbUser);
        return dbUser.getCart();
    }
}
