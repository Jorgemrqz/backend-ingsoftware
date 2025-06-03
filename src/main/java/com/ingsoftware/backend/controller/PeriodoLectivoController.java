package com.ingsoftware.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ingsoftware.backend.model.PeriodoLectivo;
import com.ingsoftware.backend.services.PeriodoLectivoImpl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin
@RestController
@RequestMapping("api/periodo-lectivo")
public class PeriodoLectivoController {

    @Autowired
    private PeriodoLectivoImpl pLectivoImpl;

    @GetMapping
    public ResponseEntity<List<PeriodoLectivo>> getPeriodosLectivos() {
        return new ResponseEntity<>(this.pLectivoImpl.getPeriodosLectivos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeriodoLectivo> getPeriodoLectivo(@PathVariable Long id) {
        Optional<PeriodoLectivo> periodo = this.pLectivoImpl.getPeriodoLectivo(id);
        if (periodo.isPresent()) {
            return new ResponseEntity<>(periodo.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<PeriodoLectivo> createPeriodoLectivo(@RequestBody PeriodoLectivo periodo) {
        try {
            PeriodoLectivo newPeriodo = this.pLectivoImpl.createPeriodoLectivo(periodo);
            return new ResponseEntity<>(newPeriodo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<PeriodoLectivo> updatePeriodoLectivo(@RequestBody PeriodoLectivo periodo) {
        try {
            PeriodoLectivo periodoUpdated = this.pLectivoImpl.updatePeriodoLectivo(periodo);
            return new ResponseEntity<>(periodoUpdated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePeriodoLectivo(@PathVariable Long id) {
        Optional<PeriodoLectivo> periodo = this.pLectivoImpl.getPeriodoLectivo(id);
        if (periodo.isPresent()) {       
            this.pLectivoImpl.deletePeriodoLectivo(periodo.get().getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
