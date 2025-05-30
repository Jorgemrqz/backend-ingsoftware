package com.ingsoftware.backend.controller;

import com.ingsoftware.backend.model.Calificacion;
import com.ingsoftware.backend.services.CalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/calificaciones")
@CrossOrigin(origins = "*")
public class CalificacionController {

    @Autowired
    private CalificacionService calificacionService;

    @GetMapping
    public ResponseEntity<List<Calificacion>> getAllCalificaciones() {
        List<Calificacion> calificaciones = calificacionService.getCalificaciones();
        return new ResponseEntity<>(calificaciones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Calificacion> getCalificacionById(@PathVariable Long id) {
        Optional<Calificacion> calificacion = calificacionService.getCalificacion(id);
        return calificacion.map(ResponseEntity::ok)
                           .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Calificacion> createCalificacion(@RequestBody Calificacion calificacion) {
        Calificacion nueva = calificacionService.createCalificacion(calificacion);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Calificacion> updateCalificacion(@PathVariable Long id, @RequestBody Calificacion calificacion) {
        Optional<Calificacion> existente = calificacionService.getCalificacion(id);
        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        calificacion.setId(id);
        Calificacion actualizada = calificacionService.updateCalificacion(calificacion);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalificacion(@PathVariable Long id) {
        Optional<Calificacion> existente = calificacionService.getCalificacion(id);
        if (existente.isPresent()) {
            calificacionService.deleteCalificacion(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}
