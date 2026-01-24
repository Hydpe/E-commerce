package com.example.Ecommerce.repository;

import com.example.Ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepo extends JpaRepository<Product,Integer> {
    //List<Product> displayProducts();
    List<Product>  findAll();


}
