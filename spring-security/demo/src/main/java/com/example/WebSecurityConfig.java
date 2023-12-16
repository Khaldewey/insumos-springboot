package com.example;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
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

  @Bean
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    return http.authorizeHttpRequests((requests) -> requests
        .requestMatchers("/", "/login").permitAll()
        .requestMatchers("/admin").hasAnyRole("ADMIN")
        .requestMatchers("/users").hasAnyRole("USER", "ADMIN")
        .anyRequest()
        .authenticated()).formLogin(withDefaults())
        .build();

  }

}
