package com.ingsoftware.backend.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ingsoftware.backend.model.CuentaContable;
import com.ingsoftware.backend.repository.CuentaContableRepository;

@ExtendWith(MockitoExtension.class)
class CuentaContableServiceTest {

    @Mock
    private CuentaContableRepository cuentaContableRepository;

    @InjectMocks
    private CuentaContableImpl cuentaContableService;

    @Test
    void testGetCuentasContables() {
        CuentaContable c1 = new CuentaContable();
        c1.setId(1L);
        c1.setNombre("Caja");

        CuentaContable c2 = new CuentaContable();
        c2.setId(2L);
        c2.setNombre("Banco");

        when(cuentaContableRepository.findAll()).thenReturn(Arrays.asList(c1, c2));

        List<CuentaContable> result = cuentaContableService.getCuentasContables();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getNombre()).isEqualTo("Caja");
    }

    @Test
    void testGetCuentaContable_found() {
        CuentaContable c = new CuentaContable();
        c.setId(1L);
        c.setNombre("Capital");

        when(cuentaContableRepository.findById(1L)).thenReturn(Optional.of(c));

        Optional<CuentaContable> result = cuentaContableService.getCuentaContable(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getNombre()).isEqualTo("Capital");
    }

    @Test
    void testGetCuentaContable_notFound() {
        when(cuentaContableRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<CuentaContable> result = cuentaContableService.getCuentaContable(99L);

        assertThat(result).isNotPresent();
    }

    @Test
    void testCreateCuentaContable() {
        CuentaContable nueva = new CuentaContable();
        nueva.setNombre("Ingresos");

        when(cuentaContableRepository.save(nueva)).thenReturn(nueva);

        CuentaContable result = cuentaContableService.createCuentaContable(nueva);

        assertThat(result.getNombre()).isEqualTo("Ingresos");
    }

    @Test
    void testUpdateCuentaContable() {
        CuentaContable cuenta = new CuentaContable();
        cuenta.setId(10L);
        cuenta.setNombre("Egresos");

        when(cuentaContableRepository.save(cuenta)).thenReturn(cuenta);

        CuentaContable result = cuentaContableService.updateCuentaContable(cuenta);

        assertThat(result.getId()).isEqualTo(10L);
        assertThat(result.getNombre()).isEqualTo("Egresos");
    }

    @Test
    void testDeleteCuentaContable() {
        Long id = 5L;

        doNothing().when(cuentaContableRepository).deleteById(id);

        cuentaContableService.deleteCuentaContable(id);

        verify(cuentaContableRepository, times(1)).deleteById(id);
    }

    @Test
    void testCreateCuentaContable_null_shouldThrowException() {
        when(cuentaContableRepository.save(null)).thenThrow(new IllegalArgumentException("Cuenta inválida"));

        assertThatThrownBy(() -> cuentaContableService.createCuentaContable(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Cuenta inválida");
    }
}
