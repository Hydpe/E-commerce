package com.example.Ecommerce.serviceInterface;

import com.example.Ecommerce.entities.Cart;
import com.example.Ecommerce.entities.Product;
import com.example.Ecommerce.entities.ProductsData;
import com.example.Ecommerce.entities.User;

public interface IcartServiceImp {
    Cart addProductToCart(User user, ProductsData product);
}
