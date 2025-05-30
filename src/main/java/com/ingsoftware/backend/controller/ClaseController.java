package com.ingsoftware.backend.controller;

import com.ingsoftware.backend.model.Clase;
import com.ingsoftware.backend.services.ClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clases")
@CrossOrigin(origins = "*")
public class ClaseController {

    @Autowired
    private ClaseService claseService;

    @GetMapping
    public ResponseEntity<List<Clase>> getAllClases() {
        List<Clase> clases = claseService.getClases();
        return new ResponseEntity<>(clases, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clase> getClaseById(@PathVariable Long id) {
        Optional<Clase> clase = claseService.getClase(id);
        return clase.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Clase> createClase(@RequestBody Clase clase) {
        Clase nueva = claseService.createClase(clase);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clase> updateClase(@PathVariable Long id, @RequestBody Clase clase) {
        Optional<Clase> existente = claseService.getClase(id);
        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        clase.setId(id);
        Clase updated = claseService.updateClase(clase);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClase(@PathVariable Long id) {
        Optional<Clase> existente = claseService.getClase(id);
        if (existente.isPresent()) {
            claseService.deleteClase(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
