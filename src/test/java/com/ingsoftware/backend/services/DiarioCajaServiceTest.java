package com.ingsoftware.backend.services;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ingsoftware.backend.model.DiarioCaja;
import com.ingsoftware.backend.repository.DiarioCajaRepository;

@ExtendWith(MockitoExtension.class)
class DiarioCajaServiceTest {

    @Mock
    private DiarioCajaRepository diarioCajaRepository;

    @InjectMocks
    private DiarioCajaImpl diarioCajaService;

    @Test
    void testGetDiariosCaja() {
        DiarioCaja d1 = new DiarioCaja();
        d1.setId(1L);
        d1.setFecha(LocalDate.of(2024, 10, 1));

        DiarioCaja d2 = new DiarioCaja();
        d2.setId(2L);
        d2.setFecha(LocalDate.of(2024, 10, 2));

        when(diarioCajaRepository.findAll()).thenReturn(Arrays.asList(d1, d2));

        List<DiarioCaja> result = diarioCajaService.getDiariosCaja();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getFecha()).isEqualTo(LocalDate.of(2024, 10, 1));
    }

    @Test
    void testGetDiarioCaja() {
        DiarioCaja diario = new DiarioCaja();
        diario.setId(1L);
        diario.setFecha(LocalDate.of(2024, 9, 30));

        when(diarioCajaRepository.findById(1L)).thenReturn(Optional.of(diario));

        Optional<DiarioCaja> result = diarioCajaService.getDiarioCaja(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getFecha()).isEqualTo(LocalDate.of(2024, 9, 30));
    }

    @Test
    void testCreateDiarioCaja() {
        DiarioCaja nuevo = new DiarioCaja();
        nuevo.setFecha(LocalDate.of(2024, 10, 5));

        when(diarioCajaRepository.save(nuevo)).thenReturn(nuevo);

        DiarioCaja result = diarioCajaService.createDiarioCaja(nuevo);

        assertThat(result.getFecha()).isEqualTo(LocalDate.of(2024, 10, 5));
    }

    @Test
    void testUpdateDiarioCaja() {
        DiarioCaja actualizado = new DiarioCaja();
        actualizado.setId(3L);
        actualizado.setFecha(LocalDate.of(2024, 11, 1));

        when(diarioCajaRepository.save(actualizado)).thenReturn(actualizado);

        DiarioCaja result = diarioCajaService.updateDiarioCaja(actualizado);

        assertThat(result.getId()).isEqualTo(3L);
        assertThat(result.getFecha()).isEqualTo(LocalDate.of(2024, 11, 1));
    }

    @Test
    void testDeleteDiarioCaja() {
        Long id = 5L;

        doNothing().when(diarioCajaRepository).deleteById(id);

        diarioCajaService.deleteDiarioCaja(id);

        verify(diarioCajaRepository, times(1)).deleteById(id);
    }
}
