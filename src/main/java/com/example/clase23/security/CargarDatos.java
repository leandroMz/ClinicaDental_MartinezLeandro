package com.example.clase23.security;

import com.example.clase23.entity.Usuario;
import com.example.clase23.entity.UsuarioRol;
import com.example.clase23.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargarDatos implements ApplicationRunner {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public CargarDatos(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(ApplicationArguments args){
        BCryptPasswordEncoder cifrador = new BCryptPasswordEncoder();
        String passACifrar="user";
        String passACifrarAdmin="admin";
        String passCifrada=cifrador.encode(passACifrar);
        String passCifradaDos=cifrador.encode(passACifrarAdmin);
        Usuario usuarioAInsertar = new Usuario("leo","m","user@gmail.com",passCifrada,UsuarioRol.ROLE_USER);
        Usuario adminAInsertar = new Usuario("leo","m","admin@gmail.com",passCifradaDos,UsuarioRol.ROLE_ADMIN);
        usuarioRepository.save(usuarioAInsertar);
        usuarioRepository.save(adminAInsertar);
    }
}
