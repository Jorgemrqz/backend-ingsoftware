package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import com.ingsoftware.backend.model.PersonalAdministrativo;

public interface PersonalAdministrativoService {

    List<PersonalAdministrativo> getPersonalAdministrativo();

    Optional<PersonalAdministrativo> getPersona(Long id);

    PersonalAdministrativo createAdministrativo(PersonalAdministrativo persona);

    PersonalAdministrativo updateAdministrativo(PersonalAdministrativo persona);

    void deletePersonalAdministrativo(Long id);
}
