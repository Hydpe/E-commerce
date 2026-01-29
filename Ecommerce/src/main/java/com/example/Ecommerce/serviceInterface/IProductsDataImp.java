package com.example.Ecommerce.serviceInterface;

import com.example.Ecommerce.entities.ProductsData;

import java.util.List;

public interface IProductsDataImp {
    List<ProductsData> addAll(List<ProductsData> productsData);
    ProductsData findById(Integer id);
    List<ProductsData> findByCategoryName(String Name);
}
