package com.example.mywebapi.demoweb.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.example.mywebapi.demoweb.handlers.CampoObrigatorioException;
import com.example.mywebapi.demoweb.model.UserApp;

@Repository
public class UserRepository {
    public void save(UserApp usuario) {
        if (usuario.getLogin() == null) {
            throw new CampoObrigatorioException("login");
        }

        if (usuario.getPassword() == null) {
            throw new CampoObrigatorioException("password");
        }
        System.out.println("SAVE - Recebendo o usuário na camada de repositório");
        System.out.println(usuario);
    }

    public void update(UserApp usuario) {
        System.out.println("UPDATE - Recebendo o usuário na camada de repositório");
        System.out.println(usuario);
    }

    public void remove(Integer id) {
        System.out.println(String.format("DELETE/id - Recebendo o id: %d para excluir um usuário", id));

        System.out.println(id);
    }

    public List<UserApp> listAll() {
        System.out.println("LIST - Listando os usários do sistema");
        List<UserApp> usuarios = new ArrayList<>();
        usuarios.add(new UserApp("gleyson", "password"));
        usuarios.add(new UserApp("frank", "masterpass"));
        return usuarios;
    }

    public UserApp finById(Integer id) {
        System.out.println(String.format("FIND/id - Recebendo o id: %d para localizar um usuário", id));
        return new UserApp("gleyson", "password");
    }

}
