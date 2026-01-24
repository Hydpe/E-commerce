package com.example.Ecommerce.service;

import com.example.Ecommerce.entities.*;
import com.example.Ecommerce.jwtUtility.JwtUtil;
import com.example.Ecommerce.repository.CartRepo;
import com.example.Ecommerce.repository.OrderRepo;
import com.example.Ecommerce.repository.ProductsRepo;
import com.example.Ecommerce.repository.UserRepo;
import com.example.Ecommerce.serviceInterface.IuserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class UserService implements IuserService {
    private JwtUtil jwtUtil;
    ProductsRepo productsRepo;
    UserRepo repo;
    OrderRepo orderRepo;
    CartRepo cartRepo;
    @Autowired
    public UserService(UserRepo repository, JwtUtil jwtUtil,OrderRepo orderRepo, ProductsRepo productsRepo, CartRepo cartRepo) {

        this.repo = repository;
        this.jwtUtil = jwtUtil;
        this.orderRepo = orderRepo;
        this.productsRepo = productsRepo;
        this.cartRepo = cartRepo;
    }
    @Override
    public String createUser(SignUp user)
    {
            boolean T=repo.existsByEmail(user.getEmail());
            if(!T) {
                User newUser = new User();
                newUser.setEmail(user.getEmail());
                newUser.setPassword(user.getPassword());
                newUser.setName(user.getName());
                newUser.getCart();
                Cart newCart = new Cart();
                newCart.getProducts();
                newCart.setUser(newUser);
                newUser.setCart(newCart);
                repo.save(newUser);
                 return "Success";
            }
            else  {
                return "User Already Exists" ;
            }
    }
    @Override
    public User getUser(String email)
    {
        return repo.findByEmail(email);
    }
    @Override
    public User checkLogin(String email,String password)
    {
        User newUser=repo.findByEmail(email);
        if(newUser==null || password==null || !newUser.getPassword().equals(password) ) {
            return null;
        }
        return newUser;
    }
   @Override
    public User getUserByToken(String token) {

        if (!jwtUtil.validateToken(token)) {
            return null;
        }
        String email = jwtUtil.extractEmail(token);
        User user = getUser(email);
        if (user == null) {
            return null;
        }
        return user;
    }
    @Override
    public User saveUser(User user) {
        return repo.save(user);
    }

    @Override
    public User addProductsFromCarttoUserOrders(User user) {
        User newUser = repo.findByEmail(user.getEmail());
        if (newUser == null) {
            return null;
        }

        Cart cart = user.getCart();
        if (cart == null || cart.getProducts() == null || cart.getProducts().isEmpty()) {
            throw new RuntimeException("Cart is empty or null");
        }


        Orders order = new Orders();
        order.setUser(newUser);


        for (Product p : cart.getProducts()) {
            p.setOrder(order);
            p.setCart(null);
          //  productsRepo.save(p);
            order.getProducts().add(p);
        }


      //  orderRepo.save(order);
        newUser.getOrders().add(order);
        repo.save(newUser);


        cart.getProducts().clear();
        cartRepo.save(cart);

        return newUser;
    }




}
