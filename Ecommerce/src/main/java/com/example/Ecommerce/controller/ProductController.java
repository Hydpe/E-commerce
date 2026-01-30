package com.example.Ecommerce.controller;

import com.example.Ecommerce.entities.Product;
import com.example.Ecommerce.entities.ProductsData;
import com.example.Ecommerce.repository.ProductsRepo;
import com.example.Ecommerce.service.ProductService;
import com.example.Ecommerce.service.ProductsDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/Products")
public class ProductController {

    private ProductService productService;
    private ProductsRepo Repo;
    private ProductsDataService dataService;

    @Autowired
    public ProductController(ProductService service, ProductsRepo Repo, ProductsDataService dataService) {
        this.productService = service;
        this.Repo = Repo;
        this.dataService = dataService;
    }

    @GetMapping("/GetProducts")
    public List<Product> findAll() {
        return Repo.findAll();
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
         Repo.deleteAll();
    }

}
