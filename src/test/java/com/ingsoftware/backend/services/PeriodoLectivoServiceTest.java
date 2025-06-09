package com.ingsoftware.backend.services;

import com.ingsoftware.backend.model.PeriodoLectivo;
import com.ingsoftware.backend.repository.PeriodoLectivoRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PeriodoLectivoServiceTest {

    @Mock
    private PeriodoLectivoRepository periodoLectivoRepository;

    @InjectMocks
    private PeriodoLectivoImpl periodoLectivoService;

    @Test
    void testGetPeriodosLectivos() {
        PeriodoLectivo p1 = new PeriodoLectivo();
        p1.setId(1L);
        p1.setNombre("2024-A");

        PeriodoLectivo p2 = new PeriodoLectivo();
        p2.setId(2L);
        p2.setNombre("2024-B");

        when(periodoLectivoRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<PeriodoLectivo> result = periodoLectivoService.getPeriodosLectivos();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getNombre()).isEqualTo("2024-A");
    }

    @Test
    void testGetPeriodoLectivo() {
        PeriodoLectivo p = new PeriodoLectivo();
        p.setId(1L);
        p.setNombre("2025-A");

        when(periodoLectivoRepository.findById(1L)).thenReturn(Optional.of(p));

        Optional<PeriodoLectivo> result = periodoLectivoService.getPeriodoLectivo(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getNombre()).isEqualTo("2025-A");
    }

    @Test
    void testCreatePeriodoLectivo() {
        PeriodoLectivo nuevo = new PeriodoLectivo();
        nuevo.setNombre("2026-A");
        nuevo.setFechaInicio(LocalDate.of(2026, 1, 1));
        nuevo.setFechaFin(LocalDate.of(2026, 6, 30));

        when(periodoLectivoRepository.save(nuevo)).thenReturn(nuevo);

        PeriodoLectivo result = periodoLectivoService.createPeriodoLectivo(nuevo);

        assertThat(result.getNombre()).isEqualTo("2026-A");
        assertThat(result.getFechaInicio()).isEqualTo(LocalDate.of(2026, 1, 1));
    }

    @Test
    void testUpdatePeriodoLectivo() {
        PeriodoLectivo actualizado = new PeriodoLectivo();
        actualizado.setId(3L);
        actualizado.setNombre("2027-A");

        when(periodoLectivoRepository.save(actualizado)).thenReturn(actualizado);

        PeriodoLectivo result = periodoLectivoService.updatePeriodoLectivo(actualizado);

        assertThat(result.getId()).isEqualTo(3L);
        assertThat(result.getNombre()).isEqualTo("2027-A");
    }

    @Test
    void testDeletePeriodoLectivo() {
        Long id = 4L;

        doNothing().when(periodoLectivoRepository).deleteById(id);

        periodoLectivoService.deletePeriodoLectivo(id);

        verify(periodoLectivoRepository, times(1)).deleteById(id);
    }
}
