package com.example.Ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Cart")
public class Cart {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    int id;

    @OneToOne(mappedBy="cart",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference  //inverse sise so cart Backs to user in json
    private User user;  //owning side is user because foreign key here

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "cart",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference  // cart manages products in json
    List<Product> products = new ArrayList<Product>();  //product is owning side.

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
/*
Product → Cart side has @ManyToOne and a @JoinColumn("cart_id") → this is the owning side.

Why? Because Product table has the foreign key cart_id.

This is the side that JPA looks at when saving/updating relationships.

Cart → Products side has mappedBy = "cart" → this is the inverse/non-owning side.

It just “mirrors” the relationship from the Product side.

JPA won’t update the foreign key from this side — it only reads it.
 */