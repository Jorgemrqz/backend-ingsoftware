package com.ingsoftware.backend.controller;

import com.ingsoftware.backend.model.Horario;
import com.ingsoftware.backend.services.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/horarios")
@CrossOrigin(origins = "*")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    @GetMapping
    public List<Horario> getAllHorarios() {
        return horarioService.getHorarios();
    }

    @GetMapping("/{id}")
    public Optional<Horario> getHorarioById(@PathVariable Long id) {
        return horarioService.getHorario(id);
    }

    @PostMapping
    public Horario createHorario(@RequestBody Horario horario) {
        return horarioService.createHorario(horario);
    }

    @PutMapping("/{id}")
    public Horario updateHorario(@PathVariable Long id, @RequestBody Horario horario) {
        horario.setId(id);
        return horarioService.updateHorario(horario);
    }

    @DeleteMapping("/{id}")
    public void deleteHorario(@PathVariable Long id) {
        horarioService.deleteHorario(id);
    }
}
