package com.ingsoftware.backend.controller;

import com.ingsoftware.backend.model.ComprobanteVenta;
import com.ingsoftware.backend.services.ComprobanteVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comprobantes-venta")
@CrossOrigin(origins = "*")
public class ComprobanteVentaController {

    @Autowired
    private ComprobanteVentaService comprobanteVentaService;

    @GetMapping
    public ResponseEntity<List<ComprobanteVenta>> getAllComprobantes() {
        List<ComprobanteVenta> comprobantes = comprobanteVentaService.getComprobantesVenta();
        return new ResponseEntity<>(comprobantes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComprobanteVenta> getComprobanteById(@PathVariable Long id) {
        Optional<ComprobanteVenta> comprobante = comprobanteVentaService.getComprobanteVenta(id);
        return comprobante.map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ComprobanteVenta> createComprobante(@RequestBody ComprobanteVenta comprobante) {
        ComprobanteVenta nuevo = comprobanteVentaService.createComprobanteVenta(comprobante);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComprobanteVenta> updateComprobante(@PathVariable Long id, @RequestBody ComprobanteVenta comprobante) {
        Optional<ComprobanteVenta> existente = comprobanteVentaService.getComprobanteVenta(id);
        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        comprobante.setId(id);
        ComprobanteVenta updated = comprobanteVentaService.updateComprobanteVenta(comprobante);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComprobante(@PathVariable Long id) {
        Optional<ComprobanteVenta> existente = comprobanteVentaService.getComprobanteVenta(id);
        if (existente.isPresent()) {
            comprobanteVentaService.deleteComprobanteVenta(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
