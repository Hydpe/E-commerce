package com.example.Ecommerce.repository;

import com.example.Ecommerce.entities.ProductsData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsDataRepo extends JpaRepository<ProductsData, Integer> {
    List<ProductsData> findAll();
    ProductsData findById(int id);
    //findall;
    //save
    //del;ete
    //
}
