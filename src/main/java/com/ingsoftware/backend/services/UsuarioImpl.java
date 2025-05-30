package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingsoftware.backend.model.Usuario;
import com.ingsoftware.backend.repository.UsuarioRepository;

@Service
public class UsuarioImpl implements UsuarioService {

    @Autowired
    UsuarioRepository uRepository;

    @Override
    public List<Usuario> getUsuarios() {
        return this.uRepository.findAll();
    }

    @Override
    public Optional<Usuario> getUsuario(Long id) {
       return this.uRepository.findById(id);
    }

    @Override
    public Usuario createUsuario(Usuario usuario) {
        return this.uRepository.save(usuario);
    }

    @Override
    public Usuario updateUsuario(Usuario usuario) {
        return this.uRepository.save(usuario);
    }

    @Override
    public void deleteUsuario(Long id) {
        this.uRepository.deleteById(id);
    }

}
