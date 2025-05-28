package com.ingsoftware.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingsoftware.backend.model.Docente;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long> {

}
