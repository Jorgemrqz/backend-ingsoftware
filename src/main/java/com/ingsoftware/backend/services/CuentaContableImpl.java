package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingsoftware.backend.model.CuentaContable;
import com.ingsoftware.backend.repository.CuentaContableRepository;

@Service
public class CuentaContableImpl implements CuentaContableService {

    @Autowired
    private CuentaContableRepository cuentaContableRepository;

    @Override
    public List<CuentaContable> getCuentasContables() {
        return this.cuentaContableRepository.findAll();
    }

    @Override
    public Optional<CuentaContable> getCuentaContable(Long id) {
        return this.cuentaContableRepository.findById(id);
    }

    @Override
    public CuentaContable createCuentaContable(CuentaContable cuenta) {
        return this.cuentaContableRepository.save(cuenta);
    }

    @Override
    public CuentaContable updateCuentaContable(CuentaContable cuenta) {
        return this.cuentaContableRepository.save(cuenta);
    }

    @Override
    public void deleteCuentaContable(Long id) {
        this.cuentaContableRepository.deleteById(id);
    }
}
