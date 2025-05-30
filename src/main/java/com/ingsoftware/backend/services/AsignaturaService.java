package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import com.ingsoftware.backend.model.Asignatura;

public interface AsignaturaService {

    List<Asignatura> getAsignaturas();

    Optional<Asignatura> getAsignatura(Long id);

    Asignatura createAsignatura(Asignatura asignatura);

    Asignatura updateAsignatura(Asignatura asignatura);

    void deleteAsignatura(Long id);
}
