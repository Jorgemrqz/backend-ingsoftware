package com.ingsoftware.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingsoftware.backend.model.TransaccionFinanciera;

@Repository
public interface TransaccionFinancieraRepository extends JpaRepository<TransaccionFinanciera, Long>{

}
