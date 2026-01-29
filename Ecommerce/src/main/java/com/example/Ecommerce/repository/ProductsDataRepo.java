package com.example.Ecommerce.repository;

import com.example.Ecommerce.entities.ProductsData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsDataRepo extends JpaRepository<ProductsData, Integer> {
    List<ProductsData> findAll();
    ProductsData findById(int id);
    //findall;
    //save
    //del;ete
    //
    List<ProductsData> findByCategoryName(String name);
}
