package com.example.mywebapi.demoweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.mywebapi.demoweb.model.UserApp;
import com.example.mywebapi.demoweb.repository.UserRepository;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository repository;

    @Operation(summary = "Get some data", description = "Get data from the API")

    @GetMapping()
    public List<UserApp> getUsers() {
        return repository.listAll();
    }

    @GetMapping("/{id}")
    public UserApp getOne(@PathVariable("id") Integer id) {
        return repository.finById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Integer id) {
        repository.remove(id);
    }

    @PostMapping()
    public void postUser(@RequestBody UserApp user) {
        repository.save(user);
    }
}
