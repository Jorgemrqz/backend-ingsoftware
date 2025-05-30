package com.ingsoftware.backend.services;

import java.util.List;
import java.util.Optional;

import com.ingsoftware.backend.model.TransaccionFinanciera;

public interface TransaccionFinancieraService {

    List<TransaccionFinanciera> getTransaccionesFinancieras();

    Optional<TransaccionFinanciera> getTransaccionFinanciera(Long id);

    TransaccionFinanciera createTransaccionFinanciera(TransaccionFinanciera transaccion);

    TransaccionFinanciera updateTransaccionFinanciera(TransaccionFinanciera transaccion);

    void deleteTransaccionFinanciera(Long id);
}
