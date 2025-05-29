package com.ingsoftware.backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "diario_caja")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiarioCaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
}
