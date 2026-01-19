package com.example.Ecommerce.repository;

import com.example.Ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    User findByEmail(String email);
   // User Save(User user);
    boolean existsByEmail(String email);
    // boolean deleteByEmail(String email);
    //void  updateUser(User user);
}
