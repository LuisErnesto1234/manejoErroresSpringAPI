package com.spring.udemy.demoerror.service;

import com.spring.udemy.demoerror.dto.Usuario;
import com.spring.udemy.demoerror.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UsuarioRepository {

    private final List<Usuario> usuarioList;

    public UsuarioService(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public List<Usuario> usuariosFindAll() {
        return Collections.unmodifiableList(usuarioList);
    }

    @Override
    public Usuario addUsuario(Usuario usuario) {
        usuarioList.add(usuario);
        return usuario;
    }

    @Override
    public void deleteUsuarioById(Long id) {
        usuarioList.removeIf(u -> u.getId().equals(id));
    }

    @Override
    public Optional<Usuario> usuarioFindById(Long id) {
        return usuarioList.stream().filter(u -> u.getId().equals(id)).findFirst();
    }
}
