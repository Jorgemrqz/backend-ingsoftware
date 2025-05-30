package com.ingsoftware.backend.controller;

import com.ingsoftware.backend.model.Estudiante;
import com.ingsoftware.backend.services.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estudiantes")
@CrossOrigin(origins = "*")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    public List<Estudiante> getAllEstudiantes() {
        return estudianteService.getEstudiantes();
    }

    @GetMapping("/{id}")
    public Optional<Estudiante> getEstudianteById(@PathVariable Long id) {
        return estudianteService.getEstudiante(id);
    }

    @PostMapping
    public Estudiante createEstudiante(@RequestBody Estudiante estudiante) {
        return estudianteService.createEstudiante(estudiante);
    }

    @PutMapping("/{id}")
    public Estudiante updateEstudiante(@PathVariable Long id, @RequestBody Estudiante estudiante) {
        estudiante.setId(id);
        return estudianteService.updateEstudiante(estudiante);
    }

    @DeleteMapping("/{id}")
    public void deleteEstudiante(@PathVariable Long id) {
        estudianteService.deleteEstudiante(id);
    }
}
