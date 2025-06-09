package com.ingsoftware.backend.controller;

import com.ingsoftware.backend.model.EspacioFisico;
import com.ingsoftware.backend.services.EspacioFisicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/espacios-fisicos")
@CrossOrigin(origins = "*")
public class EspacioFisicoController {

    @Autowired
    private EspacioFisicoService espacioFisicoService;

    @GetMapping
    public List<EspacioFisico> getAllEspacios() {
        return espacioFisicoService.getEspaciosFisicos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspacioFisico> getEspacioById(@PathVariable Long id) {
        return espacioFisicoService.getEspacioFisico(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public EspacioFisico createEspacio(@RequestBody EspacioFisico espacio) {
        return espacioFisicoService.createEspacioFisico(espacio);
    }

    @PutMapping("/{id}")
    public EspacioFisico updateEspacio(@PathVariable Long id, @RequestBody EspacioFisico espacio) {
        espacio.setId(id);
        return espacioFisicoService.updateEspacioFisico(espacio);
    }

    @DeleteMapping("/{id}")
    public void deleteEspacio(@PathVariable Long id) {
        espacioFisicoService.deleteEspacioFisico(id);
    }
}
