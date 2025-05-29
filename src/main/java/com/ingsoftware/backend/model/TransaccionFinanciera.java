package com.ingsoftware.backend.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transaccion_financiera")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransaccionFinanciera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;

    private String descripcion;

    private BigDecimal monto;

    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "diario_caja_id")
    private DiarioCaja diarioCaja;

    @ManyToOne
    @JoinColumn(name = "cuenta_contable_id")
    private CuentaContable cuentaContable;
}
