package com.ingsoftware.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingsoftware.backend.model.ComprobanteVenta;

@Repository
public interface ComprobanteVentaRepository extends JpaRepository<ComprobanteVenta, Long> {

}
