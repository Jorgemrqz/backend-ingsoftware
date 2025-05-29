package com.ingsoftware.backend.model;

import com.ingsoftware.backend.enums.RolEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private RolEnum rol;

    @OneToOne(mappedBy = "usuario")
    private Estudiante estudiante;

    @OneToOne(mappedBy = "usuario")
    private Docente docente;

    @OneToOne(mappedBy = "usuario")
    private PersonalAdministrativo personalAdministrativo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "perfil_acceso_id")
    private PerfilAcceso perfilAcceso;
}
