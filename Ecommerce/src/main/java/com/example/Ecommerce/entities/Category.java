package com.example.Ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
     String name;

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("category")
    private List<ProductsData> productsData = new ArrayList<>();


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public List<ProductsData> getProductsData() { return productsData; }
    public void setProductsData(List<ProductsData> productsData) { this.productsData = productsData; }
}
