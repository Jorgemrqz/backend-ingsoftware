package com.ingsoftware.backend.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "plan_cuenta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanCuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    @OneToMany(mappedBy = "planCuenta")
    private Set<CuentaContable> cuentasContables;
}
