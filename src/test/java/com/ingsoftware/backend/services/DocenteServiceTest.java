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

import com.ingsoftware.backend.model.Docente;
import com.ingsoftware.backend.repository.DocenteRepository;

@ExtendWith(MockitoExtension.class)
class DocenteServiceTest {

    @Mock
    private DocenteRepository docenteRepository;

    @InjectMocks
    private DocenteImpl docenteService;

    @Test
    void testGetDocentes() {
        Docente d1 = new Docente();
        d1.setId(1L);
        d1.setNombres("Carlos");

        Docente d2 = new Docente();
        d2.setId(2L);
        d2.setNombres("Ana");

        when(docenteRepository.findAll()).thenReturn(Arrays.asList(d1, d2));

        List<Docente> result = docenteService.getDocentes();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getNombres()).isEqualTo("Carlos");
    }

    @Test
    void testGetDocente() {
        Docente docente = new Docente();
        docente.setId(1L);
        docente.setNombres("Lucía");

        when(docenteRepository.findById(1L)).thenReturn(Optional.of(docente));

        Optional<Docente> result = docenteService.getDocente(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getNombres()).isEqualTo("Lucía");
    }

    @Test
    void testCreateDocente() {
        Docente nuevo = new Docente();
        nuevo.setNombres("Mario");

        when(docenteRepository.save(nuevo)).thenReturn(nuevo);

        Docente result = docenteService.createDocente(nuevo);

        assertThat(result.getNombres()).isEqualTo("Mario");
    }

    @Test
    void testUpdateDocente() {
        Docente existente = new Docente();
        existente.setId(3L);
        existente.setNombres("Laura");

        when(docenteRepository.save(existente)).thenReturn(existente);

        Docente result = docenteService.updateDocente(existente);

        assertThat(result.getId()).isEqualTo(3L);
        assertThat(result.getNombres()).isEqualTo("Laura");
    }

    @Test
    void testDeleteDocente() {
        Long id = 4L;

        doNothing().when(docenteRepository).deleteById(id);

        docenteService.deleteDocente(id);

        verify(docenteRepository, times(1)).deleteById(id);
    }
}
