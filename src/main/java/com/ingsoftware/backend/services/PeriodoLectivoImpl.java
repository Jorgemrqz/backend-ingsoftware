package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingsoftware.backend.model.PeriodoLectivo;
import com.ingsoftware.backend.repository.PeriodoLectivoRepository;

@Service
public class PeriodoLectivoImpl implements PeriodoLectivoService{

    @Autowired
    PeriodoLectivoRepository pLectivoRepository;

    @Override
    public List<PeriodoLectivo> getPeriodosLectivos() {
        return this.pLectivoRepository.findAll();
    }

    @Override
    public Optional<PeriodoLectivo> getPeriodoLectivo(Long id) {
        return this.pLectivoRepository.findById(id);
    }

    @Override
    public PeriodoLectivo createPeriodoLectivo(PeriodoLectivo periodo) {
        return this.pLectivoRepository.save(periodo);
    }

    @Override
    public PeriodoLectivo updatePeriodoLectivo(PeriodoLectivo periodo) {
        return this.pLectivoRepository.save(periodo);
    }

    @Override
    public void deletePeriodoLectivo(Long id) {
        this.pLectivoRepository.deleteById(id);
    }

}
