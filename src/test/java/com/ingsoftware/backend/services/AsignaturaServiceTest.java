package com.ingsoftware.backend.services;

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

import com.ingsoftware.backend.model.Asignatura;
import com.ingsoftware.backend.repository.AsignaturaRepository;

@ExtendWith(MockitoExtension.class)
class AsignaturaServiceTest {

    @Mock
    private AsignaturaRepository asignaturaRepository;

    @InjectMocks
    private AsignaturaImpl asignaturaService;

    @Test
    void testGetAsignaturas() {
        Asignatura a1 = new Asignatura();
        a1.setId(1L);
        a1.setNombre("Matemáticas");

        Asignatura a2 = new Asignatura();
        a2.setId(2L);
        a2.setNombre("Lengua");

        when(asignaturaRepository.findAll()).thenReturn(Arrays.asList(a1, a2));

        List<Asignatura> result = asignaturaService.getAsignaturas();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getNombre()).isEqualTo("Matemáticas");
    }

    @Test
    void testGetAsignatura() {
        Asignatura a = new Asignatura();
        a.setId(1L);
        a.setNombre("Ciencias");

        when(asignaturaRepository.findById(1L)).thenReturn(Optional.of(a));

        Optional<Asignatura> result = asignaturaService.getAsignatura(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getNombre()).isEqualTo("Ciencias");
    }

    @Test
    void testCreateAsignatura() {
        Asignatura nueva = new Asignatura();
        nueva.setNombre("Historia");

        when(asignaturaRepository.save(nueva)).thenReturn(nueva);

        Asignatura result = asignaturaService.createAsignatura(nueva);

        assertThat(result.getNombre()).isEqualTo("Historia");
    }

    @Test
    void testUpdateAsignatura() {
        Asignatura existente = new Asignatura();
        existente.setId(3L);
        existente.setNombre("Física");

        when(asignaturaRepository.save(existente)).thenReturn(existente);

        Asignatura result = asignaturaService.updateAsignatura(existente);

        assertThat(result.getId()).isEqualTo(3L);
        assertThat(result.getNombre()).isEqualTo("Física");
    }

    @Test
    void testDeleteAsignatura() {
        Long id = 5L;

        doNothing().when(asignaturaRepository).deleteById(id);

        asignaturaService.deleteAsignatura(id);

        verify(asignaturaRepository, times(1)).deleteById(id);
    }
}
