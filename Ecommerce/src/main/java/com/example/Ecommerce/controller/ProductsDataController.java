package com.example.Ecommerce.controller;

import com.example.Ecommerce.entities.ProductsData;
import com.example.Ecommerce.repository.ProductsDataRepo;
import com.example.Ecommerce.service.ProductsDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/ProductsData")
public class ProductsDataController {

    ProductsDataService dataService;
    @Autowired
    public ProductsDataController(ProductsDataService productsDataService) {
        this.dataService = productsDataService;
    }

    @PostMapping("/AddProductsRepo")
    public List<ProductsData> addProductsRepo(@RequestBody List<ProductsData> products) {

        return dataService.addAll(products);
    }




}
