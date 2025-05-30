package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import com.ingsoftware.backend.model.Grupo;

public interface GrupoService {

    List<Grupo> getGrupos();

    Optional<Grupo> getGrupo(Long id);

    Grupo createGrupo(Grupo grupo);

    Grupo updateGrupo(Grupo grupo);

    void deleteGrupo(Long id);
}
