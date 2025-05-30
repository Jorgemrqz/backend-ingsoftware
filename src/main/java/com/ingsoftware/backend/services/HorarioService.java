package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import com.ingsoftware.backend.model.Horario;

public interface HorarioService {

    List<Horario> getHorarios();

    Optional<Horario> getHorario(Long id);

    Horario createHorario(Horario horario);

    Horario updateHorario(Horario horario);

    void deleteHorario(Long id);
}
