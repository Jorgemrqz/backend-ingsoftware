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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ingsoftware.backend.model.Rubro;
import com.ingsoftware.backend.services.RubroService;

@CrossOrigin
@RestController
@RequestMapping("api/rubro")
public class RubroController {

    @Autowired
    private RubroService rService;

    @GetMapping
    public ResponseEntity<List<Rubro>> getRubros() {
        return new ResponseEntity<>(this.rService.getRubros(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rubro> getRubro(@PathVariable Long id) {
        Optional<Rubro> rubro = this.rService.getRubro(id);
        if (rubro.isPresent()) {
            return new ResponseEntity<>(rubro.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Rubro> createRubro(@RequestBody Rubro rubro) {
        try {
           Rubro newRubro = this.rService.createRubro(rubro);
            return new ResponseEntity<>(newRubro, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Rubro> updateRubro(@RequestBody Rubro rubro) {
        try {
            Rubro rubroUpdated = this.rService.updateRubro(rubro);
            return new ResponseEntity<>(rubroUpdated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRubro(@PathVariable Long id) {
        Optional<Rubro> rubro = this.rService.getRubro(id);
        if (rubro.isPresent()) {       
            this.rService.deleteRubro(rubro.get().getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
