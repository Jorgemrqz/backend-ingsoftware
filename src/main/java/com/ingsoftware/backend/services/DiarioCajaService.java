package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import com.ingsoftware.backend.model.DiarioCaja;

public interface DiarioCajaService {

    List<DiarioCaja> getDiariosCaja();

    Optional<DiarioCaja> getDiarioCaja(Long id);

    DiarioCaja createDiarioCaja(DiarioCaja diario);

    DiarioCaja updateDiarioCaja(DiarioCaja diario);

    void deleteDiarioCaja(Long id);
}
