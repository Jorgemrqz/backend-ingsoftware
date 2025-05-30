package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingsoftware.backend.model.PerfilAcceso;
import com.ingsoftware.backend.repository.PerfilAccesoRepository;

@Service
public class PerfilAccesoImpl implements PerfilAccesoService{

    @Autowired
    private PerfilAccesoRepository pAccesoRepository;

    @Override
    public List<PerfilAcceso> getPerfilesAcceso() {
        return this.pAccesoRepository.findAll();
    }

    @Override
    public Optional<PerfilAcceso> getPerfilAcceso(Long id) {
        return this.pAccesoRepository.findById(id);
    }

    @Override
    public PerfilAcceso createPerfilAccedo(PerfilAcceso perfil) {
        return this.pAccesoRepository.save(perfil);
    }

    @Override
    public PerfilAcceso updatePerfilAcceso(PerfilAcceso perfil) {
        return this.pAccesoRepository.save(perfil); 
    }

    @Override
    public void deletePerfilAcceso(Long id) {
        this.pAccesoRepository.deleteById(id);
    }

}
