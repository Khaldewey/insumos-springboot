package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.demo.model.UserApp;
import com.example.demo.repository.UserRepository;

@Component
public class StartApp implements CommandLineRunner {
    @Autowired
    private UserRepository repository;

    @Override
    public void run(String... args) throws Exception {
        List<UserApp> users = repository.filtrarPorNome("Israel");

        UserApp username = repository.findByUsername("khaldewey");

        for (UserApp u : users) {
            System.out.println(u);
        }

        System.out.println(username.getUsername());
        UserApp user = new UserApp();
        user.setName("Renata Alves");
        user.setUsername("khal");
        user.setPassword("1234");
        repository.save(user);

        // for(UserApp u : repository.findAll()){
        // System.out.println(u);
        // }

    }

}
