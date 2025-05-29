package com.ingsoftware.backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "matricula")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "periodo_lectivo_id")
    private PeriodoLectivo periodoLectivo;

    @ManyToMany
    @JoinTable(
        name = "matricula_rubro",
        joinColumns = @JoinColumn(name = "matricula_id"),
        inverseJoinColumns = @JoinColumn(name = "rubro_id")
    )
    private Set<Rubro> rubros;

    @ManyToMany(mappedBy = "matriculas")
    private Set<Clase> clases;
}
