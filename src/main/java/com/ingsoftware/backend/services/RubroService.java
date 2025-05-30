package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import com.ingsoftware.backend.model.Rubro;

public interface RubroService {

    List<Rubro> getRubros();

    Optional<Rubro> getRubro(Long id);

    Rubro createRubro(Rubro rubro);

    Rubro updateRubro(Rubro rubro);

    void deleteRubro(Long id);
}
