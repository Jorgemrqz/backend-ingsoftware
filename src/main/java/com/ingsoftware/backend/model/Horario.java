package com.ingsoftware.backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "horario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String diaSemana;

    private LocalTime horaInicio;

    private LocalTime horaFin;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "clase_id")
    private Clase clase;
}
