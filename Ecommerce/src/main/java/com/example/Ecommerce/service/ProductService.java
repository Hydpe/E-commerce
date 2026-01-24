package com.example.Ecommerce.service;

import com.example.Ecommerce.entities.Product;
import com.example.Ecommerce.repository.ProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductsRepo Repo;

    @Autowired
    public ProductService(ProductsRepo Repo) {
        this.Repo = Repo;
    }


}
