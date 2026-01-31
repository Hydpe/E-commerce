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
import java.util.Iterator;
import java.util.List;

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

    // Add product to cart
    @Override
    public Cart addProductToCart(User user, ProductsData proData) {

        if (user == null) throw new RuntimeException("User is null. Please login first");

        User dbUser = userService.getUser(user.getEmail());
        if (dbUser == null) throw new RuntimeException("User not found in database");

        if (dbUser.getCart() == null) {
            Cart cart = new Cart();
            cart.setUser(dbUser);
            cart.setProducts(new ArrayList<>());
            dbUser.setCart(cart);
        }

        if (proData == null) throw new RuntimeException("Product not found");

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


    @Override
    public Cart removeProductFromCart(User user, int productId) {
        if (user == null) throw new RuntimeException("User is null. Please login first");

        User dbUser = userService.getUser(user.getEmail());
        if (dbUser == null) throw new RuntimeException("User not found in database");

        Cart cart = dbUser.getCart();
        if (cart == null || cart.getProducts() == null || cart.getProducts().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Iterator<Product> iterator = cart.getProducts().iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            Product p = iterator.next();
            System.out.println("Removing product " + p.getId());
            if (p.getId() == productId) {
               System.out.println("Removing product " + p.getId());
                iterator.remove();
                found = true;
                break;
            }
        }
        if (!found) {
            throw new RuntimeException("Product not found in cart");
        }
        cartRepo.save(cart);
        userService.saveUser(dbUser);
        return cart;
    }
}
