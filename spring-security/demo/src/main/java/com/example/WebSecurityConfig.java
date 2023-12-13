package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class WebSecurityConfig {

  @Bean
  public UserDetailsService users() {
    UserDetails user = User.builder()
        .username("user")
        .password("{noop}user123")
        .roles("USER")
        .build();
    UserDetails admin = User.builder()
        .username("admin")
        .password("{noop}admin123")
        .roles("USER", "ADMIN")
        .build();
    return new InMemoryUserDetailsManager(user, admin);
  }

}
