package com.ingsoftware.backend.controller;

import com.ingsoftware.backend.model.Horario;
import com.ingsoftware.backend.services.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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
    public ResponseEntity<List<Horario>> getAllHorarios() {
        List<Horario> horarios = horarioService.getHorarios();
        return new ResponseEntity<>(horarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Horario> getHorarioById(@PathVariable Long id) {
        Optional<Horario> horario = horarioService.getHorario(id);
        return horario.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Horario> createHorario(@RequestBody Horario horario) {
        Horario nuevo = horarioService.createHorario(horario);
        return new ResponseEntity<>(nuevo, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Horario> updateHorario(@PathVariable Long id, @RequestBody Horario horario) {
        horario.setId(id);
        Horario actualizado = horarioService.updateHorario(horario);
        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHorario(@PathVariable Long id) {
        horarioService.deleteHorario(id);
        return ResponseEntity.ok().build();
    }
}
