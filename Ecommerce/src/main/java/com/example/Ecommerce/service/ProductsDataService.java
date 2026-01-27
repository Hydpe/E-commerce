package com.example.Ecommerce.service;

import com.example.Ecommerce.entities.ProductsData;
import com.example.Ecommerce.repository.ProductsDataRepo;
import com.example.Ecommerce.serviceInterface.IProductsDataImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsDataService implements IProductsDataImp {
    ProductsDataRepo productsDataRepo;
    @Autowired
    public ProductsDataService(ProductsDataRepo productsDataRepo) {
        this.productsDataRepo = productsDataRepo;
    }

    @Override
    public List<ProductsData> addAll(List<ProductsData> products) {
        List<ProductsData> list = new ArrayList<>();
        for(ProductsData productsData : products)
        {
            ProductsData p=new ProductsData();
            p.setProductName(productsData.getProductName());
            p.setPrice(productsData.getPrice());
            p.setDescription(productsData.getDescription());
            p.setImage(productsData.getImage());
            list.add(p);
        }
        return productsDataRepo.saveAll(list);
    }

    @Override
    public ProductsData findById(Integer id) {
        return productsDataRepo.findById(id).orElse(null);
    }

    public List<ProductsData> findAll() {
        return productsDataRepo.findAll();
    }
}
