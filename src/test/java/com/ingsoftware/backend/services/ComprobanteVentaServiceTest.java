package com.ingsoftware.backend.services;

import java.math.BigDecimal;
import java.time.LocalDate;
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

import com.ingsoftware.backend.model.ComprobanteVenta;
import com.ingsoftware.backend.repository.ComprobanteVentaRepository;

@ExtendWith(MockitoExtension.class)
class ComprobanteVentaServiceTest {

    @Mock
    private ComprobanteVentaRepository comprobanteVentaRepository;

    @InjectMocks
    private ComprobanteVentaImpl comprobanteVentaService;

    @Test
    void testGetComprobantesVenta() {
        ComprobanteVenta c1 = new ComprobanteVenta();
        c1.setId(1L);
        c1.setNumero("001-001-0000001");

        ComprobanteVenta c2 = new ComprobanteVenta();
        c2.setId(2L);
        c2.setNumero("001-001-0000002");

        when(comprobanteVentaRepository.findAll()).thenReturn(Arrays.asList(c1, c2));

        List<ComprobanteVenta> result = comprobanteVentaService.getComprobantesVenta();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getNumero()).isEqualTo("001-001-0000001");
    }

    @Test
    void testGetComprobanteVenta_found() {
        ComprobanteVenta c = new ComprobanteVenta();
        c.setId(1L);
        c.setNumero("001-001-0000003");

        when(comprobanteVentaRepository.findById(1L)).thenReturn(Optional.of(c));

        Optional<ComprobanteVenta> result = comprobanteVentaService.getComprobanteVenta(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getNumero()).isEqualTo("001-001-0000003");
    }

    @Test
    void testGetComprobanteVenta_notFound() {
        when(comprobanteVentaRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<ComprobanteVenta> result = comprobanteVentaService.getComprobanteVenta(99L);

        assertThat(result).isNotPresent();
    }

    @Test
    void testCreateComprobanteVenta() {
        ComprobanteVenta nuevo = new ComprobanteVenta();
        nuevo.setNumero("001-001-0000004");
        nuevo.setFechaEmision(LocalDate.now());
        nuevo.setTotal(BigDecimal.valueOf(150.75));

        when(comprobanteVentaRepository.save(nuevo)).thenReturn(nuevo);

        ComprobanteVenta result = comprobanteVentaService.createComprobanteVenta(nuevo);

        assertThat(result.getNumero()).isEqualTo("001-001-0000004");
        assertThat(result.getTotal()).isEqualTo(BigDecimal.valueOf(150.75));
    }

    @Test
    void testUpdateComprobanteVenta() {
        ComprobanteVenta existente = new ComprobanteVenta();
        existente.setId(5L);
        existente.setNumero("001-001-0000005");

        when(comprobanteVentaRepository.save(existente)).thenReturn(existente);

        ComprobanteVenta result = comprobanteVentaService.updateComprobanteVenta(existente);

        assertThat(result.getId()).isEqualTo(5L);
        assertThat(result.getNumero()).isEqualTo("001-001-0000005");
    }

    @Test
    void testDeleteComprobanteVenta() {
        Long id = 7L;

        doNothing().when(comprobanteVentaRepository).deleteById(id);

        comprobanteVentaService.deleteComprobanteVenta(id);

        verify(comprobanteVentaRepository, times(1)).deleteById(id);
    }

    @Test
    void testCreateComprobanteVenta_null_shouldThrowException() {
        when(comprobanteVentaRepository.save(null)).thenThrow(new IllegalArgumentException("Comprobante inválido"));

        assertThatThrownBy(() -> comprobanteVentaService.createComprobanteVenta(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Comprobante inválido");
    }
}
