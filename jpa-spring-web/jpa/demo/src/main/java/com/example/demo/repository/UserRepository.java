package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.UserApp;

public interface UserRepository extends JpaRepository<UserApp, Integer> {
    
}
