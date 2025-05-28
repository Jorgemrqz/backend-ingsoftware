package com.ingsoftware.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingsoftware.backend.model.ComprobandeVenta;

@Repository
public interface ComprobandeVentaRepository extends JpaRepository<ComprobandeVenta, Long> {

}
