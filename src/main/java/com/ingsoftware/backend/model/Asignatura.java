package com.ingsoftware.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "asignatura")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String nivel;

    private String descripcion;
}
