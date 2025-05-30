package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingsoftware.backend.model.Grupo;
import com.ingsoftware.backend.repository.GrupoRepository;

@Service
public class GrupoImpl implements GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    @Override
    public List<Grupo> getGrupos() {
        return this.grupoRepository.findAll();
    }

    @Override
    public Optional<Grupo> getGrupo(Long id) {
        return this.grupoRepository.findById(id);
    }

    @Override
    public Grupo createGrupo(Grupo grupo) {
        return this.grupoRepository.save(grupo);
    }

    @Override
    public Grupo updateGrupo(Grupo grupo) {
        return this.grupoRepository.save(grupo);
    }

    @Override
    public void deleteGrupo(Long id) {
        this.grupoRepository.deleteById(id);
    }
}
