package com.ingsoftware.backend.services;

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

import com.ingsoftware.backend.model.Calificacion;
import com.ingsoftware.backend.repository.CalificacionRepository;

@ExtendWith(MockitoExtension.class)
class CalificacionServiceTest {

    @Mock
    private CalificacionRepository calificacionRepository;

    @InjectMocks
    private CalificacionImpl calificacionService;

    @Test
    void testGetCalificaciones() {
        Calificacion c1 = new Calificacion();
        c1.setId(1L);
        c1.setNota(90.0);

        Calificacion c2 = new Calificacion();
        c2.setId(2L);
        c2.setNota(75.0);

        when(calificacionRepository.findAll()).thenReturn(Arrays.asList(c1, c2));

        List<Calificacion> result = calificacionService.getCalificaciones();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getNota()).isEqualTo(90.0);
    }

    @Test
    void testGetCalificacionById_found() {
        Calificacion c = new Calificacion();
        c.setId(1L);
        c.setNota(80.0);

        when(calificacionRepository.findById(1L)).thenReturn(Optional.of(c));

        Optional<Calificacion> result = calificacionService.getCalificacion(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getNota()).isEqualTo(80.0);
    }

    @Test
    void testGetCalificacionById_notFound() {
        when(calificacionRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Calificacion> result = calificacionService.getCalificacion(99L);

        assertThat(result).isNotPresent();
    }

    @Test
    void testCreateCalificacion() {
        Calificacion nueva = new Calificacion();
        nueva.setNota(95.0);
        nueva.setFechaRegistro(LocalDate.now());

        when(calificacionRepository.save(nueva)).thenReturn(nueva);

        Calificacion result = calificacionService.createCalificacion(nueva);

        assertThat(result.getNota()).isEqualTo(95.0);
    }

    @Test
    void testUpdateCalificacion() {
        Calificacion existente = new Calificacion();
        existente.setId(5L);
        existente.setNota(70.0);

        when(calificacionRepository.save(existente)).thenReturn(existente);

        Calificacion result = calificacionService.updateCalificacion(existente);

        assertThat(result.getId()).isEqualTo(5L);
        assertThat(result.getNota()).isEqualTo(70.0);
    }

    @Test
    void testDeleteCalificacion() {
        Long id = 10L;

        doNothing().when(calificacionRepository).deleteById(id);

        calificacionService.deleteCalificacion(id);

        verify(calificacionRepository, times(1)).deleteById(id);
    }

    @Test
    void testCreateNullCalificacion_shouldThrowException() {
        when(calificacionRepository.save(null)).thenThrow(new IllegalArgumentException("Calificación inválida"));

        assertThatThrownBy(() -> calificacionService.createCalificacion(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Calificación inválida");
    }
}
