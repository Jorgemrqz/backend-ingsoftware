package com.ingsoftware.backend.controller;

import com.ingsoftware.backend.model.ComprobanteVenta;
import com.ingsoftware.backend.services.ComprobanteVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comprobantesventa")
@CrossOrigin(origins = "*")
public class ComprobanteVentaController {

    @Autowired
    private ComprobanteVentaService comprobanteService;

    @GetMapping
    public ResponseEntity<List<ComprobanteVenta>> getAll() {
        return ResponseEntity.ok(comprobanteService.getComprobantes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComprobanteVenta> getById(@PathVariable Long id) {
        return comprobanteService.getComprobante(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ComprobanteVenta> create(@Valid @RequestBody ComprobanteVenta c) {
        return new ResponseEntity<>(comprobanteService.createComprobante(c), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComprobanteVenta> update(@PathVariable Long id, @Valid @RequestBody ComprobanteVenta c) {
        if (comprobanteService.getComprobante(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        c.setId(id);
        return ResponseEntity.ok(comprobanteService.updateComprobante(c));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (comprobanteService.getComprobante(id).isPresent()) {
            comprobanteService.deleteComprobante(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
