package com.ingsoftware.backend.controller;

import com.ingsoftware.backend.model.DiarioCaja;
import com.ingsoftware.backend.services.DiarioCajaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/diariocaja")
@CrossOrigin(origins = "*")
public class DiarioCajaController {

    @Autowired
    private DiarioCajaService diarioService;

    @GetMapping
    public ResponseEntity<List<DiarioCaja>> getAll() {
        return ResponseEntity.ok(diarioService.getDiarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiarioCaja> getById(@PathVariable Long id) {
        return diarioService.getDiario(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DiarioCaja> create(@Valid @RequestBody DiarioCaja diario) {
        return new ResponseEntity<>(diarioService.createDiario(diario), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiarioCaja> update(@PathVariable Long id, @Valid @RequestBody DiarioCaja diario) {
        if (diarioService.getDiario(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        diario.setId(id);
        return ResponseEntity.ok(diarioService.updateDiario(diario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (diarioService.getDiario(id).isPresent()) {
            diarioService.deleteDiario(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
