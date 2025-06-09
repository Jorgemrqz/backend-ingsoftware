package com.ingsoftware.backend.services;

import com.ingsoftware.backend.model.TransaccionFinanciera;
import com.ingsoftware.backend.repository.TransaccionFinancieraRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransaccionFinancieraServiceTest {

    @Mock
    private TransaccionFinancieraRepository transaccionRepository;

    @InjectMocks
    private TransaccionFinancieraImpl transaccionService;

    @Test
    void testGetTransaccionesFinancieras() {
        TransaccionFinanciera t1 = new TransaccionFinanciera();
        t1.setId(1L);
        t1.setDescripcion("Pago matrícula");

        TransaccionFinanciera t2 = new TransaccionFinanciera();
        t2.setId(2L);
        t2.setDescripcion("Compra insumos");

        when(transaccionRepository.findAll()).thenReturn(Arrays.asList(t1, t2));

        List<TransaccionFinanciera> result = transaccionService.getTransaccionesFinancieras();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getDescripcion()).isEqualTo("Pago matrícula");
    }

    @Test
    void testGetTransaccionFinanciera() {
        TransaccionFinanciera transaccion = new TransaccionFinanciera();
        transaccion.setId(3L);
        transaccion.setTipo("EGRESO");

        when(transaccionRepository.findById(3L)).thenReturn(Optional.of(transaccion));

        Optional<TransaccionFinanciera> result = transaccionService.getTransaccionFinanciera(3L);

        assertThat(result).isPresent();
        assertThat(result.get().getTipo()).isEqualTo("EGRESO");
    }

    @Test
    void testCreateTransaccionFinanciera() {
        TransaccionFinanciera nueva = new TransaccionFinanciera();
        nueva.setDescripcion("Depósito inicial");
        nueva.setMonto(new BigDecimal("500.00"));
        nueva.setFecha(LocalDate.of(2025, 1, 10));

        when(transaccionRepository.save(nueva)).thenReturn(nueva);

        TransaccionFinanciera result = transaccionService.createTransaccionFinanciera(nueva);

        assertThat(result.getDescripcion()).isEqualTo("Depósito inicial");
        assertThat(result.getMonto()).isEqualByComparingTo("500.00");
    }

    @Test
    void testUpdateTransaccionFinanciera() {
        TransaccionFinanciera actualizada = new TransaccionFinanciera();
        actualizada.setId(5L);
        actualizada.setTipo("INGRESO");

        when(transaccionRepository.save(actualizada)).thenReturn(actualizada);

        TransaccionFinanciera result = transaccionService.updateTransaccionFinanciera(actualizada);

        assertThat(result.getId()).isEqualTo(5L);
        assertThat(result.getTipo()).isEqualTo("INGRESO");
    }

    @Test
    void testDeleteTransaccionFinanciera() {
        Long id = 7L;

        doNothing().when(transaccionRepository).deleteById(id);

        transaccionService.deleteTransaccionFinanciera(id);

        verify(transaccionRepository, times(1)).deleteById(id);
    }
}
