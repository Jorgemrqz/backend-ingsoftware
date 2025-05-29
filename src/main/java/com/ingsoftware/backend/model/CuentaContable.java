package com.ingsoftware.backend.model;

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

}
