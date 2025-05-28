package com.ingsoftware.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingsoftware.backend.model.PerfilAcceso;

@Repository
public interface PerfilAccesoRepository extends JpaRepository<PerfilAcceso, Long>{

}
