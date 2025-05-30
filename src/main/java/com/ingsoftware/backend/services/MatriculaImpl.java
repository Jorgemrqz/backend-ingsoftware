package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingsoftware.backend.model.Matricula;
import com.ingsoftware.backend.repository.MatriculaRepository;

@Service
public class MatriculaImpl implements MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Override
    public List<Matricula> getMatriculas() {
        return this.matriculaRepository.findAll();
    }

    @Override
    public Optional<Matricula> getMatricula(Long id) {
        return this.matriculaRepository.findById(id);
    }

    @Override
    public Matricula createMatricula(Matricula matricula) {
        return this.matriculaRepository.save(matricula);
    }

    @Override
    public Matricula updateMatricula(Matricula matricula) {
        return this.matriculaRepository.save(matricula);
    }

    @Override
    public void deleteMatricula(Long id) {
        this.matriculaRepository.deleteById(id);
    }
}
