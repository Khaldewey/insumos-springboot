package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.UserApp;

public interface UserRepository extends JpaRepository<UserApp, Integer> {
  List<UserApp> findByNameContaining(String nome);

  UserApp findByUsername(String username);

  @Query("SELECT u FROM UserApp u WHERE u.name LIKE %:name%")
  List<UserApp> filtrarPorNome(@Param("name") String name);
}
