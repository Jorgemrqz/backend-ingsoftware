package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import com.ingsoftware.backend.model.Matricula;

public interface MatriculaService {

    List<Matricula> getMatriculas();

    Optional<Matricula> getMatricula(Long id);

    Matricula createMatricula(Matricula matricula);

    Matricula updateMatricula(Matricula matricula);

    void deleteMatricula(Long id);
}
