package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import com.ingsoftware.backend.model.PeriodoLectivo;

public interface PeriodoLectivoService {

    List<PeriodoLectivo> getPeriodosLectivos();

    Optional<PeriodoLectivo> getPeriodoLectivo(Long id);

    PeriodoLectivo createPeriodoLectivo(PeriodoLectivo periodo);

    PeriodoLectivo updatePeriodoLectivo(PeriodoLectivo periodo);

    void deletePeriodoLectivo(Long id);
}
