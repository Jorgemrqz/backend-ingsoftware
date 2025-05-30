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
    public ResponseEntity<List<Docente>> getAll() {
        return ResponseEntity.ok(docenteService.getDocentes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Docente> getById(@PathVariable Long id) {
        return docenteService.getDocente(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Docente> create(@RequestBody Docente docente) {
        return new ResponseEntity<>(docenteService.createDocente(docente), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Docente> update(@PathVariable Long id, @RequestBody Docente docente) {
        if (docenteService.getDocente(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        docente.setId(id);
        return ResponseEntity.ok(docenteService.updateDocente(docente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (docenteService.getDocente(id).isPresent()) {
            docenteService.deleteDocente(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
