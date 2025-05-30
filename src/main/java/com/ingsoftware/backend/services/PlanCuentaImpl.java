package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingsoftware.backend.model.PlanCuenta;
import com.ingsoftware.backend.repository.PlanCuentaRepository;

@Service
public class PlanCuentaImpl implements PlanCuentaService{

    @Autowired
    PlanCuentaRepository pCuentaRepository;

    @Override
    public List<PlanCuenta> getPlanes() {
        return this.pCuentaRepository.findAll();
    }

    @Override
    public Optional<PlanCuenta> getPlan(Long id) {
        return this.pCuentaRepository.findById(id);
    }

    @Override
    public PlanCuenta createPlanCuenta(PlanCuenta plan) {
        return this.pCuentaRepository.save(plan);
    }

    @Override
    public PlanCuenta updatePlanCuenta(PlanCuenta plan) {
        return this.pCuentaRepository.save(plan);
    }

    @Override
    public void deletePlanCuenta(Long id) {
        this.pCuentaRepository.deleteById(id);
    }

}
