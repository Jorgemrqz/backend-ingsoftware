package com.ingsoftware.backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "estudiante")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombres;

    private String apellidos;

    private String cedula;

    private LocalDate fechaNacimiento;

    private String direccion;

    private String contactoEmergencia;
    
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "estudiante")
    @JsonIgnore
    private Set<Matricula> matriculas;

    @OneToMany(mappedBy = "estudiante")
    @JsonIgnore
    private Set<Calificacion> calificaciones;

    @OneToMany(mappedBy = "estudiante")
    private Set<ComprobanteVenta> comprobantesVenta;

}