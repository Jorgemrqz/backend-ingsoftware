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

import com.ingsoftware.backend.model.TransaccionFinanciera;
import com.ingsoftware.backend.services.TransaccionFinancieraService;


@CrossOrigin
@RestController
@RequestMapping("api/transaccion-financiera")
public class TransaccionFinancieraController {

    @Autowired
    private TransaccionFinancieraService tFinancieraService;

    @GetMapping
    public ResponseEntity<List<TransaccionFinanciera>> getTransaccionesFinancieras() {
        return new ResponseEntity<>(this.tFinancieraService.getTransaccionesFinancieras(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransaccionFinanciera> getTransaccionFinanciera(@PathVariable Long id) {
        Optional<TransaccionFinanciera> transaccion = this.tFinancieraService.getTransaccionFinanciera(id);
        if (transaccion.isPresent()) {
            return new ResponseEntity<>(transaccion.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<TransaccionFinanciera> createTransaccionFinanciera(@RequestBody TransaccionFinanciera transaccion) {
        try {
           TransaccionFinanciera newTransaccion = this.tFinancieraService.createTransaccionFinanciera(transaccion);
            return new ResponseEntity<>(newTransaccion, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<TransaccionFinanciera> updateRubro(@RequestBody TransaccionFinanciera transaccion) {
        try {
            TransaccionFinanciera transaccionUpdated = this.tFinancieraService.updateTransaccionFinanciera(transaccion);
            return new ResponseEntity<>(transaccionUpdated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRubro(@PathVariable Long id) {
        Optional<TransaccionFinanciera> transaccion = this.tFinancieraService.getTransaccionFinanciera(id);
        if (transaccion.isPresent()) {       
            this.tFinancieraService.deleteTransaccionFinanciera(transaccion.get().getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
