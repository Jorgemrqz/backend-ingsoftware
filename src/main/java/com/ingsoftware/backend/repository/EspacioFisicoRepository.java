package com.ingsoftware.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingsoftware.backend.model.EspacioFisico;

@Repository
public interface EspacioFisicoRepository extends JpaRepository<EspacioFisico, Long> {

}
