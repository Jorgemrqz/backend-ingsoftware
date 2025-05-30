package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingsoftware.backend.model.PersonalAdministrativo;
import com.ingsoftware.backend.repository.PersonalAdministrativoRepository;

@Service
public class PersonalAdministrativoImpl implements PersonalAdministrativoService{

    @Autowired
    PersonalAdministrativoRepository pAdministrativoRepository;

    @Override
    public List<PersonalAdministrativo> getPersonalAdministrativo() {
        return this.pAdministrativoRepository.findAll();
    }

    @Override
    public Optional<PersonalAdministrativo> getPersona(Long id) {
        return this.pAdministrativoRepository.findById(id);
    }

    @Override
    public PersonalAdministrativo createAdministrativo(PersonalAdministrativo persona) {
        return this.pAdministrativoRepository.save(persona);
    }

    @Override
    public PersonalAdministrativo updateAdministrativo(PersonalAdministrativo persona) {
        return this.pAdministrativoRepository.save(persona);
    }

    @Override
    public void deletePersonalAdministrativo(Long id) {
        this.pAdministrativoRepository.deleteById(id);
    }
}
