package com.ingsoftware.backend.model;

import java.time.LocalDate;
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
@Table(name = "periodo_lectivo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PeriodoLectivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    @OneToMany(mappedBy = "periodoLectivo")
    private Set<Matricula> matriculas;
}
