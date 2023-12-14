package com.example.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAppRepository extends JpaRepository<UserApp, Long> {
  UserApp findByUsername(String username);
}
