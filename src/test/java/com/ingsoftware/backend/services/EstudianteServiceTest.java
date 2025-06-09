package com.ingsoftware.backend.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.ingsoftware.backend.model.Estudiante;
import com.ingsoftware.backend.repository.EstudianteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class EstudianteServiceTest {

    @Mock
    private EstudianteRepository estudianteRepository;

    @InjectMocks
    private EstudianteImpl estudianteService;

    @Test
    void testGetEstudiantes() {
        Estudiante e1 = new Estudiante();
        e1.setId(1L);
        e1.setNombres("Juan");

        when(estudianteRepository.findAll()).thenReturn(List.of(e1));

        List<Estudiante> result = estudianteService.getEstudiantes();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNombres()).isEqualTo("Juan");
    }

    @Test
    void testGetEstudianteById() {
        Estudiante estudiante = new Estudiante();
        estudiante.setId(1L);
        estudiante.setNombres("Ana");

        when(estudianteRepository.findById(1L)).thenReturn(Optional.of(estudiante));

        Optional<Estudiante> result = estudianteService.getEstudiante(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getNombres()).isEqualTo("Ana");
    }

    @Test
    void testCreateEstudiante() {
        Estudiante nuevo = new Estudiante();
        nuevo.setNombres("Carlos");

        when(estudianteRepository.save(nuevo)).thenReturn(nuevo);

        Estudiante result = estudianteService.createEstudiante(nuevo);

        assertThat(result.getNombres()).isEqualTo("Carlos");
    }

    @Test
    void testUpdateEstudiante() {
        Estudiante modificado = new Estudiante();
        modificado.setId(2L);
        modificado.setNombres("Modificado");

        when(estudianteRepository.save(modificado)).thenReturn(modificado);

        Estudiante result = estudianteService.updateEstudiante(modificado);

        assertThat(result.getNombres()).isEqualTo("Modificado");
    }

    @Test
    void testDeleteEstudiante() {
        doNothing().when(estudianteRepository).deleteById(1L);

        estudianteService.deleteEstudiante(1L);

        verify(estudianteRepository, times(1)).deleteById(1L);
    }
}
