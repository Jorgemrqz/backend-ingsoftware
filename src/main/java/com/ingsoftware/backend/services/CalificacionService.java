package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import com.ingsoftware.backend.model.Calificacion;

public interface CalificacionService {

    List<Calificacion> getCalificaciones();

    Optional<Calificacion> getCalificacion(Long id);

    Calificacion createCalificacion(Calificacion calificacion);

    Calificacion updateCalificacion(Calificacion calificacion);

    void deleteCalificacion(Long id);
}
