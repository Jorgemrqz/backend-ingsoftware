package com.ingsoftware.backend.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "espacio_fisico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EspacioFisico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Integer capacidad;

    private String ubicacion;
}