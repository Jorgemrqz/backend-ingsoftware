package com.ingsoftware.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingsoftware.backend.model.Rubro;

@Repository
public interface RubroRepository extends JpaRepository<Rubro, Long>{

}
