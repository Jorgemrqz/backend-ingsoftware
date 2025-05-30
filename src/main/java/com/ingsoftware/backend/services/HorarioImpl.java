package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingsoftware.backend.model.Horario;
import com.ingsoftware.backend.repository.HorarioRepository;

@Service
public class HorarioImpl implements HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    @Override
    public List<Horario> getHorarios() {
        return this.horarioRepository.findAll();
    }

    @Override
    public Optional<Horario> getHorario(Long id) {
        return this.horarioRepository.findById(id);
    }

    @Override
    public Horario createHorario(Horario horario) {
        return this.horarioRepository.save(horario);
    }

    @Override
    public Horario updateHorario(Horario horario) {
        return this.horarioRepository.save(horario);
    }

    @Override
    public void deleteHorario(Long id) {
        this.horarioRepository.deleteById(id);
    }
}
