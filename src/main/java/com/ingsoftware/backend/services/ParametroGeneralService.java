package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import com.ingsoftware.backend.model.ParametroGeneral;

public interface ParametroGeneralService {

    List<ParametroGeneral> getParametrosGenerales();

    Optional<ParametroGeneral> getParametroGeneral(Long id);

    ParametroGeneral createParametroGeneral(ParametroGeneral parametro);

    ParametroGeneral updateParametroGeneral(ParametroGeneral parametro);

    void deleteParametroGeneral(Long id);
}
