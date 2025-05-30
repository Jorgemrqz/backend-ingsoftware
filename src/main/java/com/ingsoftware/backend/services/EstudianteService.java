package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import com.ingsoftware.backend.model.Estudiante;

public interface EstudianteService {

    List<Estudiante> getEstudiantes();

    Optional<Estudiante> getEstudiante(Long id);

    Estudiante createEstudiante(Estudiante estudiante);

    Estudiante updateEstudiante(Estudiante estudiante);

    void deleteEstudiante(Long id);
}
