package com.ingsoftware.backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Set;
import java.math.BigDecimal;


@Entity
@Table(name = "comprobante_venta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComprobanteVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;

    private LocalDate fechaEmision;

    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    @ManyToMany(mappedBy = "comprobantesVenta")
    private Set<Rubro> rubros;
 
}
