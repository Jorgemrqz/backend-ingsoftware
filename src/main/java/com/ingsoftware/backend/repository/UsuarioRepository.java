package com.ingsoftware.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ingsoftware.backend.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
