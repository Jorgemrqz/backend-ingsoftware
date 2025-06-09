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

import com.ingsoftware.backend.model.Clase;
import com.ingsoftware.backend.repository.ClaseRepository;

@ExtendWith(MockitoExtension.class)
class ClaseServiceTest {

    @Mock
    private ClaseRepository claseRepository;

    @InjectMocks
    private ClaseImpl claseService;

    @Test
    void testGetClases() {
        Clase c1 = new Clase();
        c1.setId(1L);
        c1.setAula("Aula 1");

        Clase c2 = new Clase();
        c2.setId(2L);
        c2.setAula("Aula 2");

        when(claseRepository.findAll()).thenReturn(Arrays.asList(c1, c2));

        List<Clase> clases = claseService.getClases();

        assertThat(clases).hasSize(2);
        assertThat(clases.get(0).getAula()).isEqualTo("Aula 1");
    }

    @Test
    void testGetClase_found() {
        Clase clase = new Clase();
        clase.setId(10L);
        clase.setAula("Laboratorio");

        when(claseRepository.findById(10L)).thenReturn(Optional.of(clase));

        Optional<Clase> result = claseService.getClase(10L);

        assertThat(result).isPresent();
        assertThat(result.get().getAula()).isEqualTo("Laboratorio");
    }

    @Test
    void testGetClase_notFound() {
        when(claseRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Clase> result = claseService.getClase(99L);

        assertThat(result).isNotPresent();
    }

    @Test
    void testCreateClase() {
        Clase nueva = new Clase();
        nueva.setAula("Aula B");

        when(claseRepository.save(nueva)).thenReturn(nueva);

        Clase result = claseService.createClase(nueva);

        assertThat(result.getAula()).isEqualTo("Aula B");
    }

    @Test
    void testUpdateClase() {
        Clase clase = new Clase();
        clase.setId(20L);
        clase.setAula("Aula C");

        when(claseRepository.save(clase)).thenReturn(clase);

        Clase result = claseService.updateClase(clase);

        assertThat(result.getId()).isEqualTo(20L);
        assertThat(result.getAula()).isEqualTo("Aula C");
    }

    @Test
    void testDeleteClase() {
        Long id = 15L;

        doNothing().when(claseRepository).deleteById(id);

        claseService.deleteClase(id);

        verify(claseRepository, times(1)).deleteById(id);
    }

    @Test
    void testCreateClase_null_shouldThrowException() {
        when(claseRepository.save(null)).thenThrow(new IllegalArgumentException("Clase inválida"));

        assertThatThrownBy(() -> claseService.createClase(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Clase inválida");
    }
}
