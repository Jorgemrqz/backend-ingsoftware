package com.ingsoftware.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingsoftware.backend.model.Usuario;

@Repository 
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
