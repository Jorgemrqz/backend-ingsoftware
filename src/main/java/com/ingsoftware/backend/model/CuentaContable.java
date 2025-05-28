package com.tuempresa.sistemaeducativo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

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
