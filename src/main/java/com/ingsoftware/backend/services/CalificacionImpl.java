package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingsoftware.backend.model.Calificacion;
import com.ingsoftware.backend.repository.CalificacionRepository;

@Service
public class CalificacionImpl implements CalificacionService {

    @Autowired
    private CalificacionRepository calificacionRepository;

    @Override
    public List<Calificacion> getCalificaciones() {
        return this.calificacionRepository.findAll();
    }

    @Override
    public Optional<Calificacion> getCalificacion(Long id) {
        return this.calificacionRepository.findById(id);
    }

    @Override
    public Calificacion createCalificacion(Calificacion calificacion) {
        return this.calificacionRepository.save(calificacion);
    }

    @Override
    public Calificacion updateCalificacion(Calificacion calificacion) {
        return this.calificacionRepository.save(calificacion);
    }

    @Override
    public void deleteCalificacion(Long id) {
        this.calificacionRepository.deleteById(id);
    }
}
