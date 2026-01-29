package com.example.Ecommerce.service;

import com.example.Ecommerce.entities.*;
import com.example.Ecommerce.repository.CartRepo;
import com.example.Ecommerce.repository.OrderRepo;
import com.example.Ecommerce.repository.ProductsRepo;
import com.example.Ecommerce.repository.UserRepo;
import com.example.Ecommerce.serviceInterface.IuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements IuserService {

    private final UserRepo repo;
    private final ProductsRepo productsRepo;
    private final OrderRepo orderRepo;
    private final CartRepo cartRepo;

    @Autowired
    public UserService(UserRepo repo, OrderRepo orderRepo, ProductsRepo productsRepo, CartRepo cartRepo) {
        this.repo = repo;
        this.orderRepo = orderRepo;
        this.productsRepo = productsRepo;
        this.cartRepo = cartRepo;
    }

    @Override
    public String createUser(SignUp user) {
        if (repo.existsByEmail(user.getEmail())) return "User already exists";

        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());

        Cart cart = new Cart();
        cart.setUser(newUser);
        cart.setProducts(new ArrayList<>());

        newUser.setCart(cart);

        repo.save(newUser);
        return "Success";
    }
    @Override
    public User checkLogin(String email, String password) {
        User user = repo.findByEmail(email);
        if (user != null && password.equals(user.getPassword())) return user;
        return null;
    }
    @Override
    public User saveUser(User user) {
        return repo.save(user);
    }
    @Override
    public User getUser(String email)
    {
        return repo.findByEmail(email);
    }
    @Override
    public User addProductsFromCarttoUserOrders(User user) {
        if (user == null) return null;

        Cart cart = user.getCart();
        if (cart == null || cart.getProducts() == null || cart.getProducts().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Orders order = new Orders();
        order.setUser(user);

        for (Product p : cart.getProducts()) {
            p.setOrder(order);
            p.setCart(null);
            order.getProducts().add(p);
        }

        user.getOrders().add(order);
        repo.save(user);

        cart.getProducts().clear();
        cartRepo.save(cart);

        return user;
    }
}
