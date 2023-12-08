package com.example.mywebapi.demoweb.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomerController {
    @GetMapping()
    public String welcome() {
        return "Welcome to my web Api !";
    }
}
