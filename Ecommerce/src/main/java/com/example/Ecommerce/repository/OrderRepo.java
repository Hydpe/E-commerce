package com.example.Ecommerce.repository;

import com.example.Ecommerce.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Orders,Integer>{


}
