package com.example.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAppServiceImplement implements UserAppService {
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Autowired
  UserAppRepository repository;

  @Override
  public UserApp create(UserApp user) {
    UserApp existUser = repository.findByUsername(user.getUsername());

    if (existUser != null) {
      throw new Error("User already exists!");
    }

    user.setPassword(passwordEncoder().encode(user.getPassword()));
    UserApp createdUser = repository.save(user);
    return createdUser;
  }

}
