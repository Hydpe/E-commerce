package com.example.Ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Orders")
public class Orders<O, I extends Number> {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int orderId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name="user_Id")
    private User user;
    public void setUser(User user) {
       this.user = user;
    }
    public User getUser() {
       return user;
    }
    public Orders()
    {

    }
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "order",cascade = CascadeType.ALL)
    @JsonManagedReference
    List<Product> products = new ArrayList<Product>();
    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
