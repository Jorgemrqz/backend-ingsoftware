package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingsoftware.backend.model.Estudiante;
import com.ingsoftware.backend.repository.EstudianteRepository;

@Service
public class EstudianteImpl implements EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    public List<Estudiante> getEstudiantes() {
        return this.estudianteRepository.findAll();
    }

    @Override
    public Optional<Estudiante> getEstudiante(Long id) {
        return this.estudianteRepository.findById(id);
    }

    @Override
    public Estudiante createEstudiante(Estudiante estudiante) {
        return this.estudianteRepository.save(estudiante);
    }

    @Override
    public Estudiante updateEstudiante(Estudiante estudiante) {
        return this.estudianteRepository.save(estudiante);
    }

    @Override
    public void deleteEstudiante(Long id) {
        this.estudianteRepository.deleteById(id);
    }
}
