package com.ingsoftware.backend.services;

import com.ingsoftware.backend.model.Matricula;
import com.ingsoftware.backend.repository.MatriculaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MatriculaServiceTest {

    @Mock
    private MatriculaRepository matriculaRepository;

    @InjectMocks
    private MatriculaImpl matriculaService;

    @Test
    void testGetMatriculas() {
        Matricula m1 = new Matricula();
        m1.setId(1L);
        m1.setFecha(LocalDate.of(2024, 3, 1));

        when(matriculaRepository.findAll()).thenReturn(List.of(m1));

        List<Matricula> result = matriculaService.getMatriculas();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getFecha()).isEqualTo(LocalDate.of(2024, 3, 1));
    }

    @Test
    void testGetMatriculaById() {
        Matricula matricula = new Matricula();
        matricula.setId(1L);
        matricula.setFecha(LocalDate.of(2023, 12, 15));

        when(matriculaRepository.findById(1L)).thenReturn(Optional.of(matricula));

        Optional<Matricula> result = matriculaService.getMatricula(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getFecha()).isEqualTo(LocalDate.of(2023, 12, 15));
    }

    @Test
    void testCreateMatricula() {
        Matricula nueva = new Matricula();
        nueva.setFecha(LocalDate.of(2025, 1, 10));

        when(matriculaRepository.save(nueva)).thenReturn(nueva);

        Matricula result = matriculaService.createMatricula(nueva);

        assertThat(result.getFecha()).isEqualTo(LocalDate.of(2025, 1, 10));
    }

    @Test
    void testUpdateMatricula() {
        Matricula modificado = new Matricula();
        modificado.setId(2L);
        modificado.setFecha(LocalDate.of(2024, 6, 1));

        when(matriculaRepository.save(modificado)).thenReturn(modificado);

        Matricula result = matriculaService.updateMatricula(modificado);

        assertThat(result.getFecha()).isEqualTo(LocalDate.of(2024, 6, 1));
    }

    @Test
    void testDeleteMatricula() {
        doNothing().when(matriculaRepository).deleteById(1L);

        matriculaService.deleteMatricula(1L);

        verify(matriculaRepository, times(1)).deleteById(1L);
    }

}
