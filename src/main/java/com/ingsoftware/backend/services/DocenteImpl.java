package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingsoftware.backend.model.Docente;
import com.ingsoftware.backend.repository.DocenteRepository;

@Service
public class DocenteImpl implements DocenteService {

    @Autowired
    private DocenteRepository docenteRepository;

    @Override
    public List<Docente> getDocentes() {
        return this.docenteRepository.findAll();
    }

    @Override
    public Optional<Docente> getDocente(Long id) {
        return this.docenteRepository.findById(id);
    }

    @Override
    public Docente createDocente(Docente docente) {
        return this.docenteRepository.save(docente);
    }

    @Override
    public Docente updateDocente(Docente docente) {
        return this.docenteRepository.save(docente);
    }

    @Override
    public void deleteDocente(Long id) {
        this.docenteRepository.deleteById(id);
    }
}
