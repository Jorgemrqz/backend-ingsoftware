package com.ingsoftware.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "docente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombres;

    private String apellidos;

    private String especialidad;

}
