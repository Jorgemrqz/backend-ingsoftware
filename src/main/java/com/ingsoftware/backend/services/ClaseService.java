package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import com.ingsoftware.backend.model.Clase;

public interface ClaseService {

    List<Clase> getClases();

    Optional<Clase> getClase(Long id);

    Clase createClase(Clase clase);

    Clase updateClase(Clase clase);

    void deleteClase(Long id);
}
