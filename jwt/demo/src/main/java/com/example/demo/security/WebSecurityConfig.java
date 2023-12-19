package com.example.demo.security;

import org.apache.catalina.servlets.WebdavServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class WebSecurityConfig {
  // @Autowired
  // private SecurityDatabaseService securityService;

  // @Autowired
  // public void globalUserDetails(AuthenticationManagerBuilder auth) throws
  // Exception {
  // auth.userDetailsService(securityService).passwordEncoder(NoOpPasswordEncoder.getInstance());
  // }

  @Bean
  public BCryptPasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  private static final String[] SWAGGER_WHITELIST = {
      "/v2/api-docs",
      "/swagger-resources",
      "/swagger-resources/**",
      "/configuration/ui",
      "/configuration/security",
      "/swagger-ui.html",
      "/webjars/**"
  };

  // @Override
  // protected void configure(HttpSecurity http) throws Exception {
  // http.headers().frameOptions().disable();
  // http.cors().and().csrf().disable()
  // .addFilterAfter(new JWTFilter(), UsernamePasswordAuthenticationFilter.class)
  // .authorizeRequests()
  // .antMatchers(SWAGGER_WHITELIST).permitAll()
  // .antMatchers("/h2-console/**").permitAll()
  // .antMatchers(HttpMethod.POST,"/login").permitAll()
  // .antMatchers(HttpMethod.POST,"/users").permitAll()
  // .antMatchers(HttpMethod.GET,"/users").hasAnyRole("USERS","MANAGERS")
  // .antMatchers("/managers").hasAnyRole("MANAGERS")
  // .anyRequest().authenticated()
  // .and()
  // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  // }

  @Bean
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {

    return http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests((requests) -> requests

            .requestMatchers(SWAGGER_WHITELIST).permitAll()
            .requestMatchers("/h2-console/**").permitAll()
            .requestMatchers("/", "/login").permitAll()
            .requestMatchers("/admin").hasAnyRole("MANAGERS")
            .requestMatchers("/users").hasAnyRole("USERS", "MANAGERS")
            .anyRequest()
            .authenticated())
        .addFilterAfter(new JWTFilter(), UsernamePasswordAuthenticationFilter.class)
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .build();

  }

  @Bean // HABILITANDO ACESSAR O H2-DATABSE NA WEB
  public ServletRegistrationBean h2servletRegistration() {
    ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebdavServlet());
    registrationBean.addUrlMappings("/h2-console/*");
    return registrationBean;
  }

}