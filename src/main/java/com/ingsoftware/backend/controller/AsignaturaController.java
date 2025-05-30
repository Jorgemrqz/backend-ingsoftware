package com.ingsoftware.backend.controller;

import com.ingsoftware.backend.model.Asignatura;
import com.ingsoftware.backend.services.AsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/asignaturas")
@CrossOrigin(origins = "*")
public class AsignaturaController {

    @Autowired
    private AsignaturaService asignaturaService;

    @GetMapping
    public ResponseEntity<List<Asignatura>> getAllAsignaturas() {
        List<Asignatura> asignaturas = asignaturaService.getAsignaturas();
        return new ResponseEntity<>(asignaturas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asignatura> getAsignaturaById(@PathVariable Long id) {
        Optional<Asignatura> asignatura = asignaturaService.getAsignatura(id);
        return asignatura.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Asignatura> createAsignatura(@RequestBody Asignatura asignatura) {
        Asignatura nueva = asignaturaService.createAsignatura(asignatura);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asignatura> updateAsignatura(@PathVariable Long id, @RequestBody Asignatura asignatura) {
        Optional<Asignatura> existente = asignaturaService.getAsignatura(id);
        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        asignatura.setId(id);
        Asignatura updated = asignaturaService.updateAsignatura(asignatura);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsignatura(@PathVariable Long id) {
        Optional<Asignatura> existente = asignaturaService.getAsignatura(id);
        if (existente.isPresent()) {
            asignaturaService.deleteAsignatura(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
