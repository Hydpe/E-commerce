package com.example.Ecommerce.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Cart")
public class Cart {

    @Id
    int id;

    @OneToOne(mappedBy="cart",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "cart",cascade = CascadeType.ALL)
    List<Product> products = new ArrayList<Product>();
}
