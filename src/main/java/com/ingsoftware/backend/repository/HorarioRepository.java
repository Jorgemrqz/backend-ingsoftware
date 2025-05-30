package com.ingsoftware.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingsoftware.backend.model.Horario;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {

}
