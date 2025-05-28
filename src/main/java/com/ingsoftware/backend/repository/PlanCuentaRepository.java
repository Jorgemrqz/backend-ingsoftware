package com.ingsoftware.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingsoftware.backend.model.PlanCuenta;

@Repository
public interface PlanCuentaRepository extends JpaRepository<PlanCuenta, Long>{

}
