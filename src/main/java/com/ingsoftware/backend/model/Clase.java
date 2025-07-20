package com.ingsoftware.backend.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clase")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Clase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String aula;

        @ManyToOne
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;

    @ManyToOne
    @JoinColumn(name = "asignatura_id")
    private Asignatura asignatura;

    @ManyToOne
    @JoinColumn(name = "docente_id")
    private Docente docente;

    @ManyToOne
    @JoinColumn(name = "espacio_fisico_id")
    private EspacioFisico espacioFisico;

    @OneToMany(mappedBy = "clase")
    private Set<Horario> horarios;
    

    @OneToMany(mappedBy = "clase")
    @JsonIgnore
    private Set<Calificacion> calificaciones;

    @ManyToMany
    @JoinTable(
        name = "matricula_clase",
        joinColumns = @JoinColumn(name = "clase_id"),
        inverseJoinColumns = @JoinColumn(name = "matricula_id")
    )
    private Set<Matricula> matriculas;

    
}
