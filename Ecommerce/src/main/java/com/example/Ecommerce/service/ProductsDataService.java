package com.example.Ecommerce.service;

import com.example.Ecommerce.entities.Category;
import com.example.Ecommerce.entities.ProductsData;
import com.example.Ecommerce.repository.CategoryRepo;
import com.example.Ecommerce.repository.ProductsDataRepo;
import com.example.Ecommerce.serviceInterface.IProductsDataImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsDataService implements IProductsDataImp {
    ProductsDataRepo productsDataRepo;
    CategoryRepo categoryRepo;
    @Autowired
    public ProductsDataService(ProductsDataRepo productsDataRepo, CategoryRepo categoryRepo) {
        this.productsDataRepo = productsDataRepo;
        this.categoryRepo = categoryRepo;
    }

  @Override
  public List<ProductsData> addAll(List<ProductsData> products) {

      List<ProductsData> savedProducts = new ArrayList<>();

      for (ProductsData incoming : products) {

          if (incoming.getCategory() == null ||
                  incoming.getCategory().getName() == null) {
              throw new RuntimeException("Category is required");
          }

          String categoryName = incoming.getCategory().getName();

          Category category = categoryRepo.findByName(categoryName);

          if (category == null) {
              category = new Category();
              category.setName(categoryName);
          }

          ProductsData p = new ProductsData();
          p.setProductName(incoming.getProductName());
          p.setPrice(incoming.getPrice());
          p.setImage(incoming.getImage());
          p.setDescription(incoming.getDescription());


          p.setCategory(category);
          category.getProductsData().add(p);

          categoryRepo.save(category); // cascade saves product
          savedProducts.add(p);
      }

      return productsDataRepo.saveAll(savedProducts);
  }

    @Override
    public ProductsData findById(Integer id) {
        return productsDataRepo.findById(id).orElse(null);
    }

    @Override
    public List<ProductsData> findByCategoryName(String name) {
        return productsDataRepo.findByCategoryName(name);
    }

    public List<ProductsData> findAll() {
        return productsDataRepo.findAll();
    }
      public void deleteAll()
        {
        productsDataRepo.deleteAll();
        }
  }
