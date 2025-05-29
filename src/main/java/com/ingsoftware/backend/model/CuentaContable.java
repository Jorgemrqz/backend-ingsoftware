package com.ingsoftware.backend.model;

import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cuenta_contable")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CuentaContable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "plan_cuenta_id")
    private PlanCuenta planCuenta;

    @OneToMany(mappedBy = "cuentaContable")
    private Set<TransaccionFinanciera> transacciones;

}
