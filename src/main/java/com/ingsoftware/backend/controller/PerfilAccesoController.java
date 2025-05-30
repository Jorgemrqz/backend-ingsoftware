package com.ingsoftware.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ingsoftware.backend.model.PerfilAcceso;
import com.ingsoftware.backend.services.PerfilAccesoImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin
@RestController
@RequestMapping("api/perfilacceso")
public class PerfilAccesoController {

    @Autowired
    private PerfilAccesoImpl pAccesoImpl;

    @GetMapping
    public ResponseEntity<List<PerfilAcceso>> getPerfilesAcceso() {
        return new ResponseEntity<>(this.pAccesoImpl.getPerfilesAcceso(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerfilAcceso> getPerfilAcceso(@PathVariable Long id) {
        Optional<PerfilAcceso> perfil = this.pAccesoImpl.getPerfilAcceso(id);
        if (perfil.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(perfil.get(), HttpStatus.OK);
    }

    @PostMapping 
    public ResponseEntity<PerfilAcceso> createPerfilAcceso(@RequestBody PerfilAcceso perfil) {
        try {
            PerfilAcceso newPerfil = this.pAccesoImpl.createPerfilAccedo(perfil);
            return new ResponseEntity<>(newPerfil, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<PerfilAcceso> updatePerfilAcceso(@RequestBody PerfilAcceso perfil) {
        try {
            PerfilAcceso perfilUpdated = this.pAccesoImpl.updatePerfilAcceso(perfil);
            return new ResponseEntity<>(perfilUpdated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerfilAcceso(@PathVariable Long id) {
        Optional<PerfilAcceso> perfil = this.pAccesoImpl.getPerfilAcceso(id);
        if (perfil.isPresent()) {       
            this.pAccesoImpl.deletePerfilAcceso(perfil.get().getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

