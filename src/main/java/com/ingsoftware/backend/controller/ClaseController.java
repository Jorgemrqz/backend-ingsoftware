package com.ingsoftware.backend.controller;

import com.ingsoftware.backend.model.Clase;
import com.ingsoftware.backend.services.ClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
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
        return ResponseEntity.ok(claseService.getClases());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clase> getClaseById(@PathVariable Long id) {
        return claseService.getClase(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Clase> createClase(@Valid @RequestBody Clase clase) {
        return new ResponseEntity<>(claseService.createClase(clase), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clase> updateClase(@PathVariable Long id, @Valid @RequestBody Clase clase) {
        if (claseService.getClase(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        clase.setId(id);
        return ResponseEntity.ok(claseService.updateClase(clase));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClase(@PathVariable Long id) {
        if (claseService.getClase(id).isPresent()) {
            claseService.deleteClase(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
