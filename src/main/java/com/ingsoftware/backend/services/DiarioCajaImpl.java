package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingsoftware.backend.model.DiarioCaja;
import com.ingsoftware.backend.repository.DiarioCajaRepository;

@Service
public class DiarioCajaImpl implements DiarioCajaService {

    @Autowired
    private DiarioCajaRepository diarioCajaRepository;

    @Override
    public List<DiarioCaja> getDiariosCaja() {
        return this.diarioCajaRepository.findAll();
    }

    @Override
    public Optional<DiarioCaja> getDiarioCaja(Long id) {
        return this.diarioCajaRepository.findById(id);
    }

    @Override
    public DiarioCaja createDiarioCaja(DiarioCaja diario) {
        return this.diarioCajaRepository.save(diario);
    }

    @Override
    public DiarioCaja updateDiarioCaja(DiarioCaja diario) {
        return this.diarioCajaRepository.save(diario);
    }

    @Override
    public void deleteDiarioCaja(Long id) {
        this.diarioCajaRepository.deleteById(id);
    }
}
