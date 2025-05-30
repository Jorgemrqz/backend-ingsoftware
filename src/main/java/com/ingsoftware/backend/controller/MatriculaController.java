package com.ingsoftware.backend.controller;

import com.ingsoftware.backend.model.Matricula;
import com.ingsoftware.backend.services.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/matriculas")
@CrossOrigin(origins = "*")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @GetMapping
    public List<Matricula> getAllMatriculas() {
        return matriculaService.getMatriculas();
    }

    @GetMapping("/{id}")
    public Optional<Matricula> getMatriculaById(@PathVariable Long id) {
        return matriculaService.getMatricula(id);
    }

    @PostMapping
    public Matricula createMatricula(@RequestBody Matricula matricula) {
        return matriculaService.createMatricula(matricula);
    }

    @PutMapping("/{id}")
    public Matricula updateMatricula(@PathVariable Long id, @RequestBody Matricula matricula) {
        matricula.setId(id);
        return matriculaService.updateMatricula(matricula);
    }

    @DeleteMapping("/{id}")
    public void deleteMatricula(@PathVariable Long id) {
        matriculaService.deleteMatricula(id);
    }
}
