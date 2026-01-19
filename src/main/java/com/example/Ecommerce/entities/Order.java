package com.example.Ecommerce.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Orders")
public class Order {

    @Id
            @GeneratedValue(strategy=GenerationType.IDENTITY)
    int orderId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_Id")
    private User user;
    public void setUser(User user) {
       this.user = user;
    }
    public User getUser() {
       return user;
    }

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "order",cascade = CascadeType.ALL)
    List<Product> products = new ArrayList<Product>();
    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
