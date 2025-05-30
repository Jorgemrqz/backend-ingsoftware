package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import com.ingsoftware.backend.model.PerfilAcceso;

public interface PerfilAccesoService {

    List<PerfilAcceso> getPerfilesAcceso();

    Optional<PerfilAcceso> getPerfilAcceso(Long id);

    PerfilAcceso createPerfilAccedo(PerfilAcceso perfil);

    PerfilAcceso updatePerfilAcceso(PerfilAcceso perfil);

    void deletePerfilAcceso(Long id);

}
