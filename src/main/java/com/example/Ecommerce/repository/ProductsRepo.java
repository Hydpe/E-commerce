package com.example.Ecommerce.repository;

import com.example.Ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepo extends JpaRepository<Product,Integer> {
}
