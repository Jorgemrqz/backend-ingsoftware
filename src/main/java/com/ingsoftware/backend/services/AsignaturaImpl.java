package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingsoftware.backend.model.Asignatura;
import com.ingsoftware.backend.repository.AsignaturaRepository;

@Service
public class AsignaturaImpl implements AsignaturaService {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Override
    public List<Asignatura> getAsignaturas() {
        return this.asignaturaRepository.findAll();
    }

    @Override
    public Optional<Asignatura> getAsignatura(Long id) {
        return this.asignaturaRepository.findById(id);
    }

    @Override
    public Asignatura createAsignatura(Asignatura asignatura) {
        return this.asignaturaRepository.save(asignatura);
    }

    @Override
    public Asignatura updateAsignatura(Asignatura asignatura) {
        return this.asignaturaRepository.save(asignatura);
    }

    @Override
    public void deleteAsignatura(Long id) {
        this.asignaturaRepository.deleteById(id);
    }
}
