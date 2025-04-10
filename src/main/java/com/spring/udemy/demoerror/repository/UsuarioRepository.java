package com.spring.udemy.demoerror.repository;

import com.spring.udemy.demoerror.dto.Usuario;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository {

    List<Usuario> usuariosFindAll();
    Usuario addUsuario(Usuario usuario);
    void deleteUsuarioById(Long id);
    Optional<Usuario> usuarioFindById(Long id);
}
