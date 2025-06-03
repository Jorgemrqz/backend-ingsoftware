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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ingsoftware.backend.model.PlanCuenta;
import com.ingsoftware.backend.services.PlanCuentaImpl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin
@RestController
@RequestMapping("api/plan-cuenta")
public class PlanCuentaController {

    @Autowired
    PlanCuentaImpl pCuentaImpl;

    @GetMapping
    public ResponseEntity<List<PlanCuenta>> getPlanes() {
        return new ResponseEntity<>(this.pCuentaImpl.getPlanes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanCuenta> getPlanCuenta(@PathVariable Long id) {
        Optional<PlanCuenta> plan = this.pCuentaImpl.getPlan(id);
        if (plan.isPresent()) {
            return new ResponseEntity<>(plan.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<PlanCuenta> createPlanCuenta(@RequestBody PlanCuenta plan) {
        try {
            PlanCuenta newPlan = this.pCuentaImpl.createPlanCuenta(plan);
            return new ResponseEntity<>(newPlan, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<PlanCuenta> updatePlanCuenta(@RequestBody PlanCuenta plan) {
        try {
            PlanCuenta planUpdated = this.pCuentaImpl.updatePlanCuenta(plan);
            return new ResponseEntity<>(planUpdated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlanCuenta(@PathVariable Long id) {
        Optional<PlanCuenta> plan = this.pCuentaImpl.getPlan(id);
        if (plan.isPresent()) {       
            this.pCuentaImpl.deletePlanCuenta(plan.get().getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
