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

import com.ingsoftware.backend.model.PersonalAdministrativo;
import com.ingsoftware.backend.services.PersonalAdministrativoService;


@CrossOrigin
@RestController
@RequestMapping("api/personal-administrativo")
public class PersonalAdministrativoController {

    @Autowired
    private PersonalAdministrativoService pAdministrativoService;

    @GetMapping
    public ResponseEntity<List<PersonalAdministrativo>> getPersonalAdministrativo() {
        return new ResponseEntity<>(this.pAdministrativoService.getPersonalAdministrativo(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonalAdministrativo> getPersonalAdministrativo(@PathVariable Long id) {
        Optional<PersonalAdministrativo> persona = this.pAdministrativoService.getPersona(id);
        if (persona.isPresent()) {
            return new ResponseEntity<>(persona.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<PersonalAdministrativo> createPersonalAdministrativo(@RequestBody PersonalAdministrativo persona) {
        try {
            PersonalAdministrativo newPersona = this.pAdministrativoService.createAdministrativo(persona);
            return new ResponseEntity<>(newPersona, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<PersonalAdministrativo> updatePersonalAdministrativo(@RequestBody PersonalAdministrativo periodo) {
        try {
            PersonalAdministrativo personaUpdated = this.pAdministrativoService.updateAdministrativo(periodo);
            return new ResponseEntity<>(personaUpdated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonalAdministrativo(@PathVariable Long id) {
        Optional<PersonalAdministrativo> persona = this.pAdministrativoService.getPersona(id);
        if (persona.isPresent()) {       
            this.pAdministrativoService.deletePersonalAdministrativo(persona.get().getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
