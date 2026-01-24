package com.example.Ecommerce.repository;

import com.example.Ecommerce.entities.Cart;
import com.example.Ecommerce.entities.Product;
import com.example.Ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<Cart,Integer> {
 //   Cart findById(int cartId);
}
