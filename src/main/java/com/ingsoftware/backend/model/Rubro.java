package com.ingsoftware.backend.model;

import java.math.BigDecimal;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rubro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rubro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    private BigDecimal monto;

        @ManyToMany(mappedBy = "rubros")
    private Set<Matricula> matriculas;

    @ManyToMany
    @JoinTable(
        name = "comprobante_rubro",
        joinColumns = @JoinColumn(name = "rubro_id"),
        inverseJoinColumns = @JoinColumn(name = "comprobante_venta_id")
    )
    private Set<ComprobanteVenta> comprobantesVenta;
}
