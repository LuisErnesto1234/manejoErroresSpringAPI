package com.spring.udemy.demoerror.configuration;

import com.spring.udemy.demoerror.dto.Usuario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class UsuarioConfig {

    @Bean
    public List<Usuario> usuarioList (){
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario(1L, "Enesto", "Daza Firma", "luisernesto@gmail.com"));
        usuarios.add(new Usuario(2L, "Claudio", "Gallardo Lima", "claudiolima@gmail.com"));
        return usuarios;
    }
}
