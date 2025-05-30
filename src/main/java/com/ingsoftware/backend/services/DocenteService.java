package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import com.ingsoftware.backend.model.Docente;

public interface DocenteService {

    List<Docente> getDocentes();

    Optional<Docente> getDocente(Long id);

    Docente createDocente(Docente docente);

    Docente updateDocente(Docente docente);

    void deleteDocente(Long id);
}
