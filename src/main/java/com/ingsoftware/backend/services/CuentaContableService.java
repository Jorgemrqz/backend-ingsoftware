package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import com.ingsoftware.backend.model.CuentaContable;

public interface CuentaContableService {

    List<CuentaContable> getCuentasContables();

    Optional<CuentaContable> getCuentaContable(Long id);

    CuentaContable createCuentaContable(CuentaContable cuenta);

    CuentaContable updateCuentaContable(CuentaContable cuenta);

    void deleteCuentaContable(Long id);
}
