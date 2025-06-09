package com.ingsoftware.backend.controller;

import com.ingsoftware.backend.model.Grupo;
import com.ingsoftware.backend.services.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/grupos")
@CrossOrigin(origins = "*")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @GetMapping
    public List<Grupo> getAllGrupos() {
        return grupoService.getGrupos();
    }

    @GetMapping("/{id}")
        public ResponseEntity<Grupo> getGrupoById(@PathVariable Long id) {
            Optional<Grupo> grupo = grupoService.getGrupo(id);
            return grupo.map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
        }

    @PostMapping
    public Grupo createGrupo(@RequestBody Grupo grupo) {
        return grupoService.createGrupo(grupo);
    }

    @PutMapping("/{id}")
    public Grupo updateGrupo(@PathVariable Long id, @RequestBody Grupo grupo) {
        grupo.setId(id);
        return grupoService.updateGrupo(grupo);
    }

    @DeleteMapping("/{id}")
    public void deleteGrupo(@PathVariable Long id) {
        grupoService.deleteGrupo(id);
    }
}
