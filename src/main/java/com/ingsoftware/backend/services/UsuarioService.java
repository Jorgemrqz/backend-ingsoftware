package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import com.ingsoftware.backend.model.Usuario;

public interface UsuarioService {

    List<Usuario> getUsuarios();

    Optional<Usuario> getUsuario(Long id);

    Usuario createUsuario(Usuario usuario);

    Usuario updateUsuario(Usuario usuario);

    void deleteUsuario(Long id);
}
