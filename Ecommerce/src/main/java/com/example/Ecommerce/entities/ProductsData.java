package com.example.Ecommerce.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "products_data")
public class ProductsData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String productName;

    @Column(length = 1000)
    private String description;

    private float price;

    @Column(length = 1000)
    private String image;

    // ===== Getters & Setters =====

    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
}
