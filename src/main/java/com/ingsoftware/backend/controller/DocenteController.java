package com.ingsoftware.backend.controller;

import com.ingsoftware.backend.model.Docente;
import com.ingsoftware.backend.services.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/docentes")
@CrossOrigin(origins = "*")
public class DocenteController {

    @Autowired
    private DocenteService docenteService;

    @GetMapping
    public ResponseEntity<List<Docente>> getAllDocentes() {
        List<Docente> docentes = docenteService.getDocentes();
        return new ResponseEntity<>(docentes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Docente> getDocenteById(@PathVariable Long id) {
        Optional<Docente> docente = docenteService.getDocente(id);
        return docente.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Docente> createDocente(@RequestBody Docente docente) {
        Docente nuevo = docenteService.createDocente(docente);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Docente> updateDocente(@PathVariable Long id, @RequestBody Docente docente) {
        Optional<Docente> existente = docenteService.getDocente(id);
        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        docente.setId(id);
        Docente updated = docenteService.updateDocente(docente);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocente(@PathVariable Long id) {
        Optional<Docente> existente = docenteService.getDocente(id);
        if (existente.isPresent()) {
            docenteService.deleteDocente(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
