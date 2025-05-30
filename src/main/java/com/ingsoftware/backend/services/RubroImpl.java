package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ingsoftware.backend.model.Rubro;
import com.ingsoftware.backend.repository.RubroRepository;

@Service
public class RubroImpl implements RubroService{

    RubroRepository rRepository;

    @Override
    public List<Rubro> getRubros() {
        return this.rRepository.findAll();
    }

    @Override
    public Optional<Rubro> getRubro(Long id) {
        return this.rRepository.findById(id);
    }

    @Override
    public Rubro createRubro(Rubro rubro) {
        return this.rRepository.save(rubro);
    }

    @Override
    public Rubro updateRubro(Rubro rubro) {
        return this.rRepository.save(rubro);
    }

    @Override
    public void deleteRubro(Long id) {
        this.rRepository.deleteById(id);
    }
}
