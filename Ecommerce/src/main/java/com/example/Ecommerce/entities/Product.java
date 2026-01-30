package com.example.Ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name="products")
public class Product {


    @Id
    @GeneratedValue
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    String productName;
    int quantity;
    String Description;
    float price;
   // String productName;

    public int getQuantity() {
        return quantity;
    }

    @Column(length = 1000)
    private String image;
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    @ManyToOne(fetch = FetchType.EAGER)//,cascade = CascadeType.ALL product should not control cart.
    @JoinColumn(name="cart_id")  //cart is inverse side.
    @JsonBackReference  //Product backs to cart
    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name="order_id")
    @JsonBackReference
    private Orders order;

    public Orders getOrder() {
        return order;
    }


    public void setOrder(Orders order) {
        this.order = order;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


}
