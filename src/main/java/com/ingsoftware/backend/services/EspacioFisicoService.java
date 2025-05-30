package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import com.ingsoftware.backend.model.EspacioFisico;

public interface EspacioFisicoService {

    List<EspacioFisico> getEspaciosFisicos();

    Optional<EspacioFisico> getEspacioFisico(Long id);

    EspacioFisico createEspacioFisico(EspacioFisico espacio);

    EspacioFisico updateEspacioFisico(EspacioFisico espacio);

    void deleteEspacioFisico(Long id);
}
