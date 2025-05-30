package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingsoftware.backend.model.Clase;
import com.ingsoftware.backend.repository.ClaseRepository;

@Service
public class ClaseImpl implements ClaseService {

    @Autowired
    private ClaseRepository claseRepository;

    @Override
    public List<Clase> getClases() {
        return this.claseRepository.findAll();
    }

    @Override
    public Optional<Clase> getClase(Long id) {
        return this.claseRepository.findById(id);
    }

    @Override
    public Clase createClase(Clase clase) {
        return this.claseRepository.save(clase);
    }

    @Override
    public Clase updateClase(Clase clase) {
        return this.claseRepository.save(clase);
    }

    @Override
    public void deleteClase(Long id) {
        this.claseRepository.deleteById(id);
    }
}
