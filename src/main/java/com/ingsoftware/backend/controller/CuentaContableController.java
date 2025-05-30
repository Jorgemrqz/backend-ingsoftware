package com.ingsoftware.backend.controller;

import com.ingsoftware.backend.model.CuentaContable;
import com.ingsoftware.backend.services.CuentaContableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cuentas-contables")
@CrossOrigin(origins = "*")
public class CuentaContableController {

    @Autowired
    private CuentaContableService cuentaContableService;

    @GetMapping
    public ResponseEntity<List<CuentaContable>> getAllCuentasContables() {
        List<CuentaContable> cuentas = cuentaContableService.getCuentasContables();
        return new ResponseEntity<>(cuentas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaContable> getCuentaContableById(@PathVariable Long id) {
        Optional<CuentaContable> cuenta = cuentaContableService.getCuentaContable(id);
        return cuenta.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CuentaContable> createCuentaContable(@RequestBody CuentaContable cuenta) {
        CuentaContable nueva = cuentaContableService.createCuentaContable(cuenta);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaContable> updateCuentaContable(@PathVariable Long id, @RequestBody CuentaContable cuenta) {
        Optional<CuentaContable> existente = cuentaContableService.getCuentaContable(id);
        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        cuenta.setId(id);
        CuentaContable updated = cuentaContableService.updateCuentaContable(cuenta);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuentaContable(@PathVariable Long id) {
        Optional<CuentaContable> existente = cuentaContableService.getCuentaContable(id);
        if (existente.isPresent()) {
            cuentaContableService.deleteCuentaContable(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
