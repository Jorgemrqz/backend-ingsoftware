package com.ingsoftware.backend.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @ManyToMany(mappedBy = "asignaturas")
    private Set<Grupo> grupos;

    @OneToMany(mappedBy = "asignatura")
    @JsonIgnore
    private Set<Clase> clases;

    @OneToMany(mappedBy = "asignatura")
    private Set<Calificacion> calificaciones;
}
