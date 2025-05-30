package com.ingsoftware.backend.controller;

import com.ingsoftware.backend.model.Asignatura;
import com.ingsoftware.backend.services.AsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;

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
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Asignatura> createAsignatura(@Valid @RequestBody Asignatura asignatura) {
        Asignatura nueva = asignaturaService.createAsignatura(asignatura);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asignatura> updateAsignatura(@PathVariable Long id, @Valid @RequestBody Asignatura asignatura) {
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
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}
