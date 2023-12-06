package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.demo.model.UserApp;
import com.example.demo.repository.UserRepository;

@Component
public class StartApp implements CommandLineRunner{
    @Autowired
    private UserRepository repository;
    
    @Override
    public void run(String... args) throws Exception {
        
        UserApp user = new UserApp();
        user.setName("Israel Alves");
        user.setUsername("khaldewey");
        user.setPassword("123");
        repository.save(user);

        for(UserApp u : repository.findAll()){
            System.out.println(u);
        }
    }
    
}
