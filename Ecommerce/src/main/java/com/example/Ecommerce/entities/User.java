package com.example.Ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@jakarta.persistence.Entity
@Table(name="Ecommerce")
public class User {

    @Id
    @GeneratedValue
    int id;
    public int getId() {
        return id;
    }
    @Column(nullable = false)
    String name;
    @Column(unique = true,nullable = false)
    String email;
    @Column(nullable = false)
    String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="cart_id") //here user is owning side foreign key//user manages cart
    @JsonManagedReference  //here i wrote this beacuse user manages cart,
    Cart cart;
    public void setCart(Cart cart) {
        this.cart = cart;
    }
    public Cart getCart() {
        return cart;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy="user")
    @JsonManagedReference
    List<Orders>  orders=  new ArrayList<>();
    public List<Orders> getOrders() {
        return orders;
    }
    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }


    String address;

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {}


    String phone;
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }


}
