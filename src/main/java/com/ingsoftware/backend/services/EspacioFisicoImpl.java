package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingsoftware.backend.model.EspacioFisico;
import com.ingsoftware.backend.repository.EspacioFisicoRepository;

@Service
public class EspacioFisicoImpl implements EspacioFisicoService {

    @Autowired
    private EspacioFisicoRepository espacioFisicoRepository;

    @Override
    public List<EspacioFisico> getEspaciosFisicos() {
        return this.espacioFisicoRepository.findAll();
    }

    @Override
    public Optional<EspacioFisico> getEspacioFisico(Long id) {
        return this.espacioFisicoRepository.findById(id);
    }

    @Override
    public EspacioFisico createEspacioFisico(EspacioFisico espacio) {
        return this.espacioFisicoRepository.save(espacio);
    }

    @Override
    public EspacioFisico updateEspacioFisico(EspacioFisico espacio) {
        return this.espacioFisicoRepository.save(espacio);
    }

    @Override
    public void deleteEspacioFisico(Long id) {
        this.espacioFisicoRepository.deleteById(id);
    }
}
