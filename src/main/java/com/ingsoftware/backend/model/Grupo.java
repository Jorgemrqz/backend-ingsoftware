package com.ingsoftware.backend.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "grupo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String modalidad;

    @ManyToMany
    @JoinTable(
        name = "grupo_asignatura",
        joinColumns = @JoinColumn(name = "grupo_id"),
        inverseJoinColumns = @JoinColumn(name = "asignatura_id")
    )
    private Set<Asignatura> asignaturas;

    @ManyToMany
    @JoinTable(
        name = "grupo_docente",
        joinColumns = @JoinColumn(name = "grupo_id"),
        inverseJoinColumns = @JoinColumn(name = "docente_id")
    )
    private Set<Docente> docentes;

    @OneToMany(mappedBy = "grupo")
    @JsonIgnore
    private Set<Clase> clases;
}


