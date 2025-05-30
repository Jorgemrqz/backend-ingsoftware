package com.ingsoftware.backend.controller;

import com.ingsoftware.backend.model.DiarioCaja;
import com.ingsoftware.backend.services.DiarioCajaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/diarios-caja")
@CrossOrigin(origins = "*")
public class DiarioCajaController {

    @Autowired
    private DiarioCajaService diarioCajaService;

    @GetMapping
    public ResponseEntity<List<DiarioCaja>> getAllDiarios() {
        List<DiarioCaja> diarios = diarioCajaService.getDiariosCaja();
        return new ResponseEntity<>(diarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiarioCaja> getDiarioById(@PathVariable Long id) {
        Optional<DiarioCaja> diario = diarioCajaService.getDiarioCaja(id);
        return diario.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DiarioCaja> createDiario(@RequestBody DiarioCaja diario) {
        DiarioCaja nuevo = diarioCajaService.createDiarioCaja(diario);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiarioCaja> updateDiario(@PathVariable Long id, @RequestBody DiarioCaja diario) {
        Optional<DiarioCaja> existente = diarioCajaService.getDiarioCaja(id);
        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        diario.setId(id);
        DiarioCaja updated = diarioCajaService.updateDiarioCaja(diario);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiario(@PathVariable Long id) {
        Optional<DiarioCaja> existente = diarioCajaService.getDiarioCaja(id);
        if (existente.isPresent()) {
            diarioCajaService.deleteDiarioCaja(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
