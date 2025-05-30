package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingsoftware.backend.model.TransaccionFinanciera;
import com.ingsoftware.backend.repository.TransaccionFinancieraRepository;

@Service
public class TransaccionFinancieraImpl implements TransaccionFinancieraService {

    @Autowired
    TransaccionFinancieraRepository tFinancieraRepository;

    @Override
    public List<TransaccionFinanciera> getTransaccionesFinancieras() {
        return this.tFinancieraRepository.findAll();
    }

    @Override
    public Optional<TransaccionFinanciera> getTransaccionFinanciera(Long id) {
        return this.tFinancieraRepository.findById(id);
    }

    @Override
    public TransaccionFinanciera createTransaccionFinanciera(TransaccionFinanciera transaccion) {
        return this.tFinancieraRepository.save(transaccion);
    }

    @Override
    public TransaccionFinanciera updateTransaccionFinanciera(TransaccionFinanciera transaccion) {
        return this.tFinancieraRepository.save(transaccion);
    }

    @Override
    public void deleteTransaccionFinanciera(Long id) {
        this.tFinancieraRepository.deleteById(id);
    }
}
