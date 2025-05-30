package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import com.ingsoftware.backend.model.PlanCuenta;

public interface PlanCuentaService {

    List<PlanCuenta> getPlanes();

    Optional<PlanCuenta> getPlan(Long id);

    PlanCuenta createPlanCuenta(PlanCuenta plan);

    PlanCuenta updatePlanCuenta(PlanCuenta plan);

    void deletePlanCuenta(Long id);
}
