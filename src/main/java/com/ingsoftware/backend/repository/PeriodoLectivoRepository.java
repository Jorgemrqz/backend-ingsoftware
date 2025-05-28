package com.ingsoftware.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingsoftware.backend.model.PeriodoLectivo;

@Repository
public interface PeriodoLectivoRepository extends JpaRepository<PeriodoLectivo, Long>{

}
