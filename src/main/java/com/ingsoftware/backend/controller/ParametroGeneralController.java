package com.ingsoftware.backend.controller;

import com.ingsoftware.backend.model.ParametroGeneral;
import com.ingsoftware.backend.services.ParametroGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parametros-generales")
@CrossOrigin(origins = "*")
public class ParametroGeneralController {

    @Autowired
    private ParametroGeneralService parametroGeneralService;

    @GetMapping
    public List<ParametroGeneral> getAllParametrosGenerales() {
        return parametroGeneralService.getParametrosGenerales();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParametroGeneral> getParametroGeneralById(@PathVariable Long id) {
        return parametroGeneralService.getParametroGeneral(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ParametroGeneral createParametroGeneral(@RequestBody ParametroGeneral parametro) {
        return parametroGeneralService.createParametroGeneral(parametro);
    }

    @PutMapping("/{id}")
    public ParametroGeneral updateParametroGeneral(@PathVariable Long id, @RequestBody ParametroGeneral parametro) {
        parametro.setId(id);
        return parametroGeneralService.updateParametroGeneral(parametro);
    }

    @DeleteMapping("/{id}")
    public void deleteParametroGeneral(@PathVariable Long id) {
        parametroGeneralService.deleteParametroGeneral(id);
    }
}
