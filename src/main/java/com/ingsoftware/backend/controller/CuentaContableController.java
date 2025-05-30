package com.ingsoftware.backend.controller;

import com.ingsoftware.backend.model.CuentaContable;
import com.ingsoftware.backend.services.CuentaContableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cuentascontable")
@CrossOrigin(origins = "*")
public class CuentaContableController {

    @Autowired
    private CuentaContableService cuentaService;

    @GetMapping
    public ResponseEntity<List<CuentaContable>> getAll() {
        return ResponseEntity.ok(cuentaService.getCuentas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaContable> getById(@PathVariable Long id) {
        return cuentaService.getCuenta(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CuentaContable> create(@Valid @RequestBody CuentaContable cuenta) {
        return new ResponseEntity<>(cuentaService.createCuenta(cuenta), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaContable> update(@PathVariable Long id, @Valid @RequestBody CuentaContable cuenta) {
        if (cuentaService.getCuenta(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        cuenta.setId(id);
        return ResponseEntity.ok(cuentaService.updateCuenta(cuenta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (cuentaService.getCuenta(id).isPresent()) {
            cuentaService.deleteCuenta(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
